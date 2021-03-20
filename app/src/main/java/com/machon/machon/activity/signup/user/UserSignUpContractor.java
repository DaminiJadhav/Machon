package com.machon.machon.activity.signup.user;

import com.machon.machon.model.request.userRegistration.UserRegistrationRequest;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.model.response.userRegistration.UserRegistrationResponse;

public class UserSignUpContractor {

    interface Presenter{
        void postUserRegistration(UserRegistrationRequest userRegistrationRequest);
    }

    interface View{
        void userRegistrationSuccess(UserRegistrationResponse userRegistrationResponse);
        void userRegistrationFailure(String message);
    }
}
