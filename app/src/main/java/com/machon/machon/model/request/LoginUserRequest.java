package com.machon.machon.model.request;

import com.google.gson.annotations.SerializedName;

public class LoginUserRequest {
    @SerializedName("Password")
    String password;
    @SerializedName("MobNo")
    String mobNo;
    @SerializedName("FCMTocken")
    String FCMTocken;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getFCMTocken() {
        return FCMTocken;
    }

    public void setFCMTocken(String FCMTocken) {
        this.FCMTocken = FCMTocken;
    }
}


/*
{
"UserName" : "832989732",

"Password" : "qwerty"
}
 */