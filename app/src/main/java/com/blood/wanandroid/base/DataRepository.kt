package com.blood.wanandroid.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blood.wanandroid.login.LoginBean
import com.blood.wanandroid.net.HttpApi
import com.blood.wanandroid.net.HttpResult
import com.blood.wanandroid.net.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(httpApi: HttpApi) : BaseRepository(httpApi) {

    fun login(username: String, password: String): LiveData<HttpResult<LoginBean>> {
        val liveData = MutableLiveData<HttpResult<LoginBean>>()
        liveData.value = HttpResult(status = Status.LOADING)
        httpApi.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                if (it.isSuccess()) {
                    liveData.value = HttpResult(status = Status.SUCCESS, data = it.data)
                } else {
                    liveData.value = HttpResult(status = Status.FAILURE, errorMsg = it.errorMsg)
                }
            })
        return liveData
    }

}