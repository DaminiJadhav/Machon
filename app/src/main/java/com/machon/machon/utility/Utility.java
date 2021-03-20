package com.machon.machon.utility;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;
import com.machon.machon.R;

public class Utility {

    private static Utility utility = null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ProgressDialog pd;

    public static Utility getInstance() {
        return utility == null ? utility = new Utility() : utility;
    }

    /**
     * Internet connection check
     * @param context
     * @return
    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }*/

    /**
     * phone number check
     * @param contact
     * @return
     */
    public boolean isContactValid(String contact) {
        if (contact.matches("\\d{10}"))
            return true;
        else
            return false;
    }

    /**
     * Snack bar
     * @param view
     * @param message
     */
    public void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**
     * progress Bar
     * @param mContext
     */
    public void showProgressDialogue(Context mContext) {
        pd = new ProgressDialog(mContext, R.style.MyGravity);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);       // Set progress dialog style horizontal
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Set the progress dialog background color transparent
        pd.setCancelable(false);
        pd.show();
    }

    /**
     * Dismiss Progress
     */
    public void dismissProgress() {
        if (pd != null) {
            pd.dismiss();
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    public void permissionDialog(Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Enable permission").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void callPhoneNumber(String mobileNumber,Context context)
    {
//        String mobileNumber="9657431432";
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobileNumber));
                context.startActivity(callIntent);

            }
            else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobileNumber));
                context.startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



}
