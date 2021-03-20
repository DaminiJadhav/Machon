package com.machon.machon.activity.home.userhome;

import com.machon.machon.model.response.GetUserRequestAcceptListResponse;

public interface UserHomePageContractor {

    interface Presenter
    {
        void getUserGarageResponse(String userid);

    }


    interface View
    {
        void userVehicleIssueListSuccess(GetUserRequestAcceptListResponse getUserRequestAcceptListResponse);
        void userVehicleIssueListFailure(String message);

    }
}
