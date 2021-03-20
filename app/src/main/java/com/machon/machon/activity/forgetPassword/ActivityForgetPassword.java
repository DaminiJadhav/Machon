package com.machon.machon.activity.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.machon.machon.R;
import com.machon.machon.activity.login.ActivityLoginScreen;
import com.machon.machon.model.request.ForgetPasswordRequest;
import com.machon.machon.model.response.ForgetPasswordResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.CheckInternetConnection;
import com.machon.machon.utility.Constants;
import com.machon.machon.utility.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityForgetPassword extends AppCompatActivity implements ForgetPasswordContractor.View {

    @BindView(R.id.edittxt_newPassword)
    EditText ed_newpassword;

    @BindView(R.id.edittxt_confirmPassword)
    EditText ed_confirmpassword;

    String newpassword,confirmpassword,mobileNumber;

    ForgetPasswordPresenter forgetPasswordPresenter;
    AppSession appSession;
    String userSelected;
    ForgetPasswordRequest forgetPasswordRequest;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

        appSession=AppSession.getInstance(this);
        userSelected=appSession.getUserSelection();

        forgetPasswordPresenter=new ForgetPasswordPresenter(this);
        forgetPasswordRequest=new ForgetPasswordRequest();


        Intent intent=getIntent();
        mobileNumber=intent.getStringExtra(Constants.FORGETPASSWORD);


    }


    @OnClick(R.id.btn_save)
    void save_password(){

        if(validation()){
            if(CheckInternetConnection.getConnectivity(this)!=CheckInternetConnection.TYPE_NOT_CONNECTED){
                if(userSelected.equalsIgnoreCase("User")){
                    forgetPasswordRequest.setMobNo(mobileNumber);
                    forgetPasswordRequest.setPassword(ed_confirmpassword.getText().toString());
                    forgetPasswordRequest.setFlag(1);
                    forgetPasswordPresenter.postForgetPassword(forgetPasswordRequest);
                    Utility.getInstance().showProgressDialogue(this);
                    Utility.hideKeyboard(this);

                }else{
                    forgetPasswordRequest.setMobNo(mobileNumber);
                    forgetPasswordRequest.setPassword(ed_confirmpassword.getText().toString());
                    forgetPasswordRequest.setFlag(0);
                    forgetPasswordPresenter.postForgetPassword(forgetPasswordRequest);
                    Utility.getInstance().showProgressDialogue(this);
                    Utility.hideKeyboard(this);
                }

            }else{
                Toast.makeText(this, ""+getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_SHORT).show();
            }

        }else {

        }
    }



    private boolean validation() {
        newpassword=ed_newpassword.getText().toString();
        confirmpassword=ed_confirmpassword.getText().toString();


        if(ed_newpassword.getText().toString().isEmpty()){
//            ed_newpassword.setError(getResources().getString(R.string.please_enter_new_password));
            Toast.makeText(this, getResources().getString(R.string.please_enter_new_password), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_newpassword.getText().length()<=5){
//            ed_newpassword.setError(getResources().getString(R.string.please_enter_new_password));
            Toast.makeText(this, getResources().getString(R.string.enter_at_least_6_digit), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(ed_confirmpassword.getText().toString().isEmpty()){
//            ed_confirmpassword.setError(getResources().getString(R.string.please_enter_confirm_password));
            Toast.makeText(this, getResources().getString(R.string.please_enter_confirm_password), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!newpassword.equals(confirmpassword)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_same_password), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void postForgetPasswordSuccess(ForgetPasswordResponse forgetPasswordResponse) {
        Utility.getInstance().dismissProgress();

        Toast.makeText(this, ""+forgetPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ActivityForgetPassword.this, ActivityLoginScreen.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void postForgetPasswordFailure(String message) {
        Utility.getInstance().dismissProgress();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}