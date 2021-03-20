package com.machon.machon.model.request.firebaseToken;

import com.google.gson.annotations.SerializedName;

public class GarageFirebaseTokenRequest {

    @SerializedName("Id")
    String Id;
    @SerializedName("UserFCMTocken")
    String UserFCMTocken;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserFCMTocken() {
        return UserFCMTocken;
    }

    public void setUserFCMTocken(String userFCMTocken) {
        UserFCMTocken = userFCMTocken;
    }
}
