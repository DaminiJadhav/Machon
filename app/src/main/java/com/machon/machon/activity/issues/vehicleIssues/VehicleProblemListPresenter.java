package com.machon.machon.activity.issues.vehicleIssues;

import com.machon.machon.activity.signup.mechanic.garageType.MechanicGarageTypeContractor;
import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.response.GetVehicleProblemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleProblemListPresenter implements VehicleProblemListContractor.Presenter{


    VehicleProblemListContractor.View mview;

    VehicleProblemListPresenter(VehicleProblemListContractor.View mView){
        this.mview = mView;
    }

    @Override
    public void getVehicleProblemList() {
        new CustomRetroRequest().getBaseUrl().getVehicleProblemList().enqueue(new Callback<GetVehicleProblemResponse>() {
            @Override
            public void onResponse(Call<GetVehicleProblemResponse> call, Response<GetVehicleProblemResponse> response) {
                if(response.isSuccessful()){
                    if(Integer.parseInt(response.body().getStatus()) == 1) {
                        mview.vehicleProblemListSuccess(response.body());
                    }else {
                        mview.vehicleProblemListFailure(response.body().getMessage());
                    }
                }else{
                    mview.vehicleProblemListFailure("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<GetVehicleProblemResponse> call, Throwable t) {
                mview.vehicleProblemListFailure(t.getMessage());

            }
        });
    }
}
