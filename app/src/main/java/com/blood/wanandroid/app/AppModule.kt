package com.blood.wanandroid.app

import android.util.ArrayMap
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideExtras(): Map<String, Any> {
        return ArrayMap()
    }
}