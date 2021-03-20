package com.machon.machon.activity.userselection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.machon.machon.R;
import com.machon.machon.activity.login.ActivityLoginScreen;
import com.machon.machon.utility.AppSession;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityUserSelection extends AppCompatActivity {


    ImageView ivuser,ivmechanic;
    Button btnnext;
    boolean isSelect=false;
    AppSession appSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        ButterKnife.bind(this);

        appSession=AppSession.getInstance(this);

        ivuser=findViewById(R.id.iv_user);
        ivmechanic=findViewById(R.id.iv_mechanic);
        btnnext=findViewById(R.id.btn_next);



    }


    @OnClick(R.id.iv_user)
    void user_click(){
            appSession.setUserSelection("User");
            ivuser.setBackgroundResource(R.drawable.btn_background_color);
            ivmechanic.setBackgroundResource(R.drawable.edit_text_border);
            btnnext.setVisibility(View.VISIBLE);


    }

    @OnClick(R.id.iv_mechanic)
    void mechanic_click(){
        appSession.setUserSelection("Mechanic");
        ivuser.setBackgroundResource(R.drawable.edit_text_border);
        ivmechanic.setBackgroundResource(R.drawable.btn_background_color);
        btnnext.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_next)
    void next_button(){
        Intent intent=new Intent(this, ActivityLoginScreen.class);
        startActivity(intent);
        finish();
    }

}