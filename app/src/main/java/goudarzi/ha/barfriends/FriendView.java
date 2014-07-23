package goudarzi.ha.barfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class FriendView extends Activity implements View.OnClickListener {

    EditText sqlRow, sqlName, sqlNumber;
    Button sqlModify, sqlDelete, sqlGetInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        initialize();
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
        Numbers info = new Numbers(this);
        try {
            info.open();
            String data = info.getData();
            info.close();
            tv.setText(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
        sqlName = (EditText) findViewById(R.id.sqlName);
        sqlNumber = (EditText) findViewById(R.id.sqlNumber);
        sqlModify = (Button) findViewById(R.id.bModify);
        sqlDelete = (Button) findViewById(R.id.bDelete);
        sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
        sqlModify.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        /*    case R.id.bGetInfo:
                try {
                    String s = sqlRow.getText().toString();
                    long l = Long.parseLong(s);
                    Numbers num = new Numbers(this);
                    num.open();
                    String returnedName = num.getName(l);
                    String returnedNumber = num.getNumber(l);
                    num.close();
                    sqlName.setText(returnedName);
                    sqlNumber.setText(returnedNumber);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Get Unsuccessful", Toast.LENGTH_LONG).show();
                }
                break;
        */
            case R.id.bModify:
                try {
                    String sRow = sqlRow.getText().toString();
                    String sName = sqlName.getText().toString();
                    String sNumber = sqlNumber.getText().toString();
                    int lRow = Integer.parseInt(sRow);
                    Numbers ex = new Numbers(this);
                    ex.open();
                    ex.updateEntry(lRow, sName, sNumber);
                    ex.close();
                    Toast.makeText(this, "Edit Successful", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Edit Unsuccessful", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bDelete:
                try {
                    String dRow = sqlRow.getText().toString();
                    int iRow = Integer.parseInt(dRow);
                    Numbers del = new Numbers(this);
                    del.open();
                    del.deleteEntry(iRow);
                    del.close();
                    Toast.makeText(this, "Delete Successful", Toast.LENGTH_LONG).show();
                    Intent i = getIntent();
                    finish();
                    startActivity(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Delete Unsuccessful", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
