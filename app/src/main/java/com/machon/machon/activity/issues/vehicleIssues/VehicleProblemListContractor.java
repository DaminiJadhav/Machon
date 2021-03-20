package com.machon.machon.activity.issues.vehicleIssues;

import com.machon.machon.model.response.GetVehicleProblemResponse;

public interface VehicleProblemListContractor {

    interface Presenter{
        void getVehicleProblemList();
    }

    interface  View{
        void vehicleProblemListSuccess(GetVehicleProblemResponse response);
        void vehicleProblemListFailure(String message);

    }

}
