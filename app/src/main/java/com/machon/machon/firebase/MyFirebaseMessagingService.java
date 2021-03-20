package com.machon.machon.firebase;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.machon.machon.MainActivity;
import com.machon.machon.Notification.NotificationUtil;
import com.machon.machon.R;
import com.machon.machon.activity.home.mechanichome.ActivityMechanicHomePage;
import com.machon.machon.model.request.firebaseToken.GarageFirebaseTokenRequest;
import com.machon.machon.model.request.firebaseToken.UserFirebaseTokenRequest;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.model.response.firebaseToken.GarageFirebaseTokenResponse;
import com.machon.machon.model.response.firebaseToken.UserFirebaseTokenResponse;
import com.machon.machon.utility.AppSession;
import com.machon.machon.utility.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService implements UserGarageFirebaseTokenContractor.View{
    public static final String FCM_PARAM = "picture";
    private static final String CHANNEL_NAME = "FCM";
    private static final String CHANNEL_DESC = "Firebase Cloud Messaging";
    private int numMessages = 0;
    AppSession appSession;
    UserGarageFirebaseTokenPresenter userGarageFirebaseTokenPresenter;



    String imageUrl = "";
    String title;
    String action;
    String type;
    String orderId;
    String companyId;
    Bitmap image ;



    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification=remoteMessage.getNotification();
        Map<String, String> data = remoteMessage.getData();
        Log.d("Notification data", ""+remoteMessage.getData());

        Log.d("FROM", remoteMessage.getFrom());
        Log.d("Firebase", "Title " + remoteMessage.getNotification().getTitle()+" Body "+remoteMessage.getNotification().getBody());


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            sendNotification(notification, data);
//
//            Intent broacast=new Intent();
//            broacast.setAction("OPEN_NEW_ACTIVITY");
//            sendBroadcast(broacast);
//        }


        JSONObject jsonMessage;
        jsonMessage = new JSONObject(remoteMessage.getData());
        try {
            title = notification.getTitle();

//            title = jsonMessage.getString(Constants.PUSH_TITLE);
            action = jsonMessage.getString(Constants.PUSH_ACTION);
            type = jsonMessage.getString(Constants.PUSH_TYPE);

            orderId = jsonMessage.getString(Constants.PUSH_ORDER_ID);
            companyId = jsonMessage.getString(Constants.PUSH_COMPANY_ID);
            try {
                URL url = new URL(imageUrl);
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch(IOException e) {
                System.out.println(e);
            }

            sendNotificationOnDevices(title,action,type,orderId,companyId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




   private void sendNotification(RemoteMessage.Notification notification, Map<String, String> data){
       Intent intent = new Intent(this, ActivityMechanicHomePage.class);
//       intent.putExtras(bundle);
       PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
       NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
               .setContentTitle(notification.getTitle())
               .setContentText(notification.getBody())
               .setAutoCancel(true)
               .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
               //.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win))
               .setContentIntent(pendingIntent)
               .setContentInfo("Hello")
               .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//               .setColor(getColor(R.color.colorAccent))
               .setLights(Color.RED, 1000, 300)
               .setDefaults(Notification.DEFAULT_VIBRATE)
               .setNumber(++numMessages)
               .setSmallIcon(R.drawable.ic__phone);
       NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           NotificationChannel channel = new NotificationChannel(
                   getString(R.string.default_notification_channel_id), CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
           );
           channel.setDescription(CHANNEL_DESC);
           channel.setShowBadge(true);
           channel.canShowBadge();
           channel.enableLights(true);
           channel.setLightColor(Color.RED);
           channel.enableVibration(true);
           channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});
           assert notificationManager != null;
           notificationManager.createNotificationChannel(channel);
       }
       assert notificationManager != null;
       notificationManager.notify(0, notificationBuilder.build());

   }


    private void sendNotificationOnDevices(String title,String action,String type,String orderId,String companyId) {
        Random r = new Random();
        int i1 = r.nextInt(100 - 1) + 1;
        Long timestampLong = System.currentTimeMillis();
        String timeStamp = timestampLong.toString();

        NotificationUtil notificationUtils = new NotificationUtil (getApplicationContext());
        notificationUtils.showNotificationMessage(type,title,action,timeStamp,type, String.valueOf(i1),"123",orderId,companyId);
    }



    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e("newToken", token);

        appSession=AppSession.getInstance(this);
        userGarageFirebaseTokenPresenter=new UserGarageFirebaseTokenPresenter(this);

            LoginResponse loginResponse=appSession.getUserLogin();
            LoginMechanicResponse loginMechanicResponse=appSession.getMechanicLogin();

            String userSelected=appSession.getUserSelection();

            if(userSelected!=""){
                if(userSelected.equalsIgnoreCase("User")){
                    UserFirebaseTokenRequest userFirebaseTokenRequest=new UserFirebaseTokenRequest();
                    userFirebaseTokenRequest.setId(loginResponse.getResponse().getId());
                    userFirebaseTokenRequest.setUserFCMTocken(token);
                    appSession.setFirebaseToken(token);
                    userGarageFirebaseTokenPresenter.postUserFirebaseToken(userFirebaseTokenRequest);
                }else {
                    GarageFirebaseTokenRequest garageFirebaseTokenRequest=new GarageFirebaseTokenRequest();
                    garageFirebaseTokenRequest.setId(loginMechanicResponse.getGarage().getGarageRegistrationId());
                    garageFirebaseTokenRequest.setUserFCMTocken(token);
                    appSession.setFirebaseToken(token);
                    userGarageFirebaseTokenPresenter.postGarageFirebaseToken(garageFirebaseTokenRequest);
                }

            }else {
                Log.e("No one ", "Login this app");

            }




     }


    @Override
    public void userFirebaseTokenSuccess(UserFirebaseTokenResponse response) {
        Toast.makeText(this, ""+response.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userFirebaseTokenFailure(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void garageFirebaseTokenSuccess(GarageFirebaseTokenResponse response) {
        Toast.makeText(this, ""+response.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void garageFirebaseTokenFailure(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }
}
