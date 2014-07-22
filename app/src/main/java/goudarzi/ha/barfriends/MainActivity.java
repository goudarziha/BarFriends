package goudarzi.ha.barfriends;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    final Context context = this;
    EditText name, number;
    Button submit, view;
    CheckBox cute;

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
                Intent i = new Intent(this, Settings.class);
                startActivity(i);
                break;
            case R.id.action_saved:
                Intent x = new Intent(this, FriendView.class);
                startActivity(x);
                break;
            case R.id.action_about:
                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setTitle("About Bar Friends");
                ad.setMessage("This app saves names + numbers of people you meet out and " +
                        "sends them a text during submission");
                ad.setCancelable(false);
                ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = ad.create();
                alertDialog.show();
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
        cute = (CheckBox) findViewById(R.id.cbCute);
        cute.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bSubmit:
                boolean working = true;
                try {
                    String names = name.getText().toString();
                    String numbers = number.getText().toString();

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setType("vnd.android-dir/mms-sms");
                    i.putExtra("address", numbers);
                    if (cute.isChecked()) {
                        i.putExtra("sms_body", "Hey, " + names + ", it was nice meeting you and I think " +
                                "you are really cute too! ;)");
                    } else {
                        i.putExtra("sms_body", "Hey, " + names + ", it was nice meeting you!");
                    }
                    startActivity(i);

                    Numbers entry = new Numbers(this);
                    entry.open();
                    entry.createEntry(names, numbers);
                    entry.close();
                } catch (Exception e) {
                    working = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Unsuccessful " + error);
                    TextView tv = new TextView(this);
                    tv.setText("Please try submitting again.");
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (working) {
                        Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show();
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
