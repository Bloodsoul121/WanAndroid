package com.blood.wanandroid.base

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val TAG = javaClass.simpleName

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    @SuppressLint("LogNotTimber")
    fun log(msg: Any?) {
        Log.i(TAG, "$msg")
    }

}