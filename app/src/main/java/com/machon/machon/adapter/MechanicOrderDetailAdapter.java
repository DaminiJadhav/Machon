package com.machon.machon.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.machon.machon.R;
import com.machon.machon.activity.location.ActivityLocationSelection;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.GarageVehicleIssueRequestResponse;
import com.machon.machon.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class MechanicOrderDetailAdapter extends RecyclerView.Adapter<MechanicOrderDetailAdapter.MyViewHolder> {
    Context context;
    List<LocationData> locationDataList;
    List<GarageVehicleIssueRequestResponse.UserGarageIssueRequest> userGarageIssueRequests=new ArrayList<>();


    public MechanicOrderDetailAdapter(Context context, List<GarageVehicleIssueRequestResponse.UserGarageIssueRequest> userGarageIssueRequests){
        this.context=context;
        this.userGarageIssueRequests=userGarageIssueRequests;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_mechanic_order_detail_adapter_list,parent,false);
        return new MechanicOrderDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        LocationData data = locationDataList.get(position);
        GarageVehicleIssueRequestResponse.UserGarageIssueRequest userGarageIssueRequest=userGarageIssueRequests.get(position);

        holder.userfirstname.setText(userGarageIssueRequest.getFirstName());
        holder.userlastname.setText(userGarageIssueRequest.getLastName());
        holder.vehicleName.setText(userGarageIssueRequest.getVehicleType());
//        holder.user_address.setText(userGarageIssueRequest.getUserAddress());

        holder.userNumber.setText(userGarageIssueRequest.getMobNo());

        holder.ivcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callPhoneNumber(userGarageIssueRequest.getMobNo());
                Utility.getInstance().callPhoneNumber(userGarageIssueRequest.getMobNo(),context);

            }
        });
    }

    @Override
    public int getItemCount() {
        return userGarageIssueRequests.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView user_address,userfirstname,userlastname,userNumber,vehicleName;

        ImageView ivcall;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userfirstname=itemView.findViewById(R.id.txt_first_name);
            userlastname=itemView.findViewById(R.id.txt_last_name);
            userNumber=itemView.findViewById(R.id.txt_user_number);
            user_address=itemView.findViewById(R.id.txt_user_address);
            vehicleName=itemView.findViewById(R.id.txt_vehicle_name);

            ivcall=itemView.findViewById(R.id.iv_call);

        }
    }

    public void callPhoneNumber(String mobileNumber)
    {
//        String mobileNumber="9657431432";
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobileNumber));
                context.startActivity(callIntent);

            }
            else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobileNumber));
                context.startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
