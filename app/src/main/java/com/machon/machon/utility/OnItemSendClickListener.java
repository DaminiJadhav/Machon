package com.machon.machon.utility;

import android.view.View;

public class OnItemSendClickListener implements View.OnClickListener{

    private  int position = 0;
    private OnClickCallback onClickCallback;
    private String type="";

    public OnItemSendClickListener(int position, OnClickCallback onClickCallback, String type){
        this.position = position;
        this.onClickCallback = onClickCallback;
        this.type = type;
    }
    @Override
    public void onClick(View view) {
        onClickCallback.onClicked(view,position,type);
    }

    public interface OnClickCallback{
        void onClicked(View view,int position,String type);
    }
}
