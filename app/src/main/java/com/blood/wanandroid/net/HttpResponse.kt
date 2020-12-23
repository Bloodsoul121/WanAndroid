package com.blood.wanandroid.net

import com.google.gson.annotations.SerializedName

class HttpResponse<T> {

    companion object {
        const val RESULT_SUCCESS = 0
    }

    @SerializedName("errorCode")
    val resultCode = 0

    val errorMsg: String? = null

    val data: T? = null

    fun isSuccess(): Boolean {
        return resultCode == RESULT_SUCCESS
    }

    override fun toString(): String {
        return "Result(resultCode=$resultCode, errorMsg=$errorMsg, data=$data)"
    }

}