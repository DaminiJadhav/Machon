package com.machon.machon.adapter.viewpageradapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.machon.machon.fragment.FragmentMechanicOrderDetail;
import com.machon.machon.fragment.FragmentMechanicOrderPopUp;

public class GarageDetailAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;

    public GarageDetailAdapter(Context ctxt,FragmentManager fm,int totalTabs) {
        super(fm);
        context =ctxt ;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentMechanicOrderPopUp popupfragment = new FragmentMechanicOrderPopUp();
                return popupfragment;
            case 1:
                FragmentMechanicOrderDetail orderDetailFragment = new FragmentMechanicOrderDetail();
                return orderDetailFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
