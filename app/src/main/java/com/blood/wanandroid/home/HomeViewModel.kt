package com.blood.wanandroid.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.blood.wanandroid.base.BaseViewModel
import com.blood.wanandroid.base.DataRepository
import com.blood.wanandroid.bean.DataX
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

    private val pageSize = 20

    fun getPager() = Pager(PagingConfig(
        pageSize = pageSize,
        prefetchDistance = 5,
        enablePlaceholders = false,
        initialLoadSize = pageSize
    ), initialKey = 0, pagingSourceFactory = {
        object : PagingSource<Int, DataX>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataX> {
                return try {
                    val pagerNum = params.key ?: 0
                    val result = dataRepository.loadArticles(pagerNum)
                    val prevKey = if (pagerNum <= 0) null else pagerNum - 1
                    val expectNextKey = pagerNum + 1
                    val nextKey = if (expectNextKey > 3) null else expectNextKey
                    LoadResult.Page(result.data.datas, prevKey, nextKey)
                } catch (e: Exception) {
                    e.printStackTrace()
                    LoadResult.Error(e)
                }
            }
        }
    }).flow

    private val mOnSelect = PublishSubject.create<DataX>()

    val onSelect: Observable<DataX>
        get() = mOnSelect

    fun select(article: DataX) {
        mOnSelect.onNext(article)
    }

}