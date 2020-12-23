package com.blood.wanandroid;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

public class Test {

    public static void main(String[] args) {

        Observable.just(1)
                .subscribeWith(new ResourceObserver<Integer>() {
                    @Override
                    public void onNext(@NonNull Integer integer) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
