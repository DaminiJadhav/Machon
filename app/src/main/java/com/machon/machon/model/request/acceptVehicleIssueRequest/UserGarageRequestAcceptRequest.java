package com.machon.machon.model.request.acceptVehicleIssueRequest;

import com.google.gson.annotations.SerializedName;

public class UserGarageRequestAcceptRequest {
    @SerializedName("GarageId")
    String GarageId;
    @SerializedName("UserId")
    String UserId;
    @SerializedName("RequestId")
    String RequestId;


    public String getGarageId() {
        return GarageId;
    }

    public void setGarageId(String garageId) {
        GarageId = garageId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }
}
