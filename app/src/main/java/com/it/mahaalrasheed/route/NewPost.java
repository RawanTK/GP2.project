package com.it.mahaalrasheed.route;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        Button enter=(Button)findViewById(R.id.button13);
        final EditText message=(EditText)findViewById(R.id.editText7);
        final TextView error=(TextView)findViewById(R.id.textView16);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getText().toString().equals("")) {
                    // error.requestFocus();
                    error.setText("The field is empty, Please enter a value");
                    error.setError("");

                } else {
                    message.setError(null);
                    new AlertDialog.Builder(NewPost.this)
                            .setMessage("are you sure you want to continue post process ?")
                            .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String n, c, coo;
                                    n = message.getText().toString();

                                    Intent intent = new Intent();
                                    PendingIntent pIntent = PendingIntent.getActivity(NewPost.this, 0, intent, 0);
                                    Notification noti = new Notification.Builder(NewPost.this)
                                            .setTicker("Ticker Title")
                                            .setContentTitle("Content Title")
                                            .setContentText("Notification content.")
                                            .setContentIntent(pIntent).getNotification();
                                    noti.flags=Notification.FLAG_AUTO_CANCEL;
                                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    notificationManager.notify(0, noti);
                                    finish();
                                    Toast.makeText(getApplicationContext(), "your notification posted successfully", Toast.LENGTH_LONG).show();


                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
            }
        });
    }

}
