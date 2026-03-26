package com.example.UWinCSHelp;

import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String courseName =
                intent.getStringExtra("course");

        NotificationManager manager =
                (NotificationManager)
                        context.getSystemService(
                                Context.NOTIFICATION_SERVICE);

        String channelId = "course_channel";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            channelId,
                            "Course Notifications",
                            NotificationManager.IMPORTANCE_HIGH);

            manager.createNotificationChannel(channel);
        }

        Notification notification =
                new NotificationCompat.Builder(context, channelId)
                        .setContentTitle("Upcoming Class")
                        .setContentText(courseName + " starts in 15 minutes")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .build();

        manager.notify((int) System.currentTimeMillis(), notification);
    }
}