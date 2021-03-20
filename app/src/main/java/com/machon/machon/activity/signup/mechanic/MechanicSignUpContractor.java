package com.machon.machon.activity.signup.mechanic;

import com.machon.machon.model.request.mechanicRegistration.MechanicRegistrationRequest;
import com.machon.machon.model.response.GetVehicleTypeResponse;
import com.machon.machon.model.response.MechanicRegistrationResponse;

public interface MechanicSignUpContractor {

    interface Presenter
    {
        void postMechanicSignUp(MechanicRegistrationRequest mechanicRegistrationRequest);
    }


    interface View
    {
        void signUpSuccess(MechanicRegistrationResponse mechanicRegistrationResponse);
        void signUpFailure(String message);

    }

}
