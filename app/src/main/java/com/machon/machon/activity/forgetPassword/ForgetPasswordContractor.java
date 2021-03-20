package com.machon.machon.activity.forgetPassword;

import com.machon.machon.model.request.ForgetPasswordRequest;
import com.machon.machon.model.request.LoginMechanicRequest;
import com.machon.machon.model.request.LoginUserRequest;
import com.machon.machon.model.response.ForgetPasswordResponse;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;

public interface ForgetPasswordContractor {


    interface Presenter{
        void postForgetPassword(ForgetPasswordRequest forgetPasswordRequest);
    }

    interface View{
        void postForgetPasswordSuccess(ForgetPasswordResponse forgetPasswordResponse);
        void postForgetPasswordFailure(String message);
    }
}
