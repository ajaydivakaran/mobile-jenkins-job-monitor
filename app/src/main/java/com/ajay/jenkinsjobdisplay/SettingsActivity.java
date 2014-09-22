package com.ajay.jenkinsjobdisplay;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ajay.myfirstapp.R;


public class SettingsActivity extends Activity{

    private EditText jenkinsUrlEditText;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private SecurePreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        preferences = new SecurePreferences(this, "com.ajay.jenkinsjobdisplay.preferences.jenkins", "SomeKey123", true);

        jenkinsUrlEditText = (EditText) findViewById(R.id.jenkinsUrl);
        jenkinsUrlEditText.setText(preferences.getString("jenkinsUrl"));
        userNameEditText = (EditText) findViewById(R.id.userName);
        userNameEditText.setText(preferences.getString("jenkinsUserName"));
        passwordEditText = (EditText) findViewById(R.id.password);
        passwordEditText.setText(preferences.getString("jenkinsPassword"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.save_settings) {
            this.saveSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveSettings() {
        this.preferences.put("jenkinsUrl", jenkinsUrlEditText.getText().toString());
        this.preferences.put("jenkinsUserName", userNameEditText.getText().toString());
        this.preferences.put("jenkinsPassword", passwordEditText.getText().toString());
        Toast.makeText(this, "Saved settings", Toast.LENGTH_SHORT).show();
    }
}
