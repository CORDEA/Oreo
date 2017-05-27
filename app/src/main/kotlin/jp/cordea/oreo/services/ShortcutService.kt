package jp.cordea.oreo.services

import android.app.PendingIntent
import android.content.Context
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
class ShortcutService(private val context: Context) : IShortcutService {

    override fun addShortcut(info: ShortcutInfo) {
        val manager = context.getSystemService(ShortcutManager::class.java)

        if (!manager.isRequestPinShortcutSupported) {
            return
        }

        val intent = manager.createShortcutResultIntent(info)
        val callback = PendingIntent.getBroadcast(context, 0, intent, 0)

        manager.requestPinShortcut(info, callback.intentSender)
    }

}
