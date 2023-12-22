package com.inhauniv.hackathon.data

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.ui.home.HomeActivity

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken : $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if(message.notification != null) {
            Log.d(TAG, "onMessageReceived: ${message.notification?.title}")
            val title = message.notification?.title.orEmpty()
            val body = message.notification?.body.orEmpty()

            val intent = Intent(this, HomeActivity::class.java).addFlags(FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_IMMUTABLE)

            val channelId = "ChannelID"
            val mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelName = "ChannelName"
            val channelDescription = "ChannelDescription"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
                enableLights(true)
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 100, 200)
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }
            mManager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, channelId).apply {
                setSmallIcon(R.drawable.mascot)
                setAutoCancel(true)
                setDefaults(Notification.DEFAULT_ALL)
                setWhen(System.currentTimeMillis())
                setContentTitle(title)
                setContentText(body)
                setContentIntent(pendingIntent)
            }
            mManager.notify(0, builder.build())
        }
    }

    companion object {
        const val TAG = "FCMService"
    }
}
