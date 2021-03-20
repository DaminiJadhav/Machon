package com.machon.machon.fragment.garageUserVehiclRequest;

import android.util.Log;

import com.machon.machon.fragment.garageUserVehiclRequest.GarageVehicleIssueRequestContractor;
import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.request.acceptVehicleIssueRequest.UserGarageRequestAcceptRequest;
import com.machon.machon.model.response.GarageVehicleIssueRequestResponse;
import com.machon.machon.model.response.acceptVehicleIssue.UserGarageRequestAcceptResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GarageVehicleIssueRequestPresenter implements GarageVehicleIssueRequestContractor.Presenter {
    private GarageVehicleIssueRequestContractor.View mView;

    public GarageVehicleIssueRequestPresenter(GarageVehicleIssueRequestContractor.View mView){
        this.mView = mView;
    }


    @Override
    public void getGarageVehicleIssuesRequest(String garageId) {
       new CustomRetroRequest().getBaseUrl().getGarageUserVehicleIssue(garageId).enqueue(new Callback<GarageVehicleIssueRequestResponse>() {
           @Override
           public void onResponse(Call<GarageVehicleIssueRequestResponse> call, Response<GarageVehicleIssueRequestResponse> response) {
               if(response.isSuccessful()){
                   if(Integer.parseInt(response.body().getStatus()) == 1) {
                       mView.getGarageVehicleIssuesRequestResponseSuccess(response.body());
                       Log.i("Garage Request Response",""+response.body().getUserGarageIssueRequests().get(0).getCurrentDateTime());

                   }else {
                       mView.getGarageVehicleIssuesRequestResponseFailure(response.body().getMessage());
                   }
               }else{
                   mView.getGarageVehicleIssuesRequestResponseFailure("Something went wrong");
               }
           }

           @Override
           public void onFailure(Call<GarageVehicleIssueRequestResponse> call, Throwable t) {
               mView.getGarageVehicleIssuesRequestResponseFailure(t.getMessage());
           }
       });

    }

    @Override
    public void acceptUserGarageVehicleIssue(UserGarageRequestAcceptRequest userGarageRequestAcceptRequest) {
        new CustomRetroRequest().getBaseUrl().postGarageAcceptRequest(userGarageRequestAcceptRequest).enqueue(new Callback<UserGarageRequestAcceptResponse>() {
            @Override
            public void onResponse(Call<UserGarageRequestAcceptResponse> call, Response<UserGarageRequestAcceptResponse> response) {
                if(response.isSuccessful()){
                    if(Integer.parseInt(response.body().getStatus()) == 1) {
                        mView.acceptVehicleIssueSuccess(response.body());
                    }else {
                        mView.acceptVehicleIssueFailure(response.body().getMessage());
                    }
                }else{
                    mView.acceptVehicleIssueFailure("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<UserGarageRequestAcceptResponse> call, Throwable t) {
                mView.acceptVehicleIssueFailure(t.getMessage());

            }
        });
    }
}
