package com.machon.machon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.machon.machon.R;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.GetVehicleTypeResponse;
import com.machon.machon.utility.OnRecyclerViewClick;

import java.util.ArrayList;
import java.util.List;

public class SelectYourGarageTypeCheckBoxAdapter extends RecyclerView.Adapter<SelectYourGarageTypeCheckBoxAdapter.MyViewHolder>{

    Context context;
    List<LocationData> locationDataList;
    List<String>  selectedList=new ArrayList<>();
    private OnRecyclerViewClick onRecyclerViewClick;
   List<GetVehicleTypeResponse.VehicleType> vehiclesTypes;


    public SelectYourGarageTypeCheckBoxAdapter(Context context, List<GetVehicleTypeResponse.VehicleType> vehiclesTypes, OnRecyclerViewClick onRecyclerViewClick){
        this.context=context;
        this.vehiclesTypes=vehiclesTypes;
        this.onRecyclerViewClick = onRecyclerViewClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_select_your_garage_type,parent,false);
        return new SelectYourGarageTypeCheckBoxAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        LocationData data = locationDataList.get(position);

        holder.select_garagae_type.setText(vehiclesTypes.get(position).getVehicleType());


        holder.select_garagae_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    vehiclesTypes.get(position).setGarageTypeSelect(b);
                    onRecyclerViewClick.onReclerViewClick(holder.itemView, holder.getAdapterPosition());
                }else {
                    vehiclesTypes.get(position).setGarageTypeSelect(b);
                    onRecyclerViewClick.onReclerViewClick(holder.itemView, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehiclesTypes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CheckBox select_garagae_type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            select_garagae_type=itemView.findViewById(R.id.select_garage_type_checkbox);
        }

    }
}
