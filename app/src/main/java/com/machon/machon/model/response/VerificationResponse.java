package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

public class VerificationResponse {
    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("OTP")
    String OTP;

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

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}


/*
{
    "Status": 1,
    "Message": "OTP successfully sended on mobile number",
    "OTP": "1997"
}
 */