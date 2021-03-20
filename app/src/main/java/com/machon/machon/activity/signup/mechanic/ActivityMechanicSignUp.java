package com.machon.machon.activity.signup.mechanic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.machon.machon.R;
import com.machon.machon.activity.location.mechanic.ActivityGarageLocationSelection;
import com.machon.machon.activity.login.ActivityLoginScreen;
import com.machon.machon.activity.signup.mechanic.garageType.ActivitySelectGarageTypes;
import com.machon.machon.adapter.SelectedGarageTypeAdapter;
import com.machon.machon.model.request.mechanicRegistration.MechanicRegistrationRequest;
import com.machon.machon.model.response.MechanicRegistrationResponse;
import com.machon.machon.utility.CheckInternetConnection;
import com.machon.machon.utility.Constants;
import com.machon.machon.utility.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMechanicSignUp extends AppCompatActivity implements MechanicSignUpContractor.View{
    private MechanicSignUpPresenter mPresenter;
    ArrayList<String> seletedlist = new ArrayList<>();

    SelectedGarageTypeAdapter selectedGarageTypeAdapter;


    String garageLocation;
    String mobilePattern = "^[7-9][0-9]{9}$";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    double latitude;
    double longitude;


    @BindView(R.id.edittxt_garriage_name)
    EditText ed_garage_name;
    @BindView(R.id.edittxt_person_name)
    EditText ed_person_name;
    @BindView(R.id.edittxt_contatc_no)
    EditText ed_contact_no;
    @BindView(R.id.edittxt_emailId)
    EditText ed_email;
    @BindView(R.id.edittxt_address)
    EditText ed_address;
    @BindView(R.id.edittxt_password)
    EditText ed_password;
    @BindView(R.id.edittxt_no_of_mechanic)
    EditText ed_no_of_mechanic;
    @BindView(R.id.edittxt_mechanic_name)
    EditText ed_mechanic_name;
    @BindView(R.id.edittxt_mechanic_number)
    EditText ed_mechanic_number;
    @BindView(R.id.edittxt_bank_name)
    EditText ed_bank_name;

    @BindView(R.id.edittxt_account_no)
    EditText ed_account_no;
    @BindView(R.id.edittxt_ifsc_code)
    EditText ed_ifsc_code;
    @BindView(R.id.edittxt_paymentId)
    EditText ed_payment_id;
    @BindView(R.id.edittxt_paymentNo)
    EditText ed_payment_no;


    @BindView(R.id.rv_selected_garage_type)
    RecyclerView rv_selected_garage_type;

    @OnClick(R.id.choose_garage_type)
    void choose_problem() {
        Intent intent = new Intent(this, ActivitySelectGarageTypes.class);
        startActivityForResult(intent, Constants.SELECTEDGARAGETYPECODE);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_sign_up);
        ButterKnife.bind(this);

        mPresenter = new MechanicSignUpPresenter(this);

        Intent intent=getIntent();
        String mobileno=intent.getStringExtra(Constants.MECHANICMOBILENUMBER);
        ed_contact_no.setText(mobileno);

        setRecyclerView();

    }


    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActivityMechanicSignUp.this);
        rv_selected_garage_type.setLayoutManager(linearLayoutManager);
        selectedGarageTypeAdapter = new SelectedGarageTypeAdapter(ActivityMechanicSignUp.this, seletedlist);
        rv_selected_garage_type.setAdapter(selectedGarageTypeAdapter);
    }

    @OnClick(R.id.btn_mechanic_sign_up)
    void mechanic_sign_up_btn(){

        if(validation()){
            if(CheckInternetConnection.getConnectivity(this)!=CheckInternetConnection.TYPE_NOT_CONNECTED){
                MechanicRegistrationRequest mechanicRegistrationRequest=new MechanicRegistrationRequest();
                mechanicRegistrationRequest.setGarageName(ed_garage_name.getText().toString());
                mechanicRegistrationRequest.setGarageOwnerName(ed_person_name.getText().toString());
                mechanicRegistrationRequest.setGarageOwnerMobNo(ed_contact_no.getText().toString());
                mechanicRegistrationRequest.setGarageOwnerEmail(ed_email.getText().toString());
                mechanicRegistrationRequest.setPassword(ed_password.getText().toString());
                mechanicRegistrationRequest.setGarageAddress(ed_address.getText().toString());
                mechanicRegistrationRequest.setGarageLatitude(latitude);
                mechanicRegistrationRequest.setGarageLongitude(longitude);
                mechanicRegistrationRequest.setNoOfMechanics(ed_no_of_mechanic.getText().toString());

                mechanicRegistrationRequest.setHeadMechanicName(ed_mechanic_name.getText().toString());
                mechanicRegistrationRequest.setHeadMechanicMobNo(ed_mechanic_number.getText().toString());
                mechanicRegistrationRequest.setBankName(ed_bank_name.getText().toString());
                mechanicRegistrationRequest.setAccountNo(ed_account_no.getText().toString());
                mechanicRegistrationRequest.setIFSCCode(ed_ifsc_code.getText().toString());
                mechanicRegistrationRequest.setUPIPaymentId(ed_payment_id.getText().toString());
                mechanicRegistrationRequest.setUPIPaymentNo(ed_payment_no.getText().toString());

                Utility.getInstance().showProgressDialogue(this);

                mPresenter.postMechanicSignUp(mechanicRegistrationRequest);


            }else {
                Toast.makeText(this, ""+getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_SHORT).show();
            }

        }else{
        }

    }

    @OnClick(R.id.edittxt_address)
    void garage_location(){
        Intent intent=new Intent(this, ActivityGarageLocationSelection.class);
        intent.putExtra(Constants.GARAGE_LOCATION_KEY,garageLocation);
        startActivityForResult(intent,Constants.GARAGELOCATIONSELECTION);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== Constants.GARAGELOCATIONSELECTION){
            if(data!=null){
                garageLocation=data.getExtras().getString(Constants.SELECTED_GARARAGE_LOC);
                ed_address.setText(garageLocation);
                double lat=data.getExtras().getDouble(Constants.SELECTED_LATITUDE);
                double lon=data.getExtras().getDouble(Constants.SELECTED_LONGITUDE);
                latitude=lat;
                longitude=lon;

//                Toast.makeText(this, ""+lat+","+lon, Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.SELECTEDGARAGETYPECODE) {
            seletedlist.clear();
            seletedlist.addAll(data.getStringArrayListExtra(Constants.SELECTED_GARAGE_TYPE_LIST));
            selectedGarageTypeAdapter.notifyDataSetChanged();
            rv_selected_garage_type.setVisibility(View.VISIBLE);
        }
    }



    private boolean validation(){
        if(ed_garage_name.getText().toString().isEmpty()){
//            ed_garage_name.requestFocus();
            Toast.makeText(this, getResources().getString(R.string.please_enter_garage_name), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_person_name.getText().toString().isEmpty()){
            ed_person_name.requestFocus();
            Toast.makeText(this, getResources().getString(R.string.please_enter_person_name), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!ed_contact_no.getText().toString().matches(mobilePattern)){
//            ed_contact_no.hasFocusable();
            Toast.makeText(this, getResources().getString(R.string.please_enter_contact_no), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!ed_email.getText().toString().matches(emailPattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_email_id), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(ed_password.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(ed_address.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_address), Toast.LENGTH_SHORT).show();
            return false;
        }
//        else if(ed_no_of_mechanic.getText().toString().isEmpty()){
//            Toast.makeText(this, getResources().getString(R.string.please_enter_no_of_mechanic), Toast.LENGTH_SHORT).show();
//            return false;
//        }
        else if(ed_mechanic_name.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_mechanic_name), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!ed_mechanic_number.getText().toString().matches(mobilePattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_mechanic_number), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_bank_name.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_bank_name), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_account_no.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_account_no), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_ifsc_code.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_ifsc_code), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_payment_id.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_payment_id), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_payment_no.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_payment_no), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void signUpSuccess(MechanicRegistrationResponse mechanicRegistrationResponse) {
        Utility.getInstance().dismissProgress();
        if(Integer.parseInt(mechanicRegistrationResponse.getStatus())==1){
            Toast.makeText(this,""+mechanicRegistrationResponse.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(ActivityMechanicSignUp.this, ActivityLoginScreen.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,""+mechanicRegistrationResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void signUpFailure(String message) {
        Utility.getInstance().dismissProgress();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }
}

