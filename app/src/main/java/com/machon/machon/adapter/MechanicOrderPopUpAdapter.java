package com.machon.machon.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.machon.machon.R;
import com.machon.machon.model.LocationData;
import com.machon.machon.model.response.GarageVehicleIssueRequestResponse;
import com.machon.machon.utility.OnRecyclerViewClick;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MechanicOrderPopUpAdapter extends RecyclerView.Adapter<MechanicOrderPopUpAdapter.MyViewHolder> {
    Context context;
    List<LocationData> locationDataList;
    OnRecyclerViewClick onRecyclerViewClick;
    String requestSendDateTime,resquestTime;
    String currentTime;
//    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
    //2020-12-26T14:44:12.173
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


    List<GarageVehicleIssueRequestResponse.UserGarageIssueRequest> userGarageIssueRequests;

    public MechanicOrderPopUpAdapter(Context context, List<LocationData> locationDataList,OnRecyclerViewClick onRecyclerViewClick){
        this.context=context;
        this.locationDataList=locationDataList;
//        this.userGarageIssueRequests=userGarageIssueRequests;
        this.onRecyclerViewClick = onRecyclerViewClick;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_mechanic_order_pop_up_adapter,parent,false);
        return new MechanicOrderPopUpAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        LocationData data = locationDataList.get(position);

//        GarageVehicleIssueRequestResponse.UserGarageIssueRequest userGarageIssueRequest=userGarageIssueRequests.get(position);
//        holder.txtfirstname.setText(userGarageIssueRequest.getFirstName());
//        holder.txtlastname.setText(userGarageIssueRequest.getLastName());
//        holder.txtvehiclename.setText(userGarageIssueRequest.getVehicleType());
//        holder.txtlocation.setText(userGarageIssueRequest.getUserAddress());
//
//        holder.txtvehicleIssue.setText(userGarageIssueRequest.getOther());
//
//        holder.txtdescription.setText(userGarageIssueRequest.getDescription());
//
//
////        convertDateTimeToTime(userGarageIssueRequest);
//
//        DateFormat df=new SimpleDateFormat("HH:mm:ss");
//        currentTime=df.format(Calendar.getInstance().getTime());
//        Log.i("Date Time current",""+currentTime);
//
//        Date mDate;
//    requestSendDateTime = userGarageIssueRequest.getCurrentDateTime();
//    Log.i("Date Time", "" + requestSendDateTime);
//    try {
//        mDate = sdf.parse(requestSendDateTime);
//        long timeInMilliseconds1 = mDate.getHours();
//        long timeInMilliseconds2 = mDate.getMinutes();
//        long timeInMilliseconds3 = mDate.getSeconds();
//        resquestTime = timeInMilliseconds1 + ":" + timeInMilliseconds2 + ":" + timeInMilliseconds3;
//        holder.txttiming.setText(resquestTime);
//
//        Log.i("Date", "" + resquestTime);
//    } catch (Exception e) {
//        e.printStackTrace();
//        Log.e("TAG", "onBindViewHolder: " + e.getMessage() );
//    }
//
//
//        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
//        try {
//            Date date1=dateFormat.parse(currentTime);
//            Date date2=dateFormat.parse(resquestTime);
//            long diff=date1.getTime()-date2.getTime();
//            Log.i("Date second difference",""+diff);
//
//            Toast.makeText(context, ""+diff, Toast.LENGTH_SHORT).show();
//
//
//            long timeInSeconds = diff / 1000;
//            long hours, minutes, seconds;
//            hours = timeInSeconds / 3600;
//            timeInSeconds = timeInSeconds - (hours * 3600);
//            minutes = timeInSeconds / 60;
//            timeInSeconds = timeInSeconds - (minutes * 60);
//            seconds = timeInSeconds;
//
//            Log.i("Date main difference",""+hours+":"+minutes+":"+seconds);
//

//
//
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//


        holder.cardView_accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerViewClick.onReclerViewClick(holder.itemView, holder.getAdapterPosition());
            }
        });



    }

    @Override
    public int getItemCount() {
        return locationDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtfirstname,txtlastname,txtlocation,txtvehiclename,txtvehicleIssue,txtdescription,txttiming;
        CardView cardView_accept_btn,cardView_reject_btn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtfirstname=itemView.findViewById(R.id.txt_firstName);
            txtlastname=itemView.findViewById(R.id.txt_lastName);

            txtvehiclename=itemView.findViewById(R.id.txt_vehicle_name);
            txtlocation=itemView.findViewById(R.id.txt_location);
            txtvehicleIssue=itemView.findViewById(R.id.txt_vehicle_issue);
            txtdescription=itemView.findViewById(R.id.txt_description);
            txttiming=itemView.findViewById(R.id.txt_timing);
            cardView_accept_btn=itemView.findViewById(R.id.btn_accept);
            cardView_reject_btn=itemView.findViewById(R.id.btn_reject);



        }
    }


    void convertDateTimeToTime(GarageVehicleIssueRequestResponse.UserGarageIssueRequest userGarageIssueRequest){
        requestSendDateTime=userGarageIssueRequest.getCurrentDateTime();
        try {
            Date mDate = sdf.parse(requestSendDateTime);
            long timeInMilliseconds1 = mDate.getHours();
            long timeInMilliseconds2 = mDate.getMinutes();
            long timeInMilliseconds3 = mDate.getSeconds();
            resquestTime=timeInMilliseconds1+":"+timeInMilliseconds2+":"+timeInMilliseconds3;
            Log.i("Date",""+resquestTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
