package com.machon.machon.model.response.userRegistration;

import com.google.gson.annotations.SerializedName;
import com.machon.machon.model.response.LoginResponse;

public class UserRegistrationResponse {

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
