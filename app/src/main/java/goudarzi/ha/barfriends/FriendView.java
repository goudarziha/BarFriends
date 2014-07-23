package goudarzi.ha.barfriends;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class FriendView extends Activity implements View.OnClickListener {

    EditText sqlRow;
    TextView name, number;
    ListView lv;
    ArrayAdapter<String> m_adapter;
    ArrayList<String> m_listItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        initialize();
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
        Button sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
        sqlModify.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        name = (TextView) findViewById(R.id.tvName);
        number = (TextView) findViewById(R.id.tvNumber);
        lv = (ListView) findViewById(R.id.lvData);
        m_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, m_listItems);
        String name = getIntent().getExtras().getString("names");
        String number = getIntent().getExtras().getString("number");
        m_listItems.add(new String(name));
        m_adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bGetInfo:
                String s = sqlRow.getText().toString();
                int l = Integer.parseInt(s);
                Numbers num = new Numbers(this);
                try {
                    num.open();
                    String returnedName = num.getName(l);
                    String returnedNumber = num.getNumber(l);
                    num.close();
                    name.setText(returnedName);
                    number.setText(returnedNumber);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bModify:
                String sRow = sqlRow.getText().toString();
                String sName = name.getText().toString();
                String sNumber = number.getText().toString();
                int lRow = Integer.parseInt(sRow);
                Numbers ex = new Numbers(this);
                try {
                    ex.open();
                    ex.updateEntry(lRow, sName, sNumber);
                    ex.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bDelete:

                break;
        }
    }
}
