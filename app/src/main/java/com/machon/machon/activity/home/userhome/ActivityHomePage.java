package com.machon.machon.activity.home.userhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.machon.machon.R;
import com.machon.machon.activity.issues.ActivityVehicleIssuesForm;
import com.machon.machon.adapter.UserOrderDetailAdapter;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.GetUserRequestAcceptListResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityHomePage extends AppCompatActivity implements UserHomePageContractor.View{

    RecyclerView rv_order_detail;
    private List<LocationData> locationDataList = new ArrayList<>();
    UserOrderDetailAdapter orderDetailAdapter;
    androidx.appcompat.widget.Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    AppSession appSession;
    LoginResponse loginResponse;
    UserHomePagePresenter  userHomePagePresenter;
    List<GetUserRequestAcceptListResponse.UserGarageIssueRequest> userGarageIssueRequests=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        appSession=AppSession.getInstance(this);
        loginResponse=appSession.getUserLogin();
        Log.i("LoginResponse",""+loginResponse);

        userHomePagePresenter=new UserHomePagePresenter(this);
        Utility.getInstance().showProgressDialogue(this);
        userHomePagePresenter.getUserGarageResponse(loginResponse.getResponse().getId());


        toolbar=(androidx.appcompat.widget.Toolbar) findViewById(R.id.home_page_toolbar1);
        floatingActionButton=findViewById(R.id.floating_add_order_button);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        rv_order_detail=findViewById(R.id.order_recycle_view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActivityHomePage.this);
        rv_order_detail.setLayoutManager(linearLayoutManager);

        StudentDataPrepare();

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

//        orderDetailAdapter=new UserOrderDetailAdapter(ActivityHomePage.this,locationDataList);
//        rv_order_detail.setAdapter(orderDetailAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home_page_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.profile_menu:
                Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_LONG).show();
                return true;
            case R.id.history_menu:
                Toast.makeText(getApplicationContext(),"history",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.floating_add_order_button)
    void floating_add_order_btn(){
        Intent intent=new Intent(this, ActivityVehicleIssuesForm.class);
        startActivity(intent);
    }




    @Override
    public void userVehicleIssueListSuccess(GetUserRequestAcceptListResponse getUserRequestAcceptListResponse) {
        Utility.getInstance().dismissProgress();
        userGarageIssueRequests=getUserRequestAcceptListResponse.getUserGarageIssueRequests();
        orderDetailAdapter=new UserOrderDetailAdapter(ActivityHomePage.this,userGarageIssueRequests);
        rv_order_detail.setAdapter(orderDetailAdapter);
//        Toast.makeText(this, ""+getUserRequestAcceptListResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userVehicleIssueListFailure(String message) {
        Utility.getInstance().dismissProgress();

//        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}