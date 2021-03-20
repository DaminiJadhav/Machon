package com.machon.machon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.machon.machon.R;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.GetUserRequestAcceptListResponse;
import com.machon.machon.utility.Utility;

import java.util.List;

public class UserOrderDetailAdapter extends RecyclerView.Adapter<UserOrderDetailAdapter.MyViewHolder> {
    Context context;
    List<LocationData> locationDataList;
    List<GetUserRequestAcceptListResponse.UserGarageIssueRequest> userGarageIssueRequests;

    public UserOrderDetailAdapter(Context context, List<GetUserRequestAcceptListResponse.UserGarageIssueRequest> userGarageIssueRequests){
        this.context=context;
        this.userGarageIssueRequests=userGarageIssueRequests;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_order_detail_list,parent,false);
        return new UserOrderDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        LocationData data = locationDataList.get(position);
        GetUserRequestAcceptListResponse.UserGarageIssueRequest userGarageIssueRequest=userGarageIssueRequests.get(position);

        holder.garageName.setText(userGarageIssueRequest.getGarageName());
        holder.garageLocation.setText(userGarageIssueRequest.getGarageAddress());
        holder.mechanic_name.setText(userGarageIssueRequest.getHeadMechanicName());
        if(userGarageIssueRequest.getGarageOwnerMobNo()==null){
            holder.mechanicNumber.setVisibility(View.GONE);

        }else {
            holder.mechanicNumber.setVisibility(View.VISIBLE);
            holder.mechanicNumber.setText(userGarageIssueRequest.getGarageOwnerMobNo());

        }

        if(userGarageIssueRequest.isStatus()==true){
            holder.status.setText("Accept");
        }else {
            holder.status.setText("Reject");
        }

        holder.ivcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.getInstance().callPhoneNumber(userGarageIssueRequest.getGarageOwnerMobNo(),context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userGarageIssueRequests.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView garageName,mechanic_name,garageLocation,mechanicNumber,status;
        ImageView ivcall;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            garageName=itemView.findViewById(R.id.txt_garage_name);
            garageLocation=itemView.findViewById(R.id.txt_garage_address);
            mechanic_name=itemView.findViewById(R.id.txt_mechanicname);
            mechanicNumber=itemView.findViewById(R.id.txt_mechanic_number);
            status=itemView.findViewById(R.id.txt_status);
            ivcall=itemView.findViewById(R.id.iv_call);



        }
    }
}
