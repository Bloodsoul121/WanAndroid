package com.blood.wanandroid.app

import com.blood.wanandroid.BuildConfig
import com.blood.wanandroid.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : DaggerApplication() {

    companion object {
        var app: App? = null
        fun get(): App {
            return app!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}