package com.machon.machon.model.response.acceptVehicleIssue;

import com.google.gson.annotations.SerializedName;

public class UserGarageRequestAcceptResponse {


    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
