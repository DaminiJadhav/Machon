package com.machon.machon.activity.forgetPassword;

import com.machon.machon.activity.verifyphonenumber.VerificationContractor;
import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.request.ForgetPasswordRequest;
import com.machon.machon.model.response.ForgetPasswordResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordPresenter implements ForgetPasswordContractor.Presenter{
    private ForgetPasswordContractor.View mView;

    public ForgetPasswordPresenter(ForgetPasswordContractor.View mView) {
        this.mView = mView;
    }


    @Override
    public void postForgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
            new CustomRetroRequest().getBaseUrl().postForgetPassword(forgetPasswordRequest).enqueue(new Callback<ForgetPasswordResponse>() {
                @Override
                public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equals("1")){
                            mView.postForgetPasswordSuccess(response.body());
                        }else {
                            mView.postForgetPasswordFailure(response.body().getMessage());
                        }
                    }else {
                        mView.postForgetPasswordFailure("Something went wrong. Please try again.");
                    }
                }

                @Override
                public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                    mView.postForgetPasswordFailure(t.getMessage());

                }
            });
    }
}
