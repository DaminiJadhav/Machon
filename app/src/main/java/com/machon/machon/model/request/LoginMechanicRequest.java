package com.machon.machon.model.request;

import com.google.gson.annotations.SerializedName;

public class LoginMechanicRequest {
    @SerializedName("MobNo")
    String MobNo;
    @SerializedName("Password")
    String Password;
    @SerializedName("FCMTocken")
    String FCMTocken;

    public String getMobNo() {
        return MobNo;
    }

    public void setMobNo(String mobNo) {
        MobNo = mobNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public String getFCMTocken() {
        return FCMTocken;
    }

    public void setFCMTocken(String FCMTocken) {
        this.FCMTocken = FCMTocken;
    }
}
