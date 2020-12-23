package com.blood.wanandroid.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.blood.wanandroid.R
import com.blood.wanandroid.base.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var loginViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }
}