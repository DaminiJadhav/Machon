package com.machon.machon.activity.issues;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.machon.machon.R;
import com.machon.machon.activity.issues.vehicleIssues.ActivitySelectYourProblemCheckBox;
import com.machon.machon.activity.location.ActivityLocationSelection;
import com.machon.machon.activity.signup.mechanic.garageType.MechanicGarageTypeContractor;
import com.machon.machon.activity.signup.mechanic.garageType.MechanicGarageTypePresenter;
import com.machon.machon.adapter.SelectedVehicleIssuesAdapter;
import com.machon.machon.model.request.PostGarageIssueRequest;
import com.machon.machon.model.response.GetVehicleTypeResponse;
import com.machon.machon.model.response.PostGarageIssueResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.Constants;
import com.machon.machon.utility.Utility;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityVehicleIssuesForm extends AppCompatActivity implements LocationListener, VehicleIssueContractor.View, MechanicGarageTypeContractor.View {

    AppSession appSession;
    String useId,mobileNumber;
    ArrayList<String> seletedlist = new ArrayList<>();
    ArrayList<String> problemid = new ArrayList<>();

    String mobilePattern = "^[7-9][0-9]{9}$";
    VechicleIssuePresenter vechicleIssuePresenter;
    MechanicGarageTypePresenter vehicleTypePresenter;

    String yourProblemText;

    SelectedVehicleIssuesAdapter selectYourProblemCheckBoxAdapter;

    Double latitude = 0.0;
    Double longitude = 0.0;
    LocationManager locationManager;
    Geocoder geocoder;
    List<Address> address;
    String currentAddres;
    String selectedVehicType;
    String selectedVehicTypeId;


    @BindView(R.id.rv_selected_problem)
    RecyclerView rv_selected_problem;

    @BindView(R.id.ediitxt_mobileNumber)
    EditText ed_mobileNo;
    @BindView(R.id.edittxt_vehicle_number)
    EditText ed_vehicle_number;
//    @BindView(R.id.edittxt_vehicle_type)
//    EditText ed_vehicle_type;
    @BindView(R.id.spinner_vehicle_type)
    Spinner spinner_vehicle_type;
//    @BindView(R.id.edittxt_garage_type)
//    EditText ed_garage_type;
    @BindView(R.id.edittxt_enter_your_problem)
    EditText ed_enter_your_problem;
    @BindView(R.id.edittxt_description)
    EditText ed_description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_issues_form);
        ButterKnife.bind(this);

        appSession=AppSession.getInstance(this);
        useId=appSession.getUserLogin().getResponse().getId();
        mobileNumber=appSession.getUserLogin().getResponse().getPhoneNumber();
        ed_mobileNo.setText(mobileNumber);
//        Toast.makeText(this, ""+mobileNumber, Toast.LENGTH_SHORT).show();
        vehicleTypePresenter=new MechanicGarageTypePresenter(this);

        vehhicleType();


        geocoder = new Geocoder(this, Locale.getDefault());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        vechicleIssuePresenter=new VechicleIssuePresenter(this);

        getLocation();

        setRecyclerView();
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActivityVehicleIssuesForm.this);
        rv_selected_problem.setLayoutManager(linearLayoutManager);
        selectYourProblemCheckBoxAdapter = new SelectedVehicleIssuesAdapter(ActivityVehicleIssuesForm.this, seletedlist);
        rv_selected_problem.setAdapter(selectYourProblemCheckBoxAdapter);
    }

    @OnClick(R.id.btn_submit)
    void submit_btn() {
//        Intent intent = new Intent(this, ActivityLocationSelection.class);
//        startActivity(intent);
        if (validation()){
            postVehicleIssue();

        }else {

        }

    }



    @OnClick(R.id.choose_problem)
    void choose_problem() {
        Intent intent = new Intent(this, ActivitySelectYourProblemCheckBox.class);
        startActivityForResult(intent, 2);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.SELECTEDCHECKBOXCODE) {

            seletedlist.clear();
            seletedlist.addAll(data.getStringArrayListExtra(Constants.ISSUE_SELECTED_LIST));
            problemid.clear();
            problemid.addAll(data.getStringArrayListExtra(Constants.ISSUE_ID));

            if(data.getStringExtra(Constants.OTHER_ISSUE_TEXT)!=null){
                yourProblemText=data.getStringExtra(Constants.OTHER_ISSUE_TEXT);
            }else {
                yourProblemText="";
            }


//            for (int i=0;i<seletedlist.size();i++){
//                if(seletedlist.get(i).equalsIgnoreCase("Others")){
//                    ed_enter_your_problem.setVisibility(View.VISIBLE);
//                }else{
//                    ed_enter_your_problem.setVisibility(View.GONE);
//
//                }
//            }

            selectYourProblemCheckBoxAdapter.notifyDataSetChanged();
            rv_selected_problem.setVisibility(View.VISIBLE);
        }

        }

    void vehhicleType(){
        Utility.getInstance().showProgressDialogue(this);
        vehicleTypePresenter.getMechanicGarageType();
    }

    private boolean validation(){
        if(!ed_mobileNo.getText().toString().matches(mobilePattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_mobile_no), Toast.LENGTH_SHORT).show();
            return false;
        }else if(ed_vehicle_number.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_vehicle_number), Toast.LENGTH_SHORT).show();
            return false;
        }else if(seletedlist.size()==0){
            Toast.makeText(this, getResources().getString(R.string.select_your_problem), Toast.LENGTH_SHORT).show();
            return false;

        }
//        else if(ed_enter_your_problem.getVisibility()==View.VISIBLE){
//            if(ed_enter_your_problem.getText().toString().isEmpty()){
//                Toast.makeText(this, getResources().getString(R.string.enter_your_problem), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }
//        else if(ed_description.getText().toString().isEmpty()){
//            Toast.makeText(this, getResources().getString(R.string.please_write_description), Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    private void getLocation() {

//        if (ActivityCompat.checkSelfPermission(ActivityVehicleIssuesForm.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityVehicleIssuesForm.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ActivityVehicleIssuesForm.this);

        if (ActivityCompat.checkSelfPermission(
                ActivityVehicleIssuesForm.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                ActivityVehicleIssuesForm.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (locationGPS != null) {
                latitude = locationGPS.getLatitude();
                longitude = locationGPS.getLongitude();
//                Toast.makeText(this, ""+latitude+","+longitude, Toast.LENGTH_SHORT).show();
                Log.d("Issue page Latitude",""+latitude);
                Log.d("Issue page Logitude",""+longitude);

//                addresses=geocoder.getFromLocation(lat,lon,1);

                if (ActivityCompat.checkSelfPermission(ActivityVehicleIssuesForm.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityVehicleIssuesForm.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ActivityVehicleIssuesForm.this);

            } else {

                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void postVehicleIssue(){
        PostGarageIssueRequest postGarageIssueRequest=new PostGarageIssueRequest();
        postGarageIssueRequest.setUserId(useId);
        postGarageIssueRequest.setUserLatitude(latitude.toString());
        postGarageIssueRequest.setUserLongitude(longitude.toString());
        postGarageIssueRequest.setUserAddress(currentAddres);
        postGarageIssueRequest.setVehicleNo(ed_vehicle_number.getText().toString());
        postGarageIssueRequest.setVehicleTypeId(selectedVehicTypeId);
        postGarageIssueRequest.setDescription(ed_description.getText().toString());
        postGarageIssueRequest.setOther(yourProblemText);

//        postGarageIssueRequest.setOther(ed_enter_your_problem.getText().toString());

        ArrayList<PostGarageIssueRequest.AssignProblemToUserDetailList> list = new ArrayList();



        for(int i=0;i<problemid.size();i++){
            PostGarageIssueRequest.AssignProblemToUserDetailList assignProblemToUserDetailList=new PostGarageIssueRequest.AssignProblemToUserDetailList();

            assignProblemToUserDetailList.setProblemId(problemid.get(i));
            assignProblemToUserDetailList.setAssignProblemToUserHeaderId("");

            list.add(assignProblemToUserDetailList);
        }

//        postGarageIssueRequest.setAssignProblemToUserDetailList((List<PostGarageIssueRequest.AssignProblemToUserDetailList>) assignProblemToUserDetailList);
        postGarageIssueRequest.setAssignProblemToUserDetailList(list);

        Utility.getInstance().showProgressDialogue(this);

//        Log.d("post garage request",""+postGarageIssueRequest.toString());
                Log.d("Issue user id",""+useId);
        Log.d("Issue user id",""+latitude.toString());
        Log.d("Issue user id",""+longitude.toString());
        Log.d("Issue user id",""+currentAddres);
//        Log.d("Issue user id",""+useId);
//        Log.d("Issue user id",""+useId);


        vechicleIssuePresenter.postVehicleIssue(postGarageIssueRequest);


//        assignProblemToUserDetailLists.


    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//
//        Toast.makeText(this, ""+latitude, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+longitude, Toast.LENGTH_SHORT).show();
//
//        try {
//            address =geocoder.getFromLocation(latitude,longitude,1);
//            currentAddres=address.get(0).getAddressLine(0);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void vehicleIssueSuccess(PostGarageIssueResponse postGarageIssueResponse) {
//        Toast.makeText(this, ""+postGarageIssueResponse.getMessage(), Toast.LENGTH_SHORT).show();
        Utility.getInstance().dismissProgress();
        Log.d("Issue response",""+postGarageIssueResponse.getMessage());

        //        Toast.makeText(this, ""+postGarageIssueResponse.getMessage(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+yourProblemText, Toast.LENGTH_SHORT).show();

        List<PostGarageIssueResponse.GarageList> garageLists=new ArrayList<>();
        garageLists=postGarageIssueResponse.getGarageList();
        Intent intent = new Intent(this, ActivityLocationSelection.class);
        appSession.setGarageList(postGarageIssueResponse);
        startActivity(intent);
    }

    @Override
    public void vehicleIssueFailure(String message) {
        Utility.getInstance().dismissProgress();
        Log.d("Issue Fail",""+message);

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void mechanicGarageTypeSuccess(GetVehicleTypeResponse response) {
        Utility.getInstance().dismissProgress();
        List<String> listvehicle=new ArrayList<>();
        List<String> listvehicleid=new ArrayList<>();

        for (int i=0;i<response.getVehicleType().size();i++){
            listvehicle.add(response.getVehicleType().get(i).getVehicleType());
            listvehicleid.add(response.getVehicleType().get(i).getVehicleTypeId());

        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,listvehicle);
        spinner_vehicle_type.setAdapter(adapter);

        spinner_vehicle_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedVehicType=adapterView.getSelectedItem().toString();
                selectedVehicTypeId=listvehicleid.get(i);

//                Toast.makeText(ActivityVehicleIssuesForm.this, ""+selectedVehicTypeId, Toast.LENGTH_SHORT).show();
//                Toast.makeText(ActivityVehicleIssuesForm.this, ""+adapterView.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void mechanicGarageTypeFailure(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        Utility.getInstance().dismissProgress();

    }
}