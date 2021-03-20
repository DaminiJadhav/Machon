package com.machon.machon.activity.issues;

import com.machon.machon.model.request.PostGarageIssueRequest;
import com.machon.machon.model.request.mechanicRegistration.MechanicRegistrationRequest;
import com.machon.machon.model.response.MechanicRegistrationResponse;
import com.machon.machon.model.response.PostGarageIssueResponse;

public interface VehicleIssueContractor {


    interface Presenter
    {
        void postVehicleIssue(PostGarageIssueRequest postGarageIssueRequest);
    }


    interface View
    {
        void vehicleIssueSuccess(PostGarageIssueResponse postGarageIssueResponse);
        void vehicleIssueFailure(String message);

    }

}
