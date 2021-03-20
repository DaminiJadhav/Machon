package com.machon.machon.activity.signup.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.machon.machon.R;
import com.machon.machon.activity.home.userhome.ActivityHomePage;
import com.machon.machon.activity.login.ActivityLoginScreen;
import com.machon.machon.model.request.userRegistration.UserRegistrationRequest;
import com.machon.machon.model.response.userRegistration.UserRegistrationResponse;
import com.machon.machon.utility.CheckInternetConnection;
import com.machon.machon.utility.Constants;
import com.machon.machon.utility.CustomProgressDialog;
import com.machon.machon.utility.Utility;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityUserSignUpScreen extends AppCompatActivity implements UserSignUpContractor.View{

    private  UserSignUpPresenter userSignUpPresenter;
    EditText ed_firstName,ed_lastName,ed_middleName,ed_email,ed_password,ed_mobileNo,ed_dob;

     CustomProgressDialog loading_progress_dialog;
     ImageView iv_date;

    String mobilePattern = "^[7-9][0-9]{9}$";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up_screen);
        ButterKnife.bind(this);





//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        ed_firstName=findViewById(R.id.edittxt_firstName);
        ed_middleName=findViewById(R.id.edittxt_midName);
        ed_lastName=findViewById(R.id.edittxt_lastName);
        ed_email=findViewById(R.id.edittxt_emailId);
        ed_password=findViewById(R.id.edittxt_password);
        ed_mobileNo=findViewById(R.id.edittxt_mobileNumber);
        ed_dob=findViewById(R.id.edittxt_dob);
        iv_date=findViewById(R.id.iv_date_picker);

        Intent intent=getIntent();
        String mobileno=intent.getStringExtra(Constants.USERMOBILENUMBER);
        ed_mobileNo.setText(mobileno);
//        Toast.makeText(this, ""+mobileno, Toast.LENGTH_SHORT).show();




        userSignUpPresenter=new UserSignUpPresenter(this);

    }

    @OnClick(R.id.iv_date_picker)
    void date_picker(){
        final Calendar calendar= Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int month=i1+1;
                ed_dob.setText(i2+"-"+month+"-"+i);
            }
        },mYear,mMonth,mDay);
        datePickerDialog.show();

    }

    @OnClick(R.id.btn_user_sign_up)
    void user_sign_up() {
        if(validation()){

                if(CheckInternetConnection.getConnectivity(this)!=CheckInternetConnection.TYPE_NOT_CONNECTED){
                    UserRegistrationRequest userRegistrationRequest=new UserRegistrationRequest();
                    userRegistrationRequest.setFirstName(ed_firstName.getText().toString());
                    userRegistrationRequest.setMiddleName(ed_middleName.getText().toString());
                    userRegistrationRequest.setLastName(ed_lastName.getText().toString());
                    userRegistrationRequest.setEmail(ed_email.getText().toString());
                    userRegistrationRequest.setPassword(ed_password.getText().toString());
                    userRegistrationRequest.setMobNo(ed_mobileNo.getText().toString());
                    userRegistrationRequest.setDOB(ed_dob.getText().toString());
                    Utility.getInstance().showProgressDialogue(this);

                    userSignUpPresenter.postUserRegistration(userRegistrationRequest);

                }else {
                    Toast.makeText(this, ""+getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_SHORT).show();
                }

        }else {

        }

    }

    @Override
    public void userRegistrationSuccess(UserRegistrationResponse userRegistrationResponse) {
        Utility.getInstance().dismissProgress();
        if(Integer.parseInt(userRegistrationResponse.getStatus())==1) {
            Toast.makeText(this, "" + userRegistrationResponse.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ActivityLoginScreen.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "" + userRegistrationResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void userRegistrationFailure(String message) {
        Utility.getInstance().dismissProgress();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }



    private boolean validation(){
        if(ed_firstName.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_first_name), Toast.LENGTH_SHORT).show();
            return false;
        }
//        else if(ed_middleName.getText().toString().isEmpty()){
//            Toast.makeText(this, getResources().getString(R.string.please_enter_middle_name), Toast.LENGTH_SHORT).show();
//            return false;
//        }
        else if(ed_lastName.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_last_name), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_email.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_email_id), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!ed_email.getText().toString().matches(emailPattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_email_id), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_password.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_mobileNo.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_mobile_no), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!ed_mobileNo.getText().toString().matches(mobilePattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_mobile_no), Toast.LENGTH_SHORT).show();
            return false;
        }
//        else if(ed_dob.getText().toString().isEmpty()){
//            Toast.makeText(this, getResources().getString(R.string.please_enter_dob), Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

  }