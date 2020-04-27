package com.adarsh.notificationsbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2, button3;
    NotificationManager nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            nm.createNotificationChannel(new NotificationChannel("first", "default",
                    NotificationManager.IMPORTANCE_DEFAULT));
        }

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                NotificationCompat.Builder firstType =new  NotificationCompat.Builder(this,
                        "first")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentText("Hello this is description")
                        .setContentTitle("My App Title")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
               nm.notify(1, firstType.build());
                // 1 is a notification Id which is different for each Notification.
                //If this id is same as other builder then it will override with the difference
                // of priority.
                break;

            case R.id.button2:
                Intent it = new Intent();
                it.setAction(Intent.ACTION_VIEW);
                it.setData(Uri.parse("https://www.google.com"));

                PendingIntent pit = PendingIntent.getActivity(this,123,it,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder secondType = new NotificationCompat.Builder(this,
                        "first")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentText("Hello this is second description")
                        .setContentTitle("My App Title")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pit);

                nm.notify(2,secondType.build());

            case R.id.button3:

                Intent it2 = new Intent();
                it2.setAction(Intent.ACTION_VIEW);
                it2.setData(Uri.parse("https://www.google.com"));

                PendingIntent pit2 = PendingIntent.getActivity(this,123,it2,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder thirdType = new NotificationCompat.Builder(this,
                        "first")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentText("Hello this is third description")
                        .setContentTitle("My App Title")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pit2)
                        .addAction(R.drawable.ic_launcher_foreground, "Click Me!", pit2);

                nm.notify(3 , thirdType.build());
                break;
        }
    }
}
