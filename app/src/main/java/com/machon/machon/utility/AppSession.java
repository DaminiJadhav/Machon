package com.machon.machon.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.machon.machon.model.response.LoginMechanicResponse;
import com.machon.machon.model.response.LoginResponse;
import com.machon.machon.model.response.PostGarageIssueResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppSession {
    private static final String APP_DEFAULT_LANGUAGE = "en";
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    private static AppSession appSession = null;
    public static AppSession getInstance(Context context) {
        if (appSession == null) {
            appSession = new AppSession();
            try {
                sharedPref = context.getSharedPreferences(context.getPackageName() + ".AppSession", Context.MODE_PRIVATE);
                editor = sharedPref.edit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appSession;
    }


//  userSelection


    public void setUserSelection(String str) {
        editor.putString("USER_SELECTION", str);
        editor.commit();
    }

    public String getUserSelection() {
        return sharedPref.getString("USER_SELECTION", "");
    }



//   User login response

    public void setUserLogin(LoginResponse loginResponse) {
        editor.putString("USER_LOGIN_KEY", new Gson().toJson(loginResponse));
        editor.commit();
    }

    public LoginResponse getUserLogin() {
        try {
            String userJSONString = sharedPref.getString("USER_LOGIN_KEY", null);
            if (userJSONString == null)
                return null;
            Type type = new TypeToken<LoginResponse>() {
            }.getType();
            LoginResponse loginDTO= new Gson().fromJson(userJSONString, type);
            return loginDTO;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new LoginResponse();
        }
    }


//    Mechanic Login response

    public void setMechanicLogin(LoginMechanicResponse loginResponse) {
        editor.putString("MECHANIC_LOGIN_KEY", new Gson().toJson(loginResponse));
        editor.commit();
    }

    public LoginMechanicResponse getMechanicLogin() {
        try {
            String mechanicJSONString = sharedPref.getString("MECHANIC_LOGIN_KEY", null);
            if (mechanicJSONString == null)
                return null;
            Type type = new TypeToken<LoginMechanicResponse>() {
            }.getType();
            LoginMechanicResponse mechanicloginDTO= new Gson().fromJson(mechanicJSONString, type);
            return mechanicloginDTO;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new LoginMechanicResponse();
        }
    }

    //   Garage List response

    public void setGarageList(PostGarageIssueResponse garageIssueResponse) {
        editor.putString(Constants.GARAGE_ISSUE_RESPONSE, new Gson().toJson(garageIssueResponse));
        editor.commit();
    }

    public PostGarageIssueResponse getGarageList() {
        try {
            String garageListJSONString = sharedPref.getString(Constants.GARAGE_ISSUE_RESPONSE, null);
            if (garageListJSONString == null)
                return null;
            Type type = new TypeToken<PostGarageIssueResponse>() {
            }.getType();
            PostGarageIssueResponse garageListDTO= new Gson().fromJson(garageListJSONString, type);
            return garageListDTO;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new PostGarageIssueResponse();
        }
    }


//    garageAddress

    public void setGarageAddress(String garageAddress) {
        editor.putString("GARAGE_ADDRESS", garageAddress);
        editor.commit();
    }

    public String getGarageAddress() {
        return sharedPref.getString("GARAGE_ADDRESS", "");
    }


    // select vehicle issue
    public void setSelectedCheckBox(List<String> selectedProblemCheckBox) {
        editor.putString("KEY_SELECTEDCHECKEDBOX", new Gson().toJson(selectedProblemCheckBox));
        editor.commit();
    }

    public List<String> getSelectedCheckBox() {
        try {
            String seletedcheckboxList = sharedPref.getString("KEY_SELECTEDCHECKEDBOX", null);
            if (seletedcheckboxList == null)
                return null;
            Type type = new TypeToken<List<String>>() {
            }.getType();
            List<String> seletedcheckboxLists = new Gson().fromJson(seletedcheckboxList, type);
            return seletedcheckboxLists;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ArrayList<String>();

        }
    }



//    firebase token

    public void setFirebaseToken(String str) {
        editor.putString("FIREBASE_TOKEN", str);
        editor.commit();
    }

    public String getFirebaseToken() {
        return sharedPref.getString("FIREBASE_TOKEN", "");
    }








}
