package com.machon.machon.utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.machon.machon.R;

public class CustomProgressDialog extends Dialog {

    private AnimationDrawable animationDrawable;
    ProgressBar progressBar;

    public CustomProgressDialog(Context context) {
        super(context,R.style.Theme_Transparent);
        this.setContentView(R.layout.custome_progress_dialog);
        this.setCancelable(false);

        progressBar=findViewById(R.id.loading_progress_bar);
//        animationDrawable= (android.graphics.drawable.AnimationDrawable) progressBar.getIndeterminateDrawable();


    }

    @Override
    public void show() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
//                animationDrawable.start();
            }
        }, 20);
        super.show();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!hasFocus) this.dismiss();
    }
}
