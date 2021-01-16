package com.blood.wanandroid.net

import com.blood.wanandroid.bean.ArticleResult
import com.blood.wanandroid.bean.LoginBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface HttpApi {

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String?, @Field("password") password: String?): Observable<HttpResponse<LoginBean>>

    @GET("article/list/{pagerNum}/json")
    suspend fun loadArticles(@Path("pagerNum") pagerNum: Int): ArticleResult

}