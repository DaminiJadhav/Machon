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

import java.util.List;

public class SelectedVehicleIssuesAdapter extends RecyclerView.Adapter<SelectedVehicleIssuesAdapter.MyViewHolder>{
    Context context;
    List<String> selectedIssuelist;

    public SelectedVehicleIssuesAdapter(Context context, List<String> selectedIssuelist){
        this.context=context;
        this.selectedIssuelist=selectedIssuelist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_selected_vehicle_issue_adapter,parent,false);
        return new SelectedVehicleIssuesAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.selected_txt.setText(selectedIssuelist.get(position));

    }

    @Override
    public int getItemCount() {
        return selectedIssuelist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView selected_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            selected_txt=itemView.findViewById(R.id.txt_selected_issue);
        }
    }
}
