package goudarzi.ha.barfriends;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText name, number;
    Button submit;

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
    }

    @Override
    public void onClick(View view) {

    }
}
