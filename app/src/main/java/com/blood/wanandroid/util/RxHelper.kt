package com.blood.wanandroid.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableTransformer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

class RxHelper {

    companion object {

        /**
         * 统一线程处理
         *
         * @param <T> 指定的泛型类型
         * @return FlowableTransformer
        </T> */
        fun <T> rxFlSchedulerHelper(): FlowableTransformer<T, T> {
            return FlowableTransformer { flowable: Flowable<T> ->
                flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }

        /**
         * 统一线程处理
         *
         * @param <T> 指定的泛型类型
         * @return ObservableTransformer
        </T> */
        fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable: Observable<T> ->
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }

    }

}