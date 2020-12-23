package com.blood.wanandroid.base

import com.blood.wanandroid.login.LoginBean
import com.blood.wanandroid.net.HttpApi
import com.blood.wanandroid.net.HttpResponse
import com.blood.wanandroid.net.HttpResult
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(httpApi: HttpApi) : BaseRepository(httpApi) {

    fun login(username: String, password: String): Observable<HttpResponse<LoginBean>> {
        return httpApi.login(username, password)
    }

}