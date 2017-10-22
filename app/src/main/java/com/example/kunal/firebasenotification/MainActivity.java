package com.example.kunal.firebasenotification;


import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
//import okhttp3.Request;
import okhttp3.RequestBody;
//import okhttp3.Response;
import us.raudi.pushraven.FcmResponse;
import us.raudi.pushraven.Notification;
import us.raudi.pushraven.Pushraven;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    public static String info="msg not found";
            TextView showdata;
    private String token=" ";

    private final String sk="your server legacy key";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.d("***", "Tokan ------ "+token);

        showdata= (TextView) findViewById(R.id.Showdata);


    }

    public void sendNoti(View view) {

        Pushraven.setKey("AIzaSyB3QtfiNcbAHYSmylC5dcP1n_-q81xqyv0");
        sendNotification(token);
        sendFCMPush();

    }

    public void done(View view) {

        showdata.setText(info);
        Toast.makeText(this, ""+info, Toast.LENGTH_SHORT).show();
    }





    private void sendNotification(final String reg_token) {

//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    JSONObject json = new JSONObject();
//                    JSONObject dataJson = new JSONObject();
//                    dataJson.put("body", "Hi this is sent from device to device");
//                    dataJson.put("title", "dummy title");
//                    json.put("notification", dataJson);
//                    json.put("to", token);
//                    RequestBody body = RequestBody.create(JSON, json.toString());
//                    Request request = new Request.Builder()
//                            .header("Authorization", "key=" +sk )
//                            .url("https://fcm.googleapis.com/fcm/send")
//                            .post(body)
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String finalResponse = response.body().string();
//                } catch (Exception e) {
//                    //Log.d(TAG,e+"");
//                }
//                return null;
//            }
//        }.execute();


//        further if you want to send message to a particular topic, replace 'regToken' in json like this
//        json.put("to","/topics/foo-bar")
//        and at last don't forget to add INTERNET permission in your AndroidManifest.xml.

    }


    private void sendFCMPush() {

        final String Legacy_SERVER_KEY = sk;
        String msg = "kunal this is test message,.,,.,.";
        String title = "kunal my title";
//        String token = FCM_RECEIVER_TOKEN;

        JSONObject obj = null;
        JSONObject objData = null;
        JSONObject dataobjData = null;

        try {
            obj = new JSONObject();
            objData = new JSONObject();

            objData.put("body", msg);
            objData.put("title", title);
            objData.put("sound", "default");
            objData.put("icon", "icon_name"); //   icon_name image must be there in drawable
            objData.put("tag", token);
            objData.put("priority", "high");

            dataobjData = new JSONObject();
            dataobjData.put("text", msg);
            dataobjData.put("title", title);

            obj.put("to", token);
            //obj.put("priority", "high");

            obj.put("notification", objData);
            obj.put("data", dataobjData);
            Log.e("!_@rj@_@@_PASS:>", obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("!_@@_SUCESS", response + "");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("!_@@_Errors--", error + "");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "key=" + Legacy_SERVER_KEY);
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        int socketTimeout = 1000 * 60;// 60 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        requestQueue.add(jsObjRequest);
    }


    public void reg_asus(View view) {

        FirebaseMessaging.getInstance().subscribeToTopic("asus");
    }

    public void reg_intex(View view) {
        FirebaseMessaging.getInstance().subscribeToTopic("intex");
    }





    public void asus(View view) {

        final String Legacy_SERVER_KEY = sk;
        String msg = "Hello asus,.,,.,.";
        String title = "kunal my title ASUS";
//        String token = FCM_RECEIVER_TOKEN;

        JSONObject obj = null;
        JSONObject objData = null;
        JSONObject dataobjData = null;

        try {
            obj = new JSONObject();
            objData = new JSONObject();

            objData.put("body", msg);
            objData.put("title", title);
            objData.put("sound", "default");
            objData.put("icon", "icon_name"); //   icon_name image must be there in drawable
            objData.put("tag", token);
            objData.put("priority", "high");

            dataobjData = new JSONObject();
            dataobjData.put("text", msg);
            dataobjData.put("title", title);

            obj.put("to", "/topics/asus");
            //obj.put("priority", "high");

            obj.put("notification", objData);
            obj.put("data", dataobjData);
            Log.e("!_@rj@_@@_PASS:>", obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("!_@@_SUCESS", response + "");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("!_@@_Errors--", error + "");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "key=" + Legacy_SERVER_KEY);
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        int socketTimeout = 1000 * 60;// 60 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        requestQueue.add(jsObjRequest);


    }

    public void intex(View view) {

        final String Legacy_SERVER_KEY = sk;
        String msg = "Hello Intex,.,,.,.";
        String title = "kunal my title Intex";
//        String token = FCM_RECEIVER_TOKEN;

        JSONObject obj = null;
        JSONObject objData = null;
        JSONObject dataobjData = null;

        try {
            obj = new JSONObject();
            objData = new JSONObject();

            objData.put("body", msg);
            objData.put("title", title);
            objData.put("sound", "default");
            objData.put("icon", "icon_name"); //   icon_name image must be there in drawable
            objData.put("tag", token);
            objData.put("priority", "high");

            dataobjData = new JSONObject();
            dataobjData.put("text", msg);
            dataobjData.put("title", title);

            obj.put("to", "/topics/intex");
            //obj.put("priority", "high");

            obj.put("notification", objData);
            obj.put("data", dataobjData);
            Log.e("!_@rj@_@@_PASS:>", obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("!_@@_SUCESS", response + "");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("!_@@_Errors--", error + "");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "key=" + Legacy_SERVER_KEY);
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        int socketTimeout = 1000 * 60;// 60 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        requestQueue.add(jsObjRequest);


    }

    public void both(View view) {
    }




    class student{
        public student() {
        }

        public void hh(){

//       Notification raven = new Notification();



            Notification raven = new Notification();
            raven.title("MyTitle")
                    .text("Hello World!")
                    .color("#ff0000")
                    .to(token);

            Pushraven.setNotification(raven); // if not already set
            Pushraven.push();


//        JSONObject body = new JSONObject();
//        JSONObject data = new JSONObject();
//        try {
//
//
//            data.put("sender", "from kunal");
//            data.put("receiver", "to kang");
//            data.put("message", message);
//            body.put("data", data);
//
//            Notification raven = new Notification();
//            raven
//                    .data(hash)
//                    // .priority(10)
//                    .to(token);

//            raven.body("this is testing msg")
//            .to(token);
//
//            FcmResponse response1 = Pushraven.push(raven);

//        System.out.println(response1);
//        json.put("response", ""+response1);
//        json.put("info", "success");
            raven.clear();
            raven.clearAttributes();
            raven.clearTargets();

//        }catch (Exception e){
//            Toast.makeText(this, "Exception occur "+e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//        Toast.makeText(this, "fun call", Toast.LENGTH_SHORT).show();
        }
    }
}