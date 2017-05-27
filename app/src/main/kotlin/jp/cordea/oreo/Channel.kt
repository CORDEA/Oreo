package jp.cordea.oreo

import android.content.Context

/**
 * Created by Yoshihiro Tanaka on 2017/05/24.
 */
enum class Channel {
    LOW,
    HIGH;

    fun toId(context: Context): String {
        return if (this == LOW) {
            context.getString(R.string.channel_low_id)
        } else context.getString(R.string.channel_high_id)
    }
}