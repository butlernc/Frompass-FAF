package com.noahbutler.mrdiscovery.Network;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah Butler on 8/19/2015.
 */
public class Connector extends AsyncTask<String, Integer, Object> {

    private static String URL_NEW_USER = "http://104.236.237.151/users/";
    public static String URL_GET_LIST = "http://104.236.237.151/users/";


    @Override
    protected Object doInBackground(String... strings) {

        if(strings[0].contains("new_user")) {
            newUser(strings[1], strings[2]);
        }else if(strings[0].contains("request_gps")) {
            requestGPS(strings[1], strings[2], strings[3]);
        }else if(strings[0].contains("get_list")) {
            getList();
        }else if(strings[0].contains("makeFindable")) {
            makeFindable(strings[1], strings[2], strings[3]);
        }else if(strings[0].contains("add_friend")) {
            addFriend(strings[1], strings[2], strings[3]);
        }else if(strings[0].contains("login")) {
            return login();
        }

        return null;
    }

    private void newUser(String...strings) {
        String username = strings[0];
        String password = strings[1];

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("type", "create"));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));

        ServiceHandler serviceHandler = new ServiceHandler();
        String json = serviceHandler.makeServiceCall(URL_NEW_USER, serviceHandler.POST, params);
        if(json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);
                boolean error = jsonObj.getBoolean("error");
                if(!error) {
                    Log.e("User added", "> " + jsonObj.getString("output"));
                }else {
                    Log.e("Add Prediction Error: ", "> " + jsonObj.getString("output"));
                }
            } catch (JSONException e) {
                Log.e("JSON ERROR", "> " + e.toString());
            }
        }else{
            Log.e("JSON_DATA", "JSON data error!");
        }
    }

    private Boolean requestGPS(String...strings) {

        String username_requester = strings[0];
        String username_requested = strings[1];

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("type", "gps_request"));
        params.add(new BasicNameValuePair("username_requester", username_requester));
        params.add(new BasicNameValuePair("username_requested", username_requested));

        ServiceHandler serviceHandler = new ServiceHandler();
        String json = serviceHandler.makeServiceCall(URL_NEW_USER, serviceHandler.POST, params);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            return jsonObject.getBoolean("worked");
        }catch (JSONException e) {
            Log.e("JSON ERROR", "-->" + e.toString());
            return null;
        }
    }

    private void getList() {

        GetMethod getMethod = new GetMethod();
        getMethod.getJSONFromUrl(URL_GET_LIST);


//        ServiceHandler serviceHandler = new ServiceHandler();
//        String json = serviceHandler.makeServiceCall(URL_GET_LIST, serviceHandler.GET);
//        if(json != null) {
//            try {
//                JSONObject jsonObj = new JSONObject(json);
//                boolean error = jsonObj.getBoolean("error");
//                if(!error) {
//                    Log.e("User added", "> " + jsonObj.getString("output"));
//                }else {
//                    Log.e("Add Prediction Error: ", "> " + jsonObj.getString("output"));
//                }
//            } catch (JSONException e) {
//                Log.e("JSON ERROR", "> " + e.toString());
//            }
//        }else{
//            Log.e("JSON_DATA", "JSON data error!");
//        }
    }

    private Boolean login(String...strings) {
        String username = strings[0];
        String password = strings[1];

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));

        ServiceHandler serviceHandler = new ServiceHandler();
        String json = serviceHandler.makeServiceCall(URL_NEW_USER, serviceHandler.POST, params);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            return jsonObject.getBoolean("worked");
        }catch (JSONException e) {
            Log.e("JSON ERROR", "-->" + e.toString());
            return null;
        }
    }

    private void makeFindable(String...strings) {

    }

    private void addFriend(String...strings) {

    }


}
