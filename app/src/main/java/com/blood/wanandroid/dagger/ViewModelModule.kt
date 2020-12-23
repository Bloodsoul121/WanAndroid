package com.blood.wanandroid.dagger

import androidx.lifecycle.ViewModel
import com.blood.wanandroid.dagger.annotation.ViewModelKey
import com.blood.wanandroid.home.HomeViewModel
import com.blood.wanandroid.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

}