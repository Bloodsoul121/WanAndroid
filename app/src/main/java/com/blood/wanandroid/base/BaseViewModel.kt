package com.blood.wanandroid.base

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val TAG = javaClass.simpleName

    @SuppressLint("LogNotTimber")
    fun log(msg: Any?) {
        Log.i(TAG, "$msg")
    }

}