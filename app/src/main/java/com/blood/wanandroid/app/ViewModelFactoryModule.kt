package com.blood.wanandroid.app

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class])
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}