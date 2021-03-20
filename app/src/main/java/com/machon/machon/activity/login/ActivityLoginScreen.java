package com.machon.machon.activity.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.tasks.Task;
import com.machon.machon.MainActivity;
import com.machon.machon.R;
import com.machon.machon.activity.forgetPassword.ActivityForgetPassword;
import com.machon.machon.activity.home.mechanichome.ActivityMechanicHomePage;
import com.machon.machon.activity.home.userhome.ActivityHomePage;
import com.machon.machon.activity.signup.mechanic.ActivityMechanicSignUp;
import com.machon.machon.activity.signup.user.ActivityUserSignUpScreen;
import com.machon.machon.activity.verifyphonenumber.ActivityVerifyPhoneNumber;
import com.machon.machon.activity.verifyphonenumber.VerificationContractor;
import com.machon.machon.activity.verifyphonenumber.VerificationPresenter;
import com.machon.machon.model.request.LoginMechanicRequest;
import com.machon.machon.model.request.LoginUserRequest;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.model.response.VerificationResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.CheckInternetConnection;
import com.machon.machon.utility.Constants;
import com.machon.machon.utility.CustomDialogClass;
import com.machon.machon.utility.EnumClicks;
import com.machon.machon.utility.OnCustomDialogueClick;
import com.machon.machon.utility.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLoginScreen extends AppCompatActivity implements LoginContractor.View, OnCustomDialogueClick, VerificationContractor.View {
    private LoginPresenter mPresenter;
    private OnCustomDialogueClick onCustomDialogueClick = this::onDialogueClick;
    AppSession appSession;
    String userSelected,token;


    VerificationPresenter verificationPresenter;
    @BindView(R.id.edittxt_username)
    EditText edittxt_username;
    @BindView(R.id.edittxt_password)
    EditText edittxt_password;
    @BindView(R.id.iv_hideshowpass)
    ImageView iv_pass;
    @BindView(R.id.google_sign_in)
    SignInButton signInButton;

    private  GoogleApiClient googleApiClient;
    GoogleSignInClient googleSignInClient;
    private static final int SIGN_IN=1;

    String mobilePattern = "^[7-9][0-9]{9}$";
    String otp;
    private CustomDialogClass customDialogClass;


    @OnClick(R.id.txt_signup)
    void sign_up_button() {
        Intent intent = new Intent(this, ActivityVerifyPhoneNumber.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.txt_forget_password)
    void forget_password_btn() {
        if(!edittxt_username.getText().toString().matches(mobilePattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_mobile_no), Toast.LENGTH_SHORT).show();
        }else{
            if(userSelected.equalsIgnoreCase("User")){
                verificationPresenter.sendVerficationCode(edittxt_username.getText().toString(),0,0);
            }else {
                verificationPresenter.sendVerficationCode(edittxt_username.getText().toString(),0,1);
            }
        }

    }

    @OnClick(R.id.iv_hideshowpass)
    void show_hide_password(){
        ShowHidePass();
    }

    @OnClick(R.id.button_login)
    void button_login() {

        if(validation()){
            if(CheckInternetConnection.getConnectivity(this)!=CheckInternetConnection.TYPE_NOT_CONNECTED){
                if(userSelected.equalsIgnoreCase("User")){
                    LoginUserRequest loginUserRequest = new LoginUserRequest();
                    loginUserRequest.setMobNo(edittxt_username.getText().toString().trim());
                    loginUserRequest.setPassword(edittxt_password.getText().toString().trim());
                    loginUserRequest.setFCMTocken(token);

                    Utility.getInstance().showProgressDialogue(this);
                    mPresenter.postLogin(loginUserRequest);
                    Utility.hideKeyboard(this);

                }else{
                    LoginMechanicRequest loginMechanicRequest=new LoginMechanicRequest();
                    loginMechanicRequest.setMobNo(edittxt_username.getText().toString().trim());
                    loginMechanicRequest.setPassword(edittxt_password.getText().toString().trim());
                    loginMechanicRequest.setFCMTocken(token);
                    Utility.getInstance().showProgressDialogue(this);
                    mPresenter.postMechanicLogin(loginMechanicRequest);
                    Utility.hideKeyboard(this);

                }

            }else{
                Toast.makeText(this, ""+getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        verificationPresenter=new VerificationPresenter(this);


        user_google_sign_in();

        appSession=AppSession.getInstance(this);
        userSelected=appSession.getUserSelection();
        token=appSession.getFirebaseToken();
//        Toast.makeText(this, ""+token, Toast.LENGTH_SHORT).show();


    }


    private boolean validation() {
        if(!edittxt_username.getText().toString().matches(mobilePattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_mobile_no), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(edittxt_password.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



        @Override
    public void loginSuccess(LoginResponse response) {
        Utility.getInstance().dismissProgress();
        if(Integer.parseInt(response.getStatus())==1){
            Toast.makeText(this,""+response.getMessage(), Toast.LENGTH_SHORT).show();
            appSession.setUserLogin(response);
            Intent intent = new Intent(this, ActivityHomePage.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,""+response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginFailure(String message) {
        Utility.getInstance().dismissProgress();
        Toast.makeText(this,""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginMechanicSuccess(LoginMechanicResponse response) {
        Utility.getInstance().dismissProgress();
        if(Integer.parseInt(response.getStatus())==1){
            Toast.makeText(this,""+response.getMessage(), Toast.LENGTH_SHORT).show();
            appSession.setMechanicLogin(response);
            Intent intent = new Intent(this, ActivityMechanicHomePage.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,""+response.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void loginMechanicFailure(String message) {
        Utility.getInstance().dismissProgress();

        Toast.makeText(this,""+message, Toast.LENGTH_SHORT).show();

    }


    public void ShowHidePass() {

            if(edittxt_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                iv_pass.setImageResource(R.drawable.ic_hide);
                //Show Password
                edittxt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                iv_pass.setImageResource(R.drawable.ic_remove_red_eye_24);
                //Hide Password
                edittxt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
    }



    void user_google_sign_in(){
        GoogleSignInOptions googleSignInOptions =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions);

    }


    @OnClick(R.id.google_sign_in)
    void google_sign_in_btn(){
        Intent signInIntent=googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            GoogleSignInAccount gmailAccount=GoogleSignIn.getLastSignedInAccount(this);
//            if(gmailAccount!=null){
//                String personId=gmailAccount.getId();
//                String personEmail=gmailAccount.getEmail();
//                Log.i("Gmail account detail",""+personId+""+personEmail);
//                Toast.makeText(this, ""+personId+","+personEmail, Toast.LENGTH_SHORT).show();
//
//            }

            // Signed in successfully, show authenticated UI.
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode()+"  "+e.getLocalizedMessage());
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
//        GoogleSignInAccount googleSignInAccount=GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    public void onDialogueClick(EnumClicks where, String data) {
        if(where == EnumClicks.VERIFYOTP){
            if(!TextUtils.isEmpty(data)){
                if(data.equals(otp)){
                    customDialogClass.dismiss();
                    appSession= AppSession.getInstance(this);
                    userSelected=appSession.getUserSelection();
                    Toast.makeText(this, "Verification Succefull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityLoginScreen.this, ActivityForgetPassword.class);
                    intent.putExtra(Constants.FORGETPASSWORD,edittxt_username.getText().toString());
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Please enter OTP.", Toast.LENGTH_SHORT).show();
            }
        }else if(where == EnumClicks.RESENDOTP){
            Utility.getInstance().showProgressDialogue(this);
//            verificationPresenter.sendVerficationCode(edittxt_username.getText().toString().trim(),1);
            if(userSelected.equalsIgnoreCase("User")){
                verificationPresenter.sendVerficationCode(edittxt_username.getText().toString().trim(),0,0);
            }else {
                verificationPresenter.sendVerficationCode(edittxt_username.getText().toString().trim(),0,1);
            }


        }
    }


    @Override
    public void sendVerficationCodeFail(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendVerficationCodeSuccess(VerificationResponse response) {
        otp =  response.getOTP();
        Toast.makeText(this, "OTP send " + otp, Toast.LENGTH_SHORT).show();
        if(customDialogClass!=null){
            customDialogClass.dismiss();
        }
        customDialogClass = new CustomDialogClass(this,onCustomDialogueClick, edittxt_username.getText().toString().trim());
        customDialogClass.show();
        Utility.getInstance().dismissProgress();

    }
}