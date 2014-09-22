package com.ajay.jenkinsjobdisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ajay.myfirstapp.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.jenkinsSettings:
                openSettings();
                return true;
            case R.id.refreshJobs:
                refreshJobs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshJobs() {
        new JenkinsJobListTask(this).execute();
    }

    private void openSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
