package com.machon.machon.activity.signup.mechanic;

import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.request.mechanicRegistration.MechanicRegistrationRequest;
import com.machon.machon.model.response.MechanicRegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MechanicSignUpPresenter implements MechanicSignUpContractor.Presenter {
    MechanicSignUpContractor.View mView;
    MechanicSignUpPresenter(MechanicSignUpContractor.View mView){
        this.mView = mView;
    }


    @Override
    public void postMechanicSignUp(MechanicRegistrationRequest mechanicRegistrationRequest) {
        new CustomRetroRequest().getBaseUrl().postMechanicRegistration(mechanicRegistrationRequest).enqueue(new Callback<MechanicRegistrationResponse>() {
            @Override
            public void onResponse(Call<MechanicRegistrationResponse> call, Response<MechanicRegistrationResponse> response) {
                if(response.isSuccessful()){
                    if(Integer.parseInt(response.body().getStatus()) == 1) {
                        mView.signUpSuccess(response.body());
                    }else {
                        mView.signUpFailure(response.body().getMessage());
                    }
                }else{
                    mView.signUpFailure("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<MechanicRegistrationResponse> call, Throwable t) {
                mView.signUpFailure(t.getMessage());

            }
        });
    }
}
