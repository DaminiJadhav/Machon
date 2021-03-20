package com.machon.machon.activity.verifyphonenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.machon.machon.R;
import com.machon.machon.activity.signup.mechanic.ActivityMechanicSignUp;
import com.machon.machon.activity.signup.user.ActivityUserSignUpScreen;
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

public class ActivityVerifyPhoneNumber extends AppCompatActivity implements VerificationContractor.View, OnCustomDialogueClick {
    private VerificationContractor.Presenter mPresenter;
    private OnCustomDialogueClick onCustomDialogueClick = this;
    private String otpResponse;
    private AppSession appSession;
    private String userSelected;
    private CustomDialogClass customDialogClass;
    String mobilePattern = "^[7-9][0-9]{9}$";


    @BindView(R.id.edittxt_mobileNumber)
    EditText edittxt_mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);
        ButterKnife.bind(this);
        mPresenter = new VerificationPresenter(this);

        appSession= AppSession.getInstance(this);
        userSelected=appSession.getUserSelection();
//        Toast.makeText(this, ""+userSelected, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_verify)
    void btn_verify() {

        if(validation()){
            if (CheckInternetConnection.getConnectivity(getApplicationContext()) == 1 || CheckInternetConnection.getConnectivity(getApplicationContext()) == 2) {
                if (!TextUtils.isEmpty(edittxt_mobileNumber.getText()) && Utility.getInstance().isContactValid(edittxt_mobileNumber.getText().toString().trim())) {
                    Utility.getInstance().showProgressDialogue(this);
                    if(userSelected.equalsIgnoreCase("User")){
                        mPresenter.sendVerficationCode(edittxt_mobileNumber.getText().toString().trim(),1,0);
                    }else {
                        mPresenter.sendVerficationCode(edittxt_mobileNumber.getText().toString().trim(),1,1);
                    }

                }
/*            CustomDialogClass customDialogClass = new CustomDialogClass(this);
            customDialogClass.show();*/
            }
        }

    }


    @Override
    public void sendVerficationCodeFail(String message) {
        Utility.getInstance().dismissProgress();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendVerficationCodeSuccess(VerificationResponse response) {
        otpResponse =  response.getOTP();
        if(otpResponse==null){
            Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "OTP send " + response.getOTP(), Toast.LENGTH_SHORT).show();
            Log.d("Otp send ",""+otpResponse);
            if(customDialogClass!=null){
                customDialogClass.dismiss();
            }
            customDialogClass = new CustomDialogClass(this,onCustomDialogueClick, edittxt_mobileNumber.getText().toString().trim());
            customDialogClass.show();

        }
//        Toast.makeText(this, "OTP send " + otpResponse, Toast.LENGTH_SHORT).show();


        Utility.getInstance().dismissProgress();
    }

    @Override
    public void onDialogueClick(EnumClicks where, String data) {
        if(where == EnumClicks.VERIFYOTP){
            if(!TextUtils.isEmpty(data)){
                if(data.equals(otpResponse)){
                    customDialogClass.dismiss();
                    Toast.makeText(this, "Verification Succefull", Toast.LENGTH_SHORT).show();
                    if(userSelected.equalsIgnoreCase("User")) {
                        Intent intent = new Intent(ActivityVerifyPhoneNumber.this, ActivityUserSignUpScreen.class);
                        intent.putExtra(Constants.USERMOBILENUMBER,edittxt_mobileNumber.getText().toString());
                        startActivity(intent);
                        finish();


                    }else {
                        Intent intent=new Intent(ActivityVerifyPhoneNumber.this, ActivityMechanicSignUp.class);
                        intent.putExtra(Constants.MECHANICMOBILENUMBER,edittxt_mobileNumber.getText().toString());

                        startActivity(intent);
                        finish();
                    }
                }else {
                    Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Please enter OTP.", Toast.LENGTH_SHORT).show();
            }
        }else if(where == EnumClicks.RESENDOTP){
            Utility.getInstance().showProgressDialogue(this);
            if(userSelected.equalsIgnoreCase("User")){
                mPresenter.sendVerficationCode(edittxt_mobileNumber.getText().toString().trim(),1,0);
            }else {
                mPresenter.sendVerficationCode(edittxt_mobileNumber.getText().toString().trim(),1,1);
            }
        }
    }



    private boolean validation() {
        if(!edittxt_mobileNumber.getText().toString().matches(mobilePattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_mobile_no), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}