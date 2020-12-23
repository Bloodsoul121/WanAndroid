package com.blood.wanandroid.app

import android.util.Log
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Log.i(tag, "log: >>> $message")
    }

}