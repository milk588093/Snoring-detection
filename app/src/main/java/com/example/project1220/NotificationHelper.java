package com.example.project1220;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.project1220.ui.home.BTDialog2;
import com.example.project1220.ui.home.HomeFragment;

public class NotificationHelper extends ContextWrapper
{
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel()
    {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager()
    {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification()
    {
       // Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //HomeFragment.alarm11.setChecked(false);
        if(HomeFragment.bt11.isChecked())
    {
        BTDialog2.stopConnection();
        HomeFragment.bt11.setChecked(false);
    }
        else
            {
               // BTDialog2.stopConnection();
            }

        if(HomeFragment.alarm11.isChecked())
        {

            HomeFragment.alarm11.setChecked(false);
        }
        else
            {


            }
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("鬧鐘")
                .setContentText("快點起床")
                .setSmallIcon(R.drawable.ic_list);

               // .setSound(alarmSound);
    }
}