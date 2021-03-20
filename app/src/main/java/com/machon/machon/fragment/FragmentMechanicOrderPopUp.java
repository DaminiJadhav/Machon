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
import com.machon.machon.adapter.MechanicOrderDetailAdapter;
import com.machon.machon.adapter.MechanicOrderPopUpAdapter;
import com.machon.machon.fragment.garageUserVehiclRequest.GarageVehicleIssueRequestContractor;
import com.machon.machon.fragment.garageUserVehiclRequest.GarageVehicleIssueRequestPresenter;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.request.LoginUserRequest;
import com.machon.machon.model.request.acceptVehicleIssueRequest.UserGarageRequestAcceptRequest;
import com.machon.machon.model.response.GarageVehicleIssueRequestResponse;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.model.response.acceptVehicleIssue.UserGarageRequestAcceptResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.OnRecyclerViewClick;
import com.machon.machon.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class FragmentMechanicOrderPopUp extends Fragment implements GarageVehicleIssueRequestContractor.View , OnRecyclerViewClick {
    RecyclerView rv_mechanic_order_popup;
    private List<LocationData> locationDataList = new ArrayList<>();
    MechanicOrderPopUpAdapter mechanicOrderPopUpAdapter;
    View view;
    AppSession appSession;
    LoginMechanicResponse loginMechanicResponse;
    LoginResponse loginResponse;
    String userId,garageId,requestId;

    private OnRecyclerViewClick onRecyclerViewClick = this;

    GarageVehicleIssueRequestPresenter garageVehicleIssueRequestPresenter;
    List<GarageVehicleIssueRequestResponse.UserGarageIssueRequest> userGarageIssueRequests=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mechanic_order_pop_up,container,false);
        rv_mechanic_order_popup=view.findViewById(R.id.mechanic_order_popup_recycle_view);

        appSession=AppSession.getInstance(getContext());
        loginMechanicResponse=appSession.getMechanicLogin();
        loginResponse=appSession.getUserLogin();

        garageId=loginMechanicResponse.getGarage().getGarageRegistrationId();
//        userId=loginResponse.getResponse().getId();


//        if(loginMechanicResponse!=null){
//            Toast.makeText(getContext(), ""+loginMechanicResponse.getGarage().getGarageRegistrationId(), Toast.LENGTH_SHORT).show();
//        }

        garageVehicleIssueRequestPresenter=new GarageVehicleIssueRequestPresenter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_mechanic_order_popup.setLayoutManager(linearLayoutManager);


//        garageVehicleIssueRequestPresenter.getGarageVehicleIssuesRequest(garageId);

        StudentDataPrepare();

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

//        Utility.getInstance().showProgressDialogue(getContext());

        mechanicOrderPopUpAdapter=new MechanicOrderPopUpAdapter(getContext(),locationDataList,onRecyclerViewClick);
        rv_mechanic_order_popup.setAdapter(mechanicOrderPopUpAdapter);

    }


    @Override
    public void getGarageVehicleIssuesRequestResponseSuccess(GarageVehicleIssueRequestResponse garageVehicleIssueRequestResponse) {
        Utility.getInstance().dismissProgress();

//        Toast.makeText(getContext(), ""+garageVehicleIssueRequestResponse.getMessage(), Toast.LENGTH_SHORT).show();
//        userGarageIssueRequests=garageVehicleIssueRequestResponse.getUserGarageIssueRequests();
//        mechanicOrderPopUpAdapter=new MechanicOrderPopUpAdapter(getContext(),userGarageIssueRequests,onRecyclerViewClick);
//        rv_mechanic_order_popup.setAdapter(mechanicOrderPopUpAdapter);
    }

    @Override
    public void getGarageVehicleIssuesRequestResponseFailure(String message) {
        Utility.getInstance().dismissProgress();
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void acceptVehicleIssueSuccess(UserGarageRequestAcceptResponse garageVehicleIssueRequestResponse) {
//        Toast.makeText(getContext(), ""+garageVehicleIssueRequestResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void acceptVehicleIssueFailure(String message) {
//        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReclerViewClick(View view, int position) {
        Toast.makeText(getContext(), "accept", Toast.LENGTH_SHORT).show();

        locationDataList.remove(position);
        mechanicOrderPopUpAdapter.notifyDataSetChanged();
//        UserGarageRequestAcceptRequest userGarageRequestAcceptRequest=new UserGarageRequestAcceptRequest();
//        userGarageRequestAcceptRequest.setGarageId(garageId);
//        userGarageRequestAcceptRequest.setUserId(userId);
//        userGarageRequestAcceptRequest.setRequestId(userGarageIssueRequests.get(position).getAssignProblemToUserId());
//        garageVehicleIssueRequestPresenter.acceptUserGarageVehicleIssue(userGarageRequestAcceptRequest);


//        ------------------------------------------------
//        userGarageRequestAcceptRequest.setGarageId("6");
//        userGarageRequestAcceptRequest.setUserId("5bbabf57-048b-47c1-9129-");
//        userGarageRequestAcceptRequest.setRequestId("69");

    }
}
