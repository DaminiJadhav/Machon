package com.machon.machon;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.machon.machon.activity.login.ActivityLoginScreen;
import com.machon.machon.broadcast.MyReceiver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    String token,msg;
    String datetime="2020-12-26T14:44:12.173";
    String getdate,date;

    IntentFilter intentFilter=new IntentFilter("OPEN_NEW_ACTIVITY");


    @BindView(R.id.txt_timing)
    TextView textView;

    @BindView(R.id.ll_firebase_call)
    LinearLayout linearLayout;

    @OnClick(R.id.btn_click)
    void btn_ok(){


        EditText st = (EditText)findViewById(R.id.txtMsg);
        Intent intent = new Intent();
        intent.putExtra("msg",(CharSequence)st.getText().toString());
        intent.setAction("YES_ACTION");
        sendBroadcast(intent);
//        Intent intent = new Intent();
//        intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
//        sendBroadcast(intent);



    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        firebaseToken();

//        DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateFormat df=new SimpleDateFormat("HH:mm:ss");
         date=df.format(Calendar.getInstance().getTime());
//        textView.setText(date);

//convert date time into sencond
        String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        try {
            Date mDate = sdf.parse(givenDateString);
            long timeInMilliseconds1 = mDate.getHours();
            long timeInMilliseconds2 = mDate.getMinutes();
            long timeInMilliseconds3 = mDate.getSeconds();
             getdate=timeInMilliseconds1+":"+timeInMilliseconds2+":"+timeInMilliseconds3;
            Log.i("Date",""+getdate);
            System.out.println("Date in milli  " + timeInMilliseconds1+":"+timeInMilliseconds2+":"+timeInMilliseconds3);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
        try {
            Date date1=dateFormat.parse(getdate);
            Date date2=dateFormat.parse(date);
            long diff=date2.getTime()-date1.getTime();
            Log.i("Date second difference",""+diff);

            Toast.makeText(this, ""+diff, Toast.LENGTH_SHORT).show();

            setTimer(diff);

            long timeInSeconds = diff / 1000;
            long hours, minutes, seconds;
            hours = timeInSeconds / 3600;
            timeInSeconds = timeInSeconds - (hours * 3600);
            minutes = timeInSeconds / 60;
            timeInSeconds = timeInSeconds - (minutes * 60);
            seconds = timeInSeconds;

            Log.i("Date main difference",""+hours+":"+minutes+":"+seconds);




        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    void setTimer(long diff){

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
//                timer.setVisibility(View.VISIBLE);
                textView.setText("seconds remaining: " + l / 1000);

            }

            @Override
            public void onFinish() {
//                timer.setVisibility(View.GONE);
//                timer_start.setVisibility(View.GONE);
            }
        }.start();    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Broadcast RECEIVER call", Toast.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.VISIBLE);
            Intent myNewActivity = new Intent(context, ActivityLoginScreen.class);
            startActivity(myNewActivity);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(broadcastReceiver!=null){
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver=null;
        }
    }


    void firebaseToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(!task.isSuccessful()){
                            Log.w("Firebase", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        token=task.getResult();
                        Log.w("Token",""+token);
//                        Toast.makeText(ActivitySplashScreen.this, token, Toast.LENGTH_SHORT).show();

                    }


                });
    }
}