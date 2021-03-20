package com.machon.machon.activity.verifyphonenumber;

import com.machon.machon.model.response.VerificationResponse;

public interface VerificationContractor {
    interface Presenter{
        void sendVerficationCode(String mobileNumber,int flag,int roleflag);
    }

    interface View{

        void sendVerficationCodeFail(String message);

        void sendVerficationCodeSuccess(VerificationResponse response);
    }
}
