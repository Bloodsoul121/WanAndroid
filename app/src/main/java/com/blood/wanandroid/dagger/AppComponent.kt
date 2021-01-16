package com.blood.wanandroid.dagger

import com.blood.wanandroid.app.App
import com.blood.wanandroid.dagger.support_viewmodel.ViewModelFactoryModule
import com.blood.wanandroid.login.LoginModule
import com.blood.wanandroid.net.HttpModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        AppModule::class,
        ActivityModule::class,
        HttpModule::class,
        LoginModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}