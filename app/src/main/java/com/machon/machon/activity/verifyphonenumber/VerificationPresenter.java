package com.machon.machon.activity.verifyphonenumber;

import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.response.VerificationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationPresenter implements VerificationContractor.Presenter {
    private VerificationContractor.View mView;

    public VerificationPresenter(VerificationContractor.View mView) {
        this.mView = mView;
    }

    @Override
    public void sendVerficationCode(String mobileNumber,int flag,int roleflag) {
        new CustomRetroRequest().getBaseUrl().postVerification(mobileNumber,"",flag,roleflag).enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("1")){
                        mView.sendVerficationCodeSuccess(response.body());
                    }else {
                        mView.sendVerficationCodeFail(response.body().getMessage());
                    }
                }else {
                    mView.sendVerficationCodeFail("Something went wrong. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<VerificationResponse> call, Throwable t) {
                mView.sendVerficationCodeFail(t.getMessage());
            }
        });
    }
}
