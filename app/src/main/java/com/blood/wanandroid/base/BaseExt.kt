package com.blood.wanandroid.base

import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

fun View.getLifecycleOwner(): LifecycleOwner {
    var context: Context = context
    while (context !is LifecycleOwner) {
        context = (context as ContextWrapper).baseContext
    }
    return context
}

fun View.getFragmentActivity(): FragmentActivity {
    var context: Context = context
    while (context !is FragmentActivity) {
        context = (context as ContextWrapper).baseContext
    }
    return context
}

// 这个方法只适合空参的 ViewModel
@MainThread
inline fun <reified VM : ViewModel> viewModel(viewModelStoreOwner: ViewModelStoreOwner): Lazy<VM> {
    return ViewModelLazy(VM::class,
        { viewModelStoreOwner.viewModelStore },
        { return@ViewModelLazy ViewModelProvider.NewInstanceFactory() })
}