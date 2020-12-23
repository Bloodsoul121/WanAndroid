package com.blood.wanandroid.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : FragmentActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment?>? {
        return fragmentInjector
    }

    fun toast(msg: String) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun <T> skipActivity(clazz: Class<T>) {
        startActivity(Intent(this, clazz))
    }

}