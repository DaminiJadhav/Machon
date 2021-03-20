package com.machon.machon.activity.location.mechanic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.machon.machon.R;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityGarageLocationSelection extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mgooglemap;
    SupportMapFragment mapFragment;
    LocationManager locationManager;
    Double latitude = 0.0;
    Double longitude = 0.0;
    PlacesClient placesClient;
    //    List<Place.Field> places;
    String getaddress, getaddpoint;
    double lat;
    double logn;
    EditText setPlace_editText;
    TextView tvSelectedLocation;
    String garageAddress, selectedGarageLocation;
    Geocoder geocoder, mapClickGeoCoderLocation;
    List<Address> addresses, mapClickAddress;
    String address;
    String city;
    AppSession appSession;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;


//    PlaceAutocompleteFragment autocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_location_selction);
        ButterKnife.bind(this);


        appSession = AppSession.getInstance(this);
        setPlace_editText = findViewById(R.id.ed_search);
        tvSelectedLocation = findViewById(R.id.tv_selected_location);

        geocoder = new Geocoder(this, Locale.getDefault());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Intent intent = getIntent();
        selectedGarageLocation = intent.getExtras().getString(Constants.GARAGE_LOCATION_KEY);
//        Toast.makeText(this, "" + selectedGarageLocation, Toast.LENGTH_SHORT).show();

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            permissionDialog();
        } else {
            getLocation();
            loadPlace();

            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);


        }
    }


    @OnClick(R.id.ed_search)
    void search_result() {
        pickUpLocationPicker();
    }

    @OnClick(R.id.btn_confirm)
    void btn_Confirm() {
//        appSession.setGarageAddress(garageAddress);
        Intent intent = new Intent();
        intent.putExtra(Constants.SELECTED_GARARAGE_LOC, garageAddress);
        intent.putExtra(Constants.SELECTED_LATITUDE, latitude);
        intent.putExtra(Constants.SELECTED_LONGITUDE, longitude);

        setResult(Constants.GARAGELOCATIONSELECTION, intent);
        finish();
    }

    @OnClick(R.id.floating_current_location_btn)
    void floating_btn_current_loc() {
        mgooglemap.clear();
        LatLng garage_location = new LatLng(latitude, longitude);
        mgooglemap.addMarker(new MarkerOptions().position(garage_location).title("MyLocation"));
        mgooglemap.moveCamera(CameraUpdateFactory.newLatLng(garage_location));
        currentAddress(latitude, longitude);
        setPlace_editText.setText("");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PLACE_PICKUP_POINT) {
            if (resultCode == this.RESULT_OK) {
                Place place = (Place) Autocomplete.getPlaceFromIntent(data);
                getaddress = (String) place.getAddress();
                setPlace_editText.setText(place.getAddress());
                tvSelectedLocation.setText(place.getAddress());
                garageAddress = place.getAddress();
                LatLng location = place.getLatLng();
//                lat = location.latitude;
//                logn = location.longitude;
                latitude = location.latitude;
                longitude = location.longitude;
                mgooglemap.clear();
                mgooglemap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
                mgooglemap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                mgooglemap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 12.0f));
                Log.d("LLocation", "Selected Place: " + latitude + "," + longitude);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgooglemap = googleMap;
//        mgooglemap.getUiSettings().setZoomControlsEnabled(true);
        mgooglemap.setMaxZoomPreference(20);
//        mgooglemap.setMyLocationEnabled(true);
/*        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);*/


        LatLng garage_location = new LatLng(latitude, longitude);
        mgooglemap.addMarker(new MarkerOptions().position(garage_location).title("MyLocation"));
        mgooglemap.moveCamera(CameraUpdateFactory.newLatLngZoom(garage_location, 25));
//        mgooglemap.animateCamera(CameraUpdateFactory.zoomTo(25),2000,null);
//        mgooglemap.moveCamera(CameraUpdateFactory.newLatLng(garage_location));

        mgooglemap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mgooglemap.clear();
                mgooglemap.addMarker(new MarkerOptions().position(latLng).title("MyLocation"));
                mgooglemap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                currentAddress(latLng.latitude, latLng.longitude);
                setPlace_editText.setText("");
            }
        });

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                ActivityGarageLocationSelection.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                ActivityGarageLocationSelection.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (locationGPS != null) {
                latitude = locationGPS.getLatitude();
                longitude = locationGPS.getLongitude();

                currentAddress(latitude, longitude);

//                new java.util.Timer().schedule(
//
//                        new java.util.TimerTask() {
//                            @Override
//                            public void run() {
//                                // your code here, and if you have to refresh UI put this code:
//                                runOnUiThread(new Runnable() {
//                                    public void run() {
//                                        //your code
                                        if (ActivityCompat.checkSelfPermission(ActivityGarageLocationSelection.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityGarageLocationSelection.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                            // TODO: Consider calling
                                            //    ActivityCompat#requestPermissions
                                            // here to request the missing permissions, and then overriding
                                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                            //                                          int[] grantResults)
                                            // to handle the case where the user grants the permission. See the documentation
                                            // for ActivityCompat#requestPermissions for more details.
                                            return;
                                        }
                                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ActivityGarageLocationSelection.this);
//
//                                    }
//                                });
//                            }
//                        },
//                        3000
//                );

            } else {

                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void currentAddress(double lat,double lon){
        try{
            addresses=geocoder.getFromLocation(lat,lon,1);

                garageAddress=addresses.get(0).getAddressLine(0);
                tvSelectedLocation.setText(garageAddress);
//                    city=addresses.get(0).getLocality();
            Log.i("Address",""+address+"  "+city);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void permissionDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable permission").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void loadPlace(){
        if(!Places.isInitialized()){
            Places.initialize(this,getString(R.string.google_maps_key));
            placesClient=Places.createClient(this);
        }
    }

    private void pickUpLocationPicker() {
        List<Place.Field> fields = Arrays.asList(com.google.android.libraries.places.api.model.Place.Field.ID, com.google.android.libraries.places.api.model.Place.Field.NAME, com.google.android.libraries.places.api.model.Place.Field.LAT_LNG, com.google.android.libraries.places.api.model.Place.Field.ADDRESS);
        Intent autoCompleteIntent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(this);
        startActivityForResult(autoCompleteIntent, Constants.PLACE_PICKUP_POINT);
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
   /*     Toast.makeText(ActivityGarageLocationSelection.this, "call handler", Toast.LENGTH_SHORT).show();
        Location myLocation = location;*/
        latitude = location.getLatitude();
        longitude = location.getLongitude();
//        currentAddress(location.getLatitude(),location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Log.d("Latitude","enable");

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Log.d("Latitude","disable");

    }


}