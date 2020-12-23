package com.blood.wanandroid.dagger.support_viewmodel

import androidx.lifecycle.ViewModelProvider
import com.blood.wanandroid.dagger.ViewModelModule
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class])
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}