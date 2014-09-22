package com.ajay.jenkinsjobdisplay;

import android.graphics.Color;

public class JenkinsJob {
    public String Name;
    public String Url;
    public String State;

    public int getColor(){
        if(State.equals("notbuilt"))
            return Color.LTGRAY;
        else if(State.equals("blue"))
            return Color.GREEN;
        else if(State.equals("red"))
            return Color.RED;

        return Color.DKGRAY;
    }
}
