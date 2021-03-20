package com.machon.machon.activity.signup.mechanic.garageType;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.machon.machon.R;
import com.machon.machon.adapter.SelectYourGarageTypeCheckBoxAdapter;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.GetVehicleTypeResponse;
import com.machon.machon.utility.Constants;
import com.machon.machon.utility.OnRecyclerViewClick;
import com.machon.machon.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitySelectGarageTypes extends AppCompatActivity implements OnRecyclerViewClick,MechanicGarageTypeContractor.View {


    ArrayList<String> selectedList=new ArrayList<>();
    private OnRecyclerViewClick onRecyclerViewClick = this;

    private List<LocationData> locationDataList = new ArrayList<>();
    List<GetVehicleTypeResponse.VehicleType> vehicleTypes=new ArrayList<>();
    SelectYourGarageTypeCheckBoxAdapter selectYourProblemCheckBoxAdapter;


    @BindView(R.id.select_garage_type_recycle_view)
    RecyclerView select_garageType_recycle_view;
    MechanicGarageTypePresenter garageTypePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_garage_types);
        ButterKnife.bind(this);

        garageTypePresenter=new MechanicGarageTypePresenter(this);
        garageTypes();
        setRecyclerView();
//        StudentDataPrepare();
    }


    @OnClick(R.id.btn_select_garage_type)
    void btn_select(){
        Intent intent=new Intent();
        intent.putStringArrayListExtra(Constants.SELECTED_GARAGE_TYPE_LIST,selectedList);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }


    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActivitySelectGarageTypes.this);
        select_garageType_recycle_view.setLayoutManager(linearLayoutManager);
    }

    private void StudentDataPrepare() {
        LocationData data = new LocationData("2 wheeler", 0);
        locationDataList.add(data);
        data = new LocationData("3 wheeler", 0);
        locationDataList.add(data);
        data = new LocationData("4 wheeler", 0);
        locationDataList.add(data);
        data = new LocationData("Big vehicles", 0);
        locationDataList.add(data);
        data = new LocationData("all vehicles", 0);
        locationDataList.add(data);
        selectYourProblemCheckBoxAdapter.notifyDataSetChanged();
    }

    void garageTypes(){
        Utility.getInstance().showProgressDialogue(this);
        garageTypePresenter.getMechanicGarageType();
    }


    @Override
    public void onReclerViewClick(View view, int position) {
//        selectedList.add(locationDataList.get(position).getName());
        if(vehicleTypes.get(position).isGarageTypeSelect()){
            selectedList.add(vehicleTypes.get(position).getVehicleType());
        }else{
            selectedList.remove(vehicleTypes.get(position).getVehicleType());
        }
    }

    @Override
    public void mechanicGarageTypeSuccess(GetVehicleTypeResponse response) {
//        String vehicletypes=response.getVehicleType();
//        vehicleTypes.add((GetVehicleTypeResponse.VehicleType) response.getVehicleType());
        Utility.getInstance().dismissProgress();

        vehicleTypes=response.getVehicleType();
        selectYourProblemCheckBoxAdapter=new SelectYourGarageTypeCheckBoxAdapter(ActivitySelectGarageTypes.this,vehicleTypes,onRecyclerViewClick);
        select_garageType_recycle_view.setAdapter(selectYourProblemCheckBoxAdapter);
        selectYourProblemCheckBoxAdapter.notifyDataSetChanged();

        Toast.makeText(this, ""+response.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mechanicGarageTypeFailure(String message) {
        Utility.getInstance().dismissProgress();

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }
}