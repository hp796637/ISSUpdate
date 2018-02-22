
package com.hpco.harishpolusani.issupdates.Home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Response {

    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("risetime")
    @Expose
    private Integer risetime;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getRisetime() {
        Date date = new Date();
        date.setTime((long)risetime*1000);
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss aaa z");
        return dateFormat.format(date);
    }

    public void setRisetime(Integer risetime) {
        this.risetime = risetime;
    }

}
