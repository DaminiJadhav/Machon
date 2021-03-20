package com.machon.machon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.machon.machon.R;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.PostGarageIssueResponse;

import java.util.List;

public class LocationSelectionAdapter extends RecyclerView.Adapter<LocationSelectionAdapter.MyViewHolder> {
    Context context;
    List<LocationData> locationDataList;
    List<PostGarageIssueResponse.GarageList> garageLists;

    public LocationSelectionAdapter(Context context,List<PostGarageIssueResponse.GarageList> garageLists){
      this.context=context;
      this.garageLists=garageLists;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_location_detail_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        LocationData data = locationDataList.get(position);

        holder.txt_address.setText(garageLists.get(position).getGarageAddress());
        holder.txt_garagename.setText(garageLists.get(position).getGarageName());

        holder.txt_garageownerName.setText(garageLists.get(position).getGarageOwnerName());
        holder.txt_garagePhNo.setText(garageLists.get(position).getGarageOwnerMobNo());


    }

    @Override
    public int getItemCount() {
        return garageLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_address,txt_garagename,txt_garageownerName,txt_garagePhNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_address=itemView.findViewById(R.id.locationtxt);
            txt_garagename=itemView.findViewById(R.id.garageName);
            txt_garageownerName=itemView.findViewById(R.id.garage_owner_name);
            txt_garagePhNo=itemView.findViewById(R.id.garage_phone_number);

        }
    }
}
