package goudarzi.ha.barfriends;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;

public class Settings extends ActionBarActivity {

    CheckBox cute;
    public boolean checked = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cute = (CheckBox) findViewById(R.id.cbCute);
        if (cute.isChecked()) {
            checked = true;
        }
    }
}
