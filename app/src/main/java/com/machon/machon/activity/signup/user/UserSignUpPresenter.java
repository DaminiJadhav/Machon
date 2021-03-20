package com.machon.machon.activity.signup.user;

import android.util.Log;

import com.machon.machon.lib.CustomRetroRequest;
import com.machon.machon.model.request.userRegistration.UserRegistrationRequest;
import com.machon.machon.model.response.userRegistration.UserRegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignUpPresenter implements UserSignUpContractor.Presenter {
    private UserSignUpContractor.View mview;

    UserSignUpPresenter(UserSignUpContractor.View view){
        this.mview=view;
    }

    @Override
    public void postUserRegistration(UserRegistrationRequest userRegistrationRequest) {
       new CustomRetroRequest().getBaseUrl().postUserRegistration(userRegistrationRequest).enqueue(new Callback<UserRegistrationResponse>() {
           @Override
           public void onResponse(Call<UserRegistrationResponse> call, Response<UserRegistrationResponse> response) {
               if(response.isSuccessful()){
                   if(Integer.parseInt(response.body().getStatus())==1){
                       Log.i("UserRegistration",""+response.body().getMessage());
                       mview.userRegistrationSuccess(response.body());
                   }else {
                       Log.i("UserRegistration failed",""+response.body().getMessage());

                       mview.userRegistrationFailure(response.body().getMessage());
                   }
               }else{
                   Log.i("UserRegistration failed",""+response.body().getMessage());

                   mview.userRegistrationFailure("Something went wrong");

               }
           }

           @Override
           public void onFailure(Call<UserRegistrationResponse> call, Throwable t) {
               mview.userRegistrationFailure(t.getMessage());

           }
       });
    }
}
