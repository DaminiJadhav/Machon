package com.machon.machon.activity.issues;

import android.util.Log;
import android.widget.Toast;

import com.machon.machon.activity.signup.mechanic.MechanicSignUpContractor;
import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.request.PostGarageIssueRequest;
import com.machon.machon.model.response.PostGarageIssueResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VechicleIssuePresenter implements VehicleIssueContractor.Presenter {
    VehicleIssueContractor.View mView;
    VechicleIssuePresenter(VehicleIssueContractor.View mView){
        this.mView = mView;
    }


    @Override
    public void postVehicleIssue(PostGarageIssueRequest postGarageIssueRequest) {
        new CustomRetroRequest().getBaseUrl().postVehicleIssue(postGarageIssueRequest).enqueue(new Callback<PostGarageIssueResponse>() {
            @Override
            public void onResponse(Call<PostGarageIssueResponse> call, Response<PostGarageIssueResponse> response) {
                if(response.isSuccessful()){
                    if(Integer.parseInt(response.body().getStatus()) == 1) {
                        mView.vehicleIssueSuccess(response.body());
                    }else {
                        mView.vehicleIssueFailure(response.body().getMessage());
                    }
                }else{
                    mView.vehicleIssueFailure("Something went wrong");
                }

                Log.d("Vehicle Issue :",""+response.body().getMessage());
            }

            @Override
            public void onFailure(Call<PostGarageIssueResponse> call, Throwable t) {
                mView.vehicleIssueFailure(t.getMessage());

            }
        });
    }
}
