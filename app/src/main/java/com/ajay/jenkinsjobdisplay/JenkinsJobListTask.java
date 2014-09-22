package com.ajay.jenkinsjobdisplay;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ajay.myfirstapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JenkinsJobListTask extends AsyncTask<String, String, JenkinsJob[]> {

    private final Activity activity;

    public JenkinsJobListTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        TextView loadingText = (TextView) this.activity.findViewById(R.id.loadingText);
        loadingText.setVisibility(View.VISIBLE);
    }

    @Override
    protected JenkinsJob[] doInBackground(String... strings) {

        SecurePreferences preferences = new SecurePreferences(activity, "com.example.ajay.myfirstapp.preferences.jenkins", "SomeKey123", true);
        String url = preferences.getString("jenkinsUrl");
        String username = preferences.getString("jenkinsUserName");
        String password = preferences.getString("jenkinsPassword");
        HttpRequest httpRequest = HttpRequest.get(url + "/api/json").basic(username, password).trustAllCerts().trustAllHosts();
        int code = httpRequest.code();

        if(code != 200)
            return new JenkinsJob[]{};

        String body = httpRequest.body();
        JenkinsJob[] jenkinsJobs = parseResponse(body);

        return jenkinsJobs;
    }

    private JenkinsJob[] parseResponse(String body) {
        try {
            JSONObject jsonReader = new JSONObject(body);
            JSONArray jobs = jsonReader.getJSONArray("jobs");
            JenkinsJob[] jenkinsJobs = new JenkinsJob[jobs.length()];
            for(int i = 0; i < jobs.length(); i++){
                JSONObject jobItem = jobs.getJSONObject(i);
                JenkinsJob job = new JenkinsJob();
                job.Name = jobItem.getString("name");
                job.Url = jobItem.getString("url");
                job.State = jobItem.getString("color");
                jenkinsJobs[i] = job;
            }
            return jenkinsJobs;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JenkinsJob[]{};
    }

    @Override
    protected void onPostExecute(JenkinsJob[] jobs) {
        ListView jobList = (ListView) this.activity.findViewById(R.id.jobList);
        JenkinsJobArrayAdapter adapter = new JenkinsJobArrayAdapter(this.activity, R.layout.job_item, jobs);
        jobList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        TextView loadingText = (TextView) this.activity.findViewById(R.id.loadingText);
        loadingText.setVisibility(View.GONE);
    }
}
