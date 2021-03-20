package com.machon.machon.activity.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.machon.machon.R;
import com.machon.machon.activity.issues.ActivityVehicleIssuesForm;
import com.machon.machon.adapter.LocationSelectionAdapter;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.PostGarageIssueResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.Constants;
import com.machon.machon.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLocationSelection extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private RecyclerView locationDetailRecycleView;
    private List<LocationData> locationDataList = new ArrayList<>();
    LocationSelectionAdapter locationSelectionAdapter;
    LocationManager locationManager;
    SupportMapFragment mapFragment;
    private GoogleMap mgooglemap;
    List<PostGarageIssueResponse.GarageList> garageLists=new ArrayList<>();
    AppSession appSession;
    PostGarageIssueResponse postGarageIssueResponse;
    LinearLayout linearLayout;
    CardView cv_garage_detail;

    double longitude,latitude;
    String garagename;

//    @BindView(R.id.tv_gara_name)
    TextView txt_garage_name;

    @BindView(R.id.locationtxt)
    TextView tv_location;


    @BindView(R.id.garage_owner_name)
    TextView tv_garage_ownername;

    @BindView(R.id.garage_phone_number)
    TextView tv_garag_phoneNo;

    @BindView(R.id.txt_timer)
    TextView timer_start;


    @BindView(R.id.btn_send_request)
    CardView cardview_send_request;


    @BindView(R.id.card_view_direction)
    CardView direction;
    @BindView(R.id.ll_retry_request)
    LinearLayout ll_retry;

    @BindView(R.id.tv_timer)
    TextView timer;

    @BindView(R.id.rl_garage_detail)
    RelativeLayout rl_garagedetail;

    @BindView(R.id.iv_call)
    ImageView ivcall;


    @BindView(R.id.cv_retry)
    CardView cvretry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selection);
        ButterKnife.bind(this);

        locationDetailRecycleView=(RecyclerView) findViewById(R.id.locationRecycleView);
        linearLayout=findViewById(R.id.ll_garage_detail);
        cv_garage_detail=findViewById(R.id.cardView_garage_detail);
        txt_garage_name=findViewById(R.id.tv_gara_name);

        setTimer();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();

        appSession=AppSession.getInstance(this);
        postGarageIssueResponse=appSession.getGarageList();
        garageLists=postGarageIssueResponse.getGarageList();



//        Intent intent=getIntent();
//        Bundle bundle=intent.getExtras();
//
//        garageLists=bundle.getParcelableArrayList(Constants.GARAGE_ISSUE_RESPONSE);


//        Toast.makeText(this, ""+postGarageIssueResponse.getGarageList().get(0).getGarageAddress(), Toast.LENGTH_SHORT).show();


        RecyclerView.LayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        locationDetailRecycleView.setLayoutManager(manager);
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(locationDetailRecycleView);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Utility.getInstance().permissionDialog(this);
        } else {

            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);


        }

        StudentDataPrepare();
    }


    @OnClick(R.id.btn_send_request)
    void send_request_btn(){
        cardview_send_request.setVisibility(View.GONE);
    }


    @OnClick(R.id.card_view_direction)
    void direction_btn(){
//        Uri gmmIntentUri = Uri.parse("geo:"+latitude+","+longitude+"?q="+tv_location.getText());
//        Intent mapIntent=new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);
    }




    @OnClick(R.id.iv_call)
    void call_btn(){
        callPhoneNumber();
    }

    @OnClick(R.id.cv_retry)
    void retry(){
        timer.setVisibility(View.VISIBLE);

        setTimer();
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

//        locationSelectionAdapter=new LocationSelectionAdapter(ActivityLocationSelection.this,garageLists);
//        locationDetailRecycleView.setAdapter(locationSelectionAdapter);

    }


    void  getLocation(){
        if (ActivityCompat.checkSelfPermission(ActivityLocationSelection.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityLocationSelection.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ActivityLocationSelection.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgooglemap = googleMap;
//        mgooglemap.setMapType(mgooglemap.MAP_TYPE_SATELLITE);


        garageList();

        mgooglemap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

//                rl_garagedetail.setVisibility(View.VISIBLE);
//                timer_start.setVisibility(View.GONE);

//
                for (int i=0;i<garageLists.size();i++){

                    Log.d("Garage name",""+garageLists.get(i).getGarageName());
                    Toast.makeText(ActivityLocationSelection.this, ""+garageLists.get(i).getGarageName(), Toast.LENGTH_SHORT).show();
//                    if(garageLists.get(i).getGarageLatitude()==marker.getPosition().latitude && garageLists.get(i).getGarageLongitude()==marker.getPosition().longitude){
//                        txt_garage_name.setText(""+garageLists.get(i).getGarageName());
//                        tv_location.setText(""+garageLists.get(i).getGarageAddress());
//                        tv_garage_ownername.setText(""+garageLists.get(i).getGarageOwnerName());
//                        tv_garag_phoneNo.setText(""+garageLists.get(i).getGarageOwnerMobNo());
//
//                    }
                }
//

                return true;
            }
        });

    }


    void garageList(){
        for (int i=0;i<garageLists.size();i++){


//            Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
            Log.d("location size",""+garageLists.size());

            Log.d("location lat",""+garageLists.get(i).getGarageLatitude());
            Log.d("location log",""+garageLists.get(i).getGarageLongitude());
            Log.d("location garage",""+garageLists.get(i).getGarageOwnerName());

//            Toast.makeText(this, ""+garageLists.get(i).getGarageLatitude()+","+garageLists.get(i).getGarageLongitude(), Toast.LENGTH_SHORT).show();
            Log.d("garage",""+ garageLists.get(i).getGarageLatitude()+","+garageLists.get(i).getGarageLongitude());



            LatLng location_selection = new LatLng(garageLists.get(i).getGarageLatitude(), garageLists.get(i).getGarageLongitude());
            garagename=garageLists.get(i).getGarageName();
            mgooglemap.addMarker(new MarkerOptions().position(location_selection).title(garageLists.get(i).getGarageName()).icon(bitmapDescriptorVector(getApplicationContext(),R.drawable.ic_car_repair)));
//            mgooglemap.addMarker(new MarkerOptions().position(location_selection).title(garageLists.get(i).getGarageName()));

            mgooglemap.moveCamera(CameraUpdateFactory.newLatLngZoom(location_selection, 12));
        }

    }


    void setTimer(){

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
//                timer.setVisibility(View.VISIBLE);
                timer.setText("seconds remaining: " + l / 1000);
                timer_start.setText("seconds remaining: " + l / 1000);

            }

            @Override
            public void onFinish() {
//                timer.setVisibility(View.GONE);
//                timer_start.setVisibility(View.GONE);
                direction.setVisibility(View.VISIBLE);
            }
        }.start();
    }


    private BitmapDescriptor bitmapDescriptorVector(Context context, int vectorResId){
        Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    public void callPhoneNumber()
    {
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ActivityLocationSelection.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + tv_garag_phoneNo.getText()));
                startActivity(callIntent);

            }
            else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + tv_garag_phoneNo.getText()));
                startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
//        Toast.makeText(this, ""+latitude+","+longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("onStatusChanged"," call");
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Log.i("onProviderEnabled"," call");

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Log.i("onProviderDisabled"," call");

    }
}