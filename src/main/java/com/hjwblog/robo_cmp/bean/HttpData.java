package com.hjwblog.robo_cmp.bean;

import com.google.gson.annotations.SerializedName;

public class HttpData {
    private String version;
    private int result;
    @SerializedName("hostName")
    private String hostName;
    public void setVersion(String version) {
        this.version = version;
    }
    public String getVersion() {
        return version;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public int getResult() {
        return result;
    }

    public void setHostname(String hostname) {
        this.hostName = hostname;
    }
    public String getHostname() {
        return hostName;
    }
}
