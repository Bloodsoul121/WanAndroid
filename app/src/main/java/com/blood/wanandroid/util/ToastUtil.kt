package com.blood.wanandroid.util

import android.content.Context
import android.widget.Toast
import com.blood.wanandroid.app.App

class ToastUtil {

    companion object {

        fun toast(msg: String) {
            val toast = Toast.makeText(App.get(), "", Toast.LENGTH_SHORT)
            toast.setText("" + msg)
            toast.show()
        }

        fun toast(context: Context, msg: String) {
            val toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
            toast.setText("" + msg)
            toast.show()
        }

    }

}