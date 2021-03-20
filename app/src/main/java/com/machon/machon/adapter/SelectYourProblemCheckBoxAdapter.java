package com.machon.machon.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.machon.machon.R;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.GetVehicleProblemResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.OnItemSendClickListener;
import com.machon.machon.utility.OnRecyclerViewClick;

import java.util.ArrayList;
import java.util.List;

public class SelectYourProblemCheckBoxAdapter extends RecyclerView.Adapter<SelectYourProblemCheckBoxAdapter.MyViewHolder> {

    Context context;
    List<LocationData> locationDataList;
    List<GetVehicleProblemResponse.VehicleType> problemListTypes=new ArrayList<>();

    AppSession appSession;
   private OnRecyclerViewClick onRecyclerViewClick;



    public SelectYourProblemCheckBoxAdapter(Context context, List<GetVehicleProblemResponse.VehicleType> problevehicleTypes, OnRecyclerViewClick onRecyclerViewClick){
        this.context=context;
        this.problemListTypes=problevehicleTypes;
        this.onRecyclerViewClick = onRecyclerViewClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_select_your_problem_checkbox,parent,false);
        return new SelectYourProblemCheckBoxAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        LocationData data = locationDataList.get(position);

        holder.problem_select_checkbox.setText(problemListTypes.get(position).getPeoblem());

        boolean checked = holder.problem_select_checkbox.isChecked();

           // holder.problem_select_checkbox.setOnClickListener(new OnItemSendClickListener(position,onclick,"selectedCheckBox"));

        holder.problem_select_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    problemListTypes.get(position).setSelectProblem(b);
                    onRecyclerViewClick.onReclerViewClick(holder.itemView, holder.getAdapterPosition());
                }else {
                    problemListTypes.get(position).setSelectProblem(b);
                    onRecyclerViewClick.onReclerViewClick(holder.itemView, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return problemListTypes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CheckBox problem_select_checkbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            problem_select_checkbox=itemView.findViewById(R.id.select_checkbox);
        }

    }
}
