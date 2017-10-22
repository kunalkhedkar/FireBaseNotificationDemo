package com.example.kunal.firebasenotification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.R.id.message;

/**
 * Created by kunal on 23/9/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MSG";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
////                scheduleJob();
//            } else {
//                // Handle message within 10 seconds
////                handleNow();
//            }
//
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        }
//
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.

    }

    @Override
    public void handleIntent(Intent intent) {
//        super.handleIntent(intent);

        Log.d(TAG, "handleIntent: "+intent.getExtras());
        String t=intent.getStringExtra("title");
        String m=intent.getStringExtra("body");

        MainActivity.info=intent.getExtras()+"";


        NotificationCompat.Builder b = new NotificationCompat.Builder(this.getApplicationContext());
        b.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("{your tiny message}")
                .setContentTitle(t)
                .setContentText(m)
                .setContentInfo("INFO");

        NotificationManager nm = (NotificationManager) this.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, b.build());


    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d(TAG, "onDeletedMessages: ");
    }
}


//https://stackoverflow.com/questions/37358462/firebase-onmessagereceived-not-called-when-app-in-background