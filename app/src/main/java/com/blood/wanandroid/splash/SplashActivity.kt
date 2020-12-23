package com.blood.wanandroid.splash

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.blood.wanandroid.R
import com.blood.wanandroid.base.BaseActivity
import com.blood.wanandroid.login.LoginActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class SplashActivity : BaseActivity() {

    lateinit var rxPermissionsDisposable: Disposable

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkPermission()
    }

    private fun checkPermission() {
        rxPermissionsDisposable = RxPermissions(this).request(
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe(Consumer {
            if (it) {
                toast("accept permissions")
                handler.postDelayed({
                    skipActivity(LoginActivity::class.java)
                    finish()
                }, 2000)
            } else {
                toast("deny permissions")
                finish();
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        rxPermissionsDisposable.dispose()
    }

}