package com.blood.wanandroid.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

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
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}