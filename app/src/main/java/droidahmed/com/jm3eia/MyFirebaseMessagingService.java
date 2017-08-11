package droidahmed.com.jm3eia;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.model.Notification;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed radwan on 4/10/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            Log.d("uuuu",remoteMessage.toString());
            if (remoteMessage.getData().size() > 0) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                Log.d("uuuu", remoteMessage.getData().toString() + "   "+ "   "
                        + remoteMessage.toString());
           sendNotification(remoteMessage.getData().get("body"),
                   remoteMessage.getData().get("title"));
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                Notification notification = new Notification();
                notification.setContent(remoteMessage.getData().get("body"));
                notification.setTitle(remoteMessage.getData().get("title"));
                databaseHelper.createNotification(notification);
            }
        } catch (Exception e) {
            Log.d("uuuiiii",e.toString());

        }

    }

    private void sendNotification(String content, String type) {
        Intent intent = new Intent(this, MainActivity.class);
         intent.putExtra("notify","notify");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Drawable myDrawable = getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap myBitmap = ((BitmapDrawable)myDrawable).getBitmap();

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(type)
                .setContentText(content)
                .setLargeIcon(myBitmap)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

}
