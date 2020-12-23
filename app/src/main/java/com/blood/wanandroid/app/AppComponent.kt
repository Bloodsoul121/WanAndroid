package com.blood.wanandroid.app

import com.blood.wanandroid.login.LoginModule
import com.blood.wanandroid.net.HttpModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        AppModule::class,
        ActivityModule::class,
        LoginModule::class,
        HttpModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}