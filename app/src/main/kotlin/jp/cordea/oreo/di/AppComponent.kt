package jp.cordea.oreo.di

import dagger.Component
import jp.cordea.oreo.App
import jp.cordea.oreo.NotificationActivity
import jp.cordea.oreo.PinActivity
import javax.inject.Singleton

/**
 * Created by Yoshihiro Tanaka on 2017/05/24.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)

    fun inject(activity: NotificationActivity)

    fun inject(activity: PinActivity)

}
