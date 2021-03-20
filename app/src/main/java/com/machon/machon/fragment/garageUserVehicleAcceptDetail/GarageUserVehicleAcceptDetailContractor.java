package com.machon.machon.fragment.garageUserVehicleAcceptDetail;

import com.machon.machon.model.response.GarageVehicleIssueRequestResponse;

public interface GarageUserVehicleAcceptDetailContractor {

    interface Presenter{
        void getGarageVehicleIssuesRequest(String garageId);

    }

    interface View{
        void getGarageVehicleIssuesRequestResponseSuccess(GarageVehicleIssueRequestResponse garageVehicleIssueRequestResponse);
        void getGarageVehicleIssuesRequestResponseFailure(String message);
    }
}
