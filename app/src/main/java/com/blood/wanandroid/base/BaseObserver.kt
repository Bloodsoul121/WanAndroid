package com.blood.wanandroid.base

import io.reactivex.observers.ResourceObserver

abstract class BaseObserver<T> : ResourceObserver<T>() {

    override fun onError(e: Throwable) {

    }

    override fun onComplete() {

    }
}