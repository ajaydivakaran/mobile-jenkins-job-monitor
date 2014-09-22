package com.ajay.jenkinsjobdisplay;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ajay.jenkinsjobdisplay.JenkinsJob;
import com.example.ajay.myfirstapp.R;


public class JenkinsJobArrayAdapter extends ArrayAdapter<JenkinsJob> {

    public JenkinsJobArrayAdapter(Context context, int textViewResourceId, JenkinsJob[] items) {
        super(context, textViewResourceId, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JenkinsJob job = getItem(position);
        JobViewHolder viewHolder = null;

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.job_item, null);
            viewHolder = new JobViewHolder();
            viewHolder.Name = (TextView) convertView.findViewById(R.id.jobName);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (JobViewHolder) convertView.getTag();
        }

        viewHolder.Name.setText(job.Name);
        convertView.setBackgroundColor(job.getColor());

        return  convertView;
    }

    private class JobViewHolder {
        TextView Name;
    }
}
