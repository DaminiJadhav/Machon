package com.machon.machon.firebase;

import android.util.Log;

import com.machon.machon.activity.login.LoginContractor;
import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.request.LoginMechanicRequest;
import com.machon.machon.model.request.LoginUserRequest;
import com.machon.machon.model.request.firebaseToken.GarageFirebaseTokenRequest;
import com.machon.machon.model.request.firebaseToken.UserFirebaseTokenRequest;
import com.machon.machon.model.response.firebaseToken.GarageFirebaseTokenResponse;
import com.machon.machon.model.response.firebaseToken.UserFirebaseTokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserGarageFirebaseTokenPresenter implements UserGarageFirebaseTokenContractor.Presenter {
    private UserGarageFirebaseTokenContractor.View mView;
    UserGarageFirebaseTokenPresenter(UserGarageFirebaseTokenContractor.View mView){
        this.mView = mView;
    }


    @Override
    public void postUserFirebaseToken(UserFirebaseTokenRequest userFirebaseTokenRequest) {
        new CustomRetroRequest().getBaseUrl().postUserFirebaseToken(userFirebaseTokenRequest).enqueue(new Callback<UserFirebaseTokenResponse>() {
            @Override
            public void onResponse(Call<UserFirebaseTokenResponse> call, Response<UserFirebaseTokenResponse> response) {
                if(response.isSuccessful()){
                    if(Integer.parseInt(response.body().getStatus()) == 1) {
                        mView.userFirebaseTokenSuccess(response.body());
                        Log.i("User Firebase Token","call");
                    }else {
                        mView.userFirebaseTokenFailure(response.body().getMessage());
                        Log.i("User Firebase Token","fail");

                    }
                }else{
                    mView.userFirebaseTokenFailure("Something went wrong");
                    Log.i("User Firebase Token","fail");

                }
            }

            @Override
            public void onFailure(Call<UserFirebaseTokenResponse> call, Throwable t) {
                mView.userFirebaseTokenFailure(t.getMessage());
                Log.i("User Firebase Token",""+t.getMessage());


            }
        });
    }

    @Override
    public void postGarageFirebaseToken(GarageFirebaseTokenRequest garageFirebaseTokenRequest) {
        new CustomRetroRequest().getBaseUrl().postGarageFirebaseToken(garageFirebaseTokenRequest).enqueue(new Callback<GarageFirebaseTokenResponse>() {
            @Override
            public void onResponse(Call<GarageFirebaseTokenResponse> call, Response<GarageFirebaseTokenResponse> response) {
                if(response.isSuccessful()){
                    if(Integer.parseInt(response.body().getStatus()) == 1) {
                        mView.garageFirebaseTokenSuccess(response.body());
                        Log.i("Garage Firebase Token","call");

                    }else {
                        mView.garageFirebaseTokenFailure(response.body().getMessage());
                        Log.i("Garage Firebase Token","fail");

                    }
                }else{
                    mView.garageFirebaseTokenFailure("Something went wrong");
                    Log.i("Garage Firebase Token","fail");

                }
            }

            @Override
            public void onFailure(Call<GarageFirebaseTokenResponse> call, Throwable t) {
                mView.garageFirebaseTokenFailure(t.getMessage());
                Log.i("Garage Firebase Token",""+t.getMessage());


            }
        });
    }
}
