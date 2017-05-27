package jp.cordea.oreo

import android.app.Application
import jp.cordea.oreo.di.AppComponent
import jp.cordea.oreo.di.AppModule
import jp.cordea.oreo.di.DaggerAppComponent
import jp.cordea.oreo.services.INotificationService
import javax.inject.Inject

/**
 * Created by Yoshihiro Tanaka on 2017/05/24.
 */
class App : Application() {

    @Inject
    lateinit var notificationService: INotificationService

    override fun onCreate() {
        super.onCreate()

        component =
                DaggerAppComponent
                        .builder()
                        .appModule(AppModule(this))
                        .build()

        component.inject(this)

        notificationService.setChannels()
    }

    companion object {
        lateinit var component: AppComponent
    }

}