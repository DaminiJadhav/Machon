package com.machon.machon.lib;

import com.machon.machon.model.request.ForgetPasswordRequest;
import com.machon.machon.model.request.LoginMechanicRequest;
import com.machon.machon.model.request.LoginUserRequest;
import com.machon.machon.model.request.PostGarageIssueRequest;
import com.machon.machon.model.request.acceptVehicleIssueRequest.UserGarageRequestAcceptRequest;
import com.machon.machon.model.request.firebaseToken.GarageFirebaseTokenRequest;
import com.machon.machon.model.request.firebaseToken.UserFirebaseTokenRequest;
import com.machon.machon.model.request.mechanicRegistration.MechanicRegistrationRequest;
import com.machon.machon.model.request.userRegistration.UserRegistrationRequest;
import com.machon.machon.model.response.ForgetPasswordResponse;
import com.machon.machon.model.response.GarageVehicleIssueRequestResponse;
import com.machon.machon.model.response.GetUserRequestAcceptListResponse;
import com.machon.machon.model.response.GetVehicleProblemResponse;
import com.machon.machon.model.response.GetVehicleTypeResponse;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.model.response.MechanicRegistrationResponse;
import com.machon.machon.model.response.PostGarageIssueResponse;
import com.machon.machon.model.response.VerificationResponse;
import com.machon.machon.model.response.acceptVehicleIssue.UserGarageRequestAcceptResponse;
import com.machon.machon.model.response.firebaseToken.GarageFirebaseTokenResponse;
import com.machon.machon.model.response.firebaseToken.UserFirebaseTokenResponse;
import com.machon.machon.model.response.userRegistration.UserRegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {

    //http://mechon.sdaemon.com//api/Login/UserLogin
    @POST("Login/UserLogin")
    Call<LoginResponse> postLogin(@Body LoginUserRequest loginUserRequest);

    @POST("Registration/UserRegistration")
    Call<UserRegistrationResponse> postUserRegistration(@Body UserRegistrationRequest userRegistrationRequest);

    //https://mechon.sdaemon.com/api/OTP/OTPVerification?MobNo=9657431432&Email

//    https://mechon.sdaemon.com/api/OTP/OTPVerification?MobNo=9657431432&Email&flag=1&Roleflag=0
  //  @FormUrlEncoded
//    flag =1 for New Password
//    flag=0 for Forget Password
//    Roleflag=0  for  User
//    Roleflag=1  for  Garage
    @POST("OTP/OTPVerification")
    Call<VerificationResponse> postVerification(@Query("MobNo") String MobNo, @Query("Email") String email, @Query("flag") int flag,@Query("Roleflag") int roleflag);


    @POST("GarageRegistration/GarageRegistration")
    Call<MechanicRegistrationResponse> postMechanicRegistration(@Body MechanicRegistrationRequest userRegistrationRequest);

    @POST("GarageLogin/Login")
    Call<LoginMechanicResponse> postMechanicLogin(@Body LoginMechanicRequest loginMechanicRequest);

    @GET("VehicleType/GetVehicleType")
    Call<GetVehicleTypeResponse> getMechanicgarages();

    @POST("UserGarageIssueRequest/PostVehicleIssue")
    Call<PostGarageIssueResponse> postVehicleIssue(@Body PostGarageIssueRequest postGarageIssueRequest);

    @GET("Problem/GetProblem")
    Call<GetVehicleProblemResponse> getVehicleProblemList();


//    Flag=1 for User
//    Flag=0 for Garage
    @POST("ChangePassword/ForgetPassword")
    Call<ForgetPasswordResponse> postForgetPassword(@Body ForgetPasswordRequest forgetPasswordRequest);


    @GET("UserGarageIssueRequest/GetUserGarageIssueRequest")
    Call<GarageVehicleIssueRequestResponse> getGarageUserVehicleIssue(@Query("garageId") String garageId);


    @POST("GarageRequestAccept/PostGarageRequestAccept")
    Call<UserGarageRequestAcceptResponse> postGarageAcceptRequest(@Body UserGarageRequestAcceptRequest userGarageRequestAcceptRequest);

    @GET("GarageRequestAccept/GetUserRequestAccept")
    Call<GetUserRequestAcceptListResponse> getUserIssueRequestList(@Query("userId") String userId);


//    update firebase token

    @POST("FireBaseTockenUser/UserFireBaseTocken")
    Call<UserFirebaseTokenResponse> postUserFirebaseToken(@Body UserFirebaseTokenRequest userFirebaseTokenRequest);

    @POST("FireBaseTockenGarage/GarageFireBaseTocken")
    Call<GarageFirebaseTokenResponse> postGarageFirebaseToken(@Body GarageFirebaseTokenRequest garageFirebaseTokenRequest);




}
