### Alarms are not used, Best is to practice Job Scheduler

```
 Intent it = new Intent(getBaseContext(), Main2Activity.class);

                PendingIntent pit = PendingIntent.getActivity(getBaseContext(), 123, it,
                        PendingIntent.FLAG_ONE_SHOT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME,
                        SystemClock.elapsedRealtime()+60000, pit);

```

#### Repeating Alarms
```
 Intent it = new Intent(getBaseContext(), Main2Activity.class);

                PendingIntent pit = PendingIntent.getActivity(getBaseContext(), 123, it,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            //    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
              //          SystemClock.elapsedRealtime()+6000, pit);

                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                        SystemClock.elapsedRealtime()+6000, 6000,pit);
