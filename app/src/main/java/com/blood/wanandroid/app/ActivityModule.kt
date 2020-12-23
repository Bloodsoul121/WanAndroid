package com.blood.wanandroid.app

import com.blood.wanandroid.home.HomeActivity
import com.blood.wanandroid.login.LoginActivity
import com.blood.wanandroid.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun ContributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun ContributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun ContributeHomeActivity(): HomeActivity

}