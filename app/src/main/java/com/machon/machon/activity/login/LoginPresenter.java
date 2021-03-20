package com.machon.machon.activity.login;

import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.request.LoginMechanicRequest;
import com.machon.machon.model.request.LoginUserRequest;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContractor.Presenter{
    private LoginContractor.View mView;
    LoginPresenter(LoginContractor.View mView){
        this.mView = mView;
    }


    @Override
    public void postLogin(LoginUserRequest loginUserRequest) {
        new CustomRetroRequest().getBaseUrl().postLogin(loginUserRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    if(Integer.parseInt(response.body().getStatus()) == 1) {
                        mView.loginSuccess(response.body());
                    }else {
                        mView.loginFailure(response.body().getMessage());
                    }
                }else{
                    mView.loginFailure("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mView.loginFailure(t.getMessage());
            }
        });
    }

    @Override
    public void postMechanicLogin(LoginMechanicRequest loginMechanicRequest) {
            new CustomRetroRequest().getBaseUrl().postMechanicLogin(loginMechanicRequest).enqueue(new Callback<LoginMechanicResponse>() {
                @Override
                public void onResponse(Call<LoginMechanicResponse> call, Response<LoginMechanicResponse> response) {
                    if(response.isSuccessful()){
                        if(Integer.parseInt(response.body().getStatus()) == 1) {
                            mView.loginMechanicSuccess(response.body());
                        }else {
                            mView.loginMechanicFailure(response.body().getMessage());
                        }
                    }else{
                        mView.loginMechanicFailure("Something went wrong");
                    }
                }

                @Override
                public void onFailure(Call<LoginMechanicResponse> call, Throwable t) {
                    mView.loginMechanicFailure(t.getMessage());

                }
            });
    }
}
