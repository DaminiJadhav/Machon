package com.machon.machon.firebase;

import com.machon.machon.model.request.LoginMechanicRequest;
import com.machon.machon.model.request.LoginUserRequest;
import com.machon.machon.model.request.firebaseToken.GarageFirebaseTokenRequest;
import com.machon.machon.model.request.firebaseToken.UserFirebaseTokenRequest;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.model.response.firebaseToken.GarageFirebaseTokenResponse;
import com.machon.machon.model.response.firebaseToken.UserFirebaseTokenResponse;

public interface UserGarageFirebaseTokenContractor {


    interface Presenter{
        void postUserFirebaseToken(UserFirebaseTokenRequest userFirebaseTokenRequest);
        void postGarageFirebaseToken(GarageFirebaseTokenRequest garageFirebaseTokenRequest);
    }

    interface View{
        void userFirebaseTokenSuccess(UserFirebaseTokenResponse response);
        void userFirebaseTokenFailure(String message);
        void garageFirebaseTokenSuccess(GarageFirebaseTokenResponse response);
        void garageFirebaseTokenFailure(String message);
    }
}
