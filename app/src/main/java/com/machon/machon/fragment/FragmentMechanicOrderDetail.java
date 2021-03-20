package com.machon.machon.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.machon.machon.R;
import com.machon.machon.activity.home.userhome.ActivityHomePage;
import com.machon.machon.adapter.MechanicOrderDetailAdapter;
import com.machon.machon.adapter.UserOrderDetailAdapter;
import com.machon.machon.fragment.garageUserVehiclRequest.GarageVehicleIssueRequestPresenter;
import com.machon.machon.fragment.garageUserVehicleAcceptDetail.GarageUserVehicleAcceptDetailContractor;
import com.machon.machon.fragment.garageUserVehicleAcceptDetail.GarageUserVehicleAcceptDetailPresenter;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.GarageVehicleIssueRequestResponse;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.utility.AppSession;

import java.util.ArrayList;
import java.util.List;

public class FragmentMechanicOrderDetail extends Fragment implements GarageUserVehicleAcceptDetailContractor.View {
    RecyclerView rv_mechanic_order_detail;
    private List<LocationData> locationDataList = new ArrayList<>();
    MechanicOrderDetailAdapter mechanicOrderDetailAdapter;
    View view;

    AppSession appSession;
    LoginMechanicResponse loginMechanicResponse;
    String userId,garageId,requestId;



    GarageUserVehicleAcceptDetailPresenter garageUserVehicleAcceptDetailPresenter;
    List<GarageVehicleIssueRequestResponse.UserGarageIssueRequest> userGarageIssueRequests=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_mechanic_order_detail,container,false);
        rv_mechanic_order_detail=view.findViewById(R.id.mechanic_order_recycle_view);

        appSession=AppSession.getInstance(getContext());
        loginMechanicResponse=appSession.getMechanicLogin();

        garageUserVehicleAcceptDetailPresenter=new GarageUserVehicleAcceptDetailPresenter(this);


        garageId=loginMechanicResponse.getGarage().getGarageRegistrationId();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_mechanic_order_detail.setLayoutManager(linearLayoutManager);

        garageUserVehicleAcceptDetailPresenter.getGarageVehicleIssuesRequest(garageId);

//        StudentDataPrepare();

        return view;
    }

    private void StudentDataPrepare() {
        LocationData data = new LocationData("Nigdi", 25);
        locationDataList.add(data);
        data = new LocationData("Baner", 25);
        locationDataList.add(data);
        data = new LocationData("pune", 20);
        locationDataList.add(data);
        data = new LocationData("akurdi", 28);
        locationDataList.add(data);
        data = new LocationData("chinchwad", 15);
        locationDataList.add(data);
        data = new LocationData("katraj", 19);
        locationDataList.add(data);
        data = new LocationData("satara", 52);
        locationDataList.add(data);

//        mechanicOrderDetailAdapter=new MechanicOrderDetailAdapter(getContext(),locationDataList);
//        rv_mechanic_order_detail.setAdapter(mechanicOrderDetailAdapter);

    }

    @Override
    public void getGarageVehicleIssuesRequestResponseSuccess(GarageVehicleIssueRequestResponse garageVehicleIssueRequestResponse) {
        Log.i("Garage Order Detail","");
        userGarageIssueRequests=garageVehicleIssueRequestResponse.getUserGarageIssueRequests();
        mechanicOrderDetailAdapter=new MechanicOrderDetailAdapter(getContext(),userGarageIssueRequests);
        rv_mechanic_order_detail.setAdapter(mechanicOrderDetailAdapter);
//        Toast.makeText(getContext(), ""+garageVehicleIssueRequestResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getGarageVehicleIssuesRequestResponseFailure(String message) {
        Log.i("Garage Order Detail","");

        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }
}
