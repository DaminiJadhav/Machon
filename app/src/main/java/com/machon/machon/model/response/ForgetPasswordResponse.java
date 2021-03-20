package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordResponse {

    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("Response")
    Response Response;

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

    public ForgetPasswordResponse.Response getResponse() {
        return Response;
    }

    public void setResponse(ForgetPasswordResponse.Response response) {
        Response = response;
    }

    public class Response{
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

}
