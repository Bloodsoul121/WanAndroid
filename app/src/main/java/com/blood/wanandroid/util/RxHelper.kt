package com.blood.wanandroid.util

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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