package com.machon.machon.activity.home.mechanichome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.machon.machon.R;
import com.machon.machon.adapter.viewpageradapter.GarageDetailAdapter;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.utility.AppSession;

public class ActivityMechanicHomePage extends AppCompatActivity {
    TabLayout mechanic_detail_tabLayout;
    ViewPager mechanic_detail_viewPager;
    GarageDetailAdapter garageDetailAdapter;
    AppSession appSession;
    LoginMechanicResponse loginMechanicResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_home_page);




        mechanic_detail_tabLayout = findViewById(R.id.mechanic_tabLayout);
        mechanic_detail_viewPager = findViewById(R.id.mechanic_view_pager);

        mechanic_detail_tabLayout.addTab(mechanic_detail_tabLayout.newTab().setText("Mechanic"));
        mechanic_detail_tabLayout.addTab(mechanic_detail_tabLayout.newTab().setText("Garage Detail"));
//        mechanic_detail_tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        garageDetailAdapter=new GarageDetailAdapter(this,getSupportFragmentManager(),mechanic_detail_tabLayout.getTabCount());
        mechanic_detail_viewPager.setAdapter(garageDetailAdapter);

        mechanic_detail_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mechanic_detail_tabLayout));
        mechanic_detail_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mechanic_detail_viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}