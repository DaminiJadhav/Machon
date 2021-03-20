package com.machon.machon.utility;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.machon.machon.R;

public class CustomDialogClass extends Dialog implements
    android.view.View.OnClickListener {

  public Activity context;
  public Dialog d;
  public Button submit;
  AppSession appSession;
  String userSelected;
  private OnCustomDialogueClick onCustomDialogueClick;
  private EditText edittxt_otp;
  private TextView txt_resend;
  private String mobileNumber;
  private TextView textNumber;


  public CustomDialogClass(Activity a, OnCustomDialogueClick onCustomDialogueClick, String mobileNumber) {
    super(a);
    // TODO Auto-generated constructor stub
    this.context = a;
    this.onCustomDialogueClick = onCustomDialogueClick;
    this.mobileNumber = mobileNumber;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.otp_custom_dialog_box);
    initUI();
  }

  private void initUI() {
    appSession=AppSession.getInstance(context);
    userSelected=appSession.getUserSelection();
    txt_resend = findViewById(R.id.txt_resend);
    edittxt_otp = findViewById(R.id.edittxt_otp);
    textNumber = findViewById(R.id.textNumber);
    textNumber.setText(mobileNumber);
    submit = (Button) findViewById(R.id.btn_submit);
    submit.setOnClickListener(this);
    txt_resend.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.btn_submit:
      Log.i("User",""+userSelected);
/*      if(userSelected.equalsIgnoreCase("User")){
        Intent intent=new Intent(context, ActivityUserSignUpScreen.class);
        getContext().startActivity(intent);
      }else{
        Intent intent=new Intent(context, ActivityMechanicSignUp.class);
        getContext().startActivity(intent);
      }*/
      String otp = edittxt_otp.getText().toString().trim();
      onCustomDialogueClick.onDialogueClick(EnumClicks.VERIFYOTP,otp);
      break;
      case R.id.txt_resend :
        onCustomDialogueClick.onDialogueClick(EnumClicks.RESENDOTP,"");
        break;
    default:
      break;
    }
/*    dismiss();*/
  }
}