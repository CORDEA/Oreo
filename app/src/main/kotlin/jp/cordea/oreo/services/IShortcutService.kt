package jp.cordea.oreo.services

import android.content.pm.ShortcutInfo

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
interface IShortcutService {

    fun addShortcut(info: ShortcutInfo)

}
