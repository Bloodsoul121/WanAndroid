package com.blood.wanandroid.base

import com.blood.wanandroid.bean.ArticleResult
import com.blood.wanandroid.bean.LoginBean
import com.blood.wanandroid.net.HttpApi
import com.blood.wanandroid.net.HttpResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(httpApi: HttpApi) : BaseRepository(httpApi) {

    fun login(username: String, password: String): Observable<HttpResponse<LoginBean>> {
        return httpApi.login(username, password)
    }

    suspend fun loadArticles(pagerNum: Int): ArticleResult {
        return httpApi.loadArticles(pagerNum)
    }

}