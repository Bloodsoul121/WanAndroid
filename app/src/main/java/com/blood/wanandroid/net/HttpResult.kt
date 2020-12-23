package com.blood.wanandroid.net

data class HttpResult<T>(
    val status: Status = Status.LOADING,
    var errorMsg: String? = null,
    var data: T? = null
)



