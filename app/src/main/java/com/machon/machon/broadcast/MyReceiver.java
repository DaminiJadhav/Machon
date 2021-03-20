package com.machon.machon.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        CharSequence iData = intent.getCharSequenceExtra("msg");
        Toast.makeText(context,"Tutlane Received Message: "+iData,Toast.LENGTH_LONG).show();
//        Toast.makeText(context, "Success receiver", Toast.LENGTH_SHORT).show();
//        if("com.codinginflow.EXAMPLE_ACTION".equals(intent.getAction())){
//            String receivetxt=intent.getStringExtra("com.codinginflow.EXTRA_TEXT");
//            Toast.makeText(context, "Action: " + receivetxt, Toast.LENGTH_SHORT).show();
//        }


//        Bundle extras=intent.getExtras();
//        Intent i =new Intent("broastCastName");
//        i.putExtra("Data",extras.getString("datakey"));
//        context.sendBroadcast(i);
    }
}
