package goudarzi.ha.barfriends;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    EditText name, number;
    Button submit, view;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                break;
            case R.id.action_saved:

                break;
            case R.id.action_about:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        name = (EditText) findViewById(R.id.etName);
        number = (EditText) findViewById(R.id.etNumber);
        submit = (Button) findViewById(R.id.bSubmit);
        submit.setOnClickListener(this);
        view = (Button) findViewById(R.id.bView);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bSubmit:
                boolean working = true;
                try {
                    String names = name.getText().toString();
                    String numbers = number.getText().toString();
                    Numbers entry = new Numbers(this);
                    entry.open();
                    entry.createEntry(names, numbers);
                    entry.close();
                } catch (Exception e) {
                    working = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle(error);
                    TextView tv = new TextView(this);
                    tv.setText("Unsuccessful!");
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (working) {
                        Dialog d = new Dialog(this);
                        TextView tv = new TextView(this);
                        tv.setText("Success!");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.bView:
                Intent i = new Intent(this, FriendView.class);
                startActivity(i);
                break;
        }
    }
}
