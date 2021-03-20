package com.machon.machon.activity.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.machon.machon.MainActivity;
import com.machon.machon.R;
import com.machon.machon.activity.home.mechanichome.ActivityMechanicHomePage;
import com.machon.machon.activity.home.userhome.ActivityHomePage;
import com.machon.machon.activity.location.ActivityLocationSelection;
import com.machon.machon.activity.login.ActivityLoginScreen;
import com.machon.machon.activity.userselection.ActivityUserSelection;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.utility.AppSession;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitySplashScreen extends AppCompatActivity {

    String token;
    AppSession appSession;

    @OnClick(R.id.button_test)
    void button_test(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        appSession=AppSession.getInstance(this);

        firebaseToken();
//        checkIsLogin();
        new  Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(ActivitySplashScreen.this, ActivityUserSelection.class);
                startActivity(intent);
                finish();
            }
        },3000);
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
                        appSession.setFirebaseToken(token);
                        Log.d("Token",""+token);
//                        Toast.makeText(ActivitySplashScreen.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    void checkIsLogin(){

        new  Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String checkLogin=appSession.getUserSelection();
                if(checkLogin!=""){
                    if(checkLogin.equalsIgnoreCase("User")){
                        Log.e("User ", "Login");

                        LoginResponse loginResponse=appSession.getUserLogin();
                        if(loginResponse!=null){
                            Intent intent=new Intent(ActivitySplashScreen.this, ActivityHomePage.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent=new Intent(ActivitySplashScreen.this, ActivityUserSelection.class);
                            startActivity(intent);
                            finish();
                        }
                    }else {
                        Log.e("Mechanic ", "Login");

                        LoginMechanicResponse loginMechanicResponse=appSession.getMechanicLogin();
                        if(loginMechanicResponse!=null){
                            Intent intent=new Intent(ActivitySplashScreen.this, ActivityMechanicHomePage.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent=new Intent(ActivitySplashScreen.this, ActivityUserSelection.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                }else {
                    Log.e("No one ", "Login this app");

                    Intent intent=new Intent(ActivitySplashScreen.this, ActivityUserSelection.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);

    }
}