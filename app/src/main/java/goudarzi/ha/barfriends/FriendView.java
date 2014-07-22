package goudarzi.ha.barfriends;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class FriendView extends Activity implements View.OnClickListener {

    EditText sqlRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
        Numbers info = new Numbers(this);
        try {
            info.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String data = info.getData();
        info.close();
        tv.setText(data);
    }

    public void initialize() {
        sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
        Button sqlModify = (Button) findViewById(R.id.bModify);
        Button sqlDelete = (Button) findViewById(R.id.bDelete);
        sqlModify.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bModify:

                break;
            case R.id.bDelete:

                break;
        }
    }
}
