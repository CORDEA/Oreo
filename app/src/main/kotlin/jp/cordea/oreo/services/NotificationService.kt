package jp.cordea.oreo.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import jp.cordea.oreo.R

/**
 * Created by Yoshihiro Tanaka on 2017/05/24.
 */
class NotificationService(private val context: Context) : INotificationService {

    val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun setChannels() {
        notificationManager.createNotificationChannels(
                arrayListOf(
                        NotificationChannel(
                                context.getString(R.string.channel_low_id),
                                context.getString(R.string.channel_low_title),
                                NotificationManager.IMPORTANCE_LOW
                        ).apply {
                            this.description = context.getString(R.string.channel_low_description)
                            enableLights(false)
                            enableVibration(false)
                        },
                        NotificationChannel(
                                context.getString(R.string.channel_high_id),
                                context.getString(R.string.channel_high_title),
                                NotificationManager.IMPORTANCE_HIGH
                        ).apply {
                            this.description = context.getString(R.string.channel_high_description)
                            lightColor = android.graphics.Color.RED
                            enableLights(true)
                            enableVibration(true)
                        }
                )
        )
    }

    override fun post(id: Int, channelId: String, title: String, text: String) {
        val notification = Notification
                .Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .build()

        notificationManager.notify(id, notification)
    }

}
