package com.blood.wanandroid.net

import com.blood.wanandroid.login.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HttpApi {

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String?, @Field("password") password: String?): Observable<HttpResponse<LoginBean>>

}