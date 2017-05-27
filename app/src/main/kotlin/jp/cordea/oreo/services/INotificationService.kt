package jp.cordea.oreo.services

/**
 * Created by Yoshihiro Tanaka on 2017/05/24.
 */
interface INotificationService {

    fun setChannels()

    fun post(id: Int, channelId: String, title: String, text: String)

}