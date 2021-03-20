package com.machon.machon.model.request;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordRequest {

    @SerializedName("MobNo")
    String MobNo;
    @SerializedName("Password")
    String Password;
    @SerializedName("Flag")
    int Flag;


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

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }
}
