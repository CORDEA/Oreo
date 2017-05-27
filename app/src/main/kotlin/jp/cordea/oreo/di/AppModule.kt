package jp.cordea.oreo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import jp.cordea.oreo.services.INotificationService
import jp.cordea.oreo.services.IShortcutService
import jp.cordea.oreo.services.NotificationService
import jp.cordea.oreo.services.ShortcutService
import javax.inject.Singleton

/**
 * Created by Yoshihiro Tanaka on 2017/05/24.
 */
@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideNotificationService(context: Context): INotificationService {
        return NotificationService(context)
    }

    @Provides
    @Singleton
    fun provideShortcutService(context: Context): IShortcutService {
        return ShortcutService(context)
    }
}