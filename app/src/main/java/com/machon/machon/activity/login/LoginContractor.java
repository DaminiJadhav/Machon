package com.machon.machon.activity.login;

import com.machon.machon.model.request.LoginMechanicRequest;
import com.machon.machon.model.request.LoginUserRequest;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;

public interface LoginContractor {
    interface Presenter{
        void postLogin(LoginUserRequest loginUserRequest);
        void postMechanicLogin(LoginMechanicRequest loginMechanicRequest);
    }

    interface View{
        void loginSuccess(LoginResponse response);
        void loginFailure(String message);
        void loginMechanicSuccess(LoginMechanicResponse response);
        void loginMechanicFailure(String message);
    }
}
