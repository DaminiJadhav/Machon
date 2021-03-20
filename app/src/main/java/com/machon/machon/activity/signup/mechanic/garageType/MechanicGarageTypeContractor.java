package com.machon.machon.activity.signup.mechanic.garageType;

import com.machon.machon.model.response.GetVehicleTypeResponse;

public interface MechanicGarageTypeContractor {

    interface Presenter{
        void getMechanicGarageType();
    }

    interface  View{
        void mechanicGarageTypeSuccess(GetVehicleTypeResponse response);
        void mechanicGarageTypeFailure(String message);

    }
}
