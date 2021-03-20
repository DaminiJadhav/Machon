package com.machon.machon.activity.signup.mechanic.garageType;

import com.machon.machon.activity.signup.mechanic.MechanicSignUpContractor;
import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.response.GetVehicleTypeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MechanicGarageTypePresenter implements MechanicGarageTypeContractor.Presenter {
    MechanicGarageTypeContractor.View mview;

    public MechanicGarageTypePresenter(MechanicGarageTypeContractor.View mView){
        this.mview = mView;
    }


    @Override
    public void getMechanicGarageType() {
        new CustomRetroRequest().getBaseUrl().getMechanicgarages().enqueue(new Callback<GetVehicleTypeResponse>() {
            @Override
            public void onResponse(Call<GetVehicleTypeResponse> call, Response<GetVehicleTypeResponse> response) {
                if(response.isSuccessful()){
                    if(Integer.parseInt(response.body().getStatus()) == 1) {
                        mview.mechanicGarageTypeSuccess(response.body());
                    }else {
                        mview.mechanicGarageTypeFailure(response.body().getMessage());
                    }
                }else{
                    mview.mechanicGarageTypeFailure("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<GetVehicleTypeResponse> call, Throwable t) {
                mview.mechanicGarageTypeFailure(t.getMessage());

            }
        });
    }
}
