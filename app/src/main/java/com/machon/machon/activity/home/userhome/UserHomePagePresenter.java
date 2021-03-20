package com.machon.machon.activity.home.userhome;

import com.machon.machon.activity.signup.mechanic.garageType.MechanicGarageTypeContractor;
import com.machon.machon.activity.signup.user.UserSignUpContractor;
import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.request.mechanicRegistration.MechanicRegistrationRequest;
import com.machon.machon.model.response.GetUserRequestAcceptListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHomePagePresenter implements UserHomePageContractor.Presenter {

    UserHomePageContractor.View mview;

    public UserHomePagePresenter(UserHomePageContractor.View mView){
        this.mview = mView;
    }


    @Override
    public void getUserGarageResponse(String userid) {
         new CustomRetroRequest().getBaseUrl().getUserIssueRequestList(userid).enqueue(new Callback<GetUserRequestAcceptListResponse>() {
             @Override
             public void onResponse(Call<GetUserRequestAcceptListResponse> call, Response<GetUserRequestAcceptListResponse> response) {
                 if(response.isSuccessful()){
                     if(Integer.parseInt(response.body().getStatus()) == 1) {
                         mview.userVehicleIssueListSuccess(response.body());
                     }else {
                         mview.userVehicleIssueListFailure(response.body().getMessage());
                     }
                 }else{
                     mview.userVehicleIssueListFailure("Something went wrong");
                 }
             }

             @Override
             public void onFailure(Call<GetUserRequestAcceptListResponse> call, Throwable t) {
                 mview.userVehicleIssueListFailure(t.getMessage());

             }
         });
    }
}
