package com.machon.machon.fragment.garageUserVehiclRequest;


import com.machon.machon.model.request.acceptVehicleIssueRequest.UserGarageRequestAcceptRequest;
import com.machon.machon.model.response.GarageVehicleIssueRequestResponse;
import com.machon.machon.model.response.acceptVehicleIssue.UserGarageRequestAcceptResponse;

public interface GarageVehicleIssueRequestContractor {

    interface Presenter{
        void getGarageVehicleIssuesRequest(String garageId);
        void acceptUserGarageVehicleIssue(UserGarageRequestAcceptRequest userGarageRequestAcceptRequest);

    }

    interface View{

        void getGarageVehicleIssuesRequestResponseSuccess(GarageVehicleIssueRequestResponse garageVehicleIssueRequestResponse);
        void getGarageVehicleIssuesRequestResponseFailure(String message);
        void acceptVehicleIssueSuccess(UserGarageRequestAcceptResponse garageVehicleIssueRequestResponse);
        void acceptVehicleIssueFailure(String message);
    }
}
