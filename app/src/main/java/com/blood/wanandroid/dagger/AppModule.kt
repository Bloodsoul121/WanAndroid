package com.blood.wanandroid.dagger

import android.util.ArrayMap
import com.blood.wanandroid.dagger.ViewModelModule
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