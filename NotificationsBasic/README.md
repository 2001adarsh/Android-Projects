#### First create a Notification Channel
```
 nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            nm.createNotificationChannel(new NotificationChannel("first", "default",
                    NotificationManager.IMPORTANCE_DEFAULT));
        }
```

#### Then create the simple notification using NotificationCompat.builder
```
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
```                
