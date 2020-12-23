package com.blood.wanandroid.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blood.wanandroid.R
import com.blood.wanandroid.base.BaseObserver
import com.blood.wanandroid.base.BaseViewModel
import com.blood.wanandroid.base.DataRepository
import com.blood.wanandroid.net.HttpResponse
import com.blood.wanandroid.net.HttpResult
import com.blood.wanandroid.net.Status
import com.blood.wanandroid.util.RxHelper
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun login(username: String, password: String): LiveData<HttpResult<LoginBean>> {
        val liveData = MutableLiveData<HttpResult<LoginBean>>()
        liveData.value = HttpResult(status = Status.LOADING)
        val disposable =
            dataRepository.login(username, password)
                .compose(RxHelper.rxSchedulerHelper())
                .subscribeWith(object : BaseObserver<HttpResponse<LoginBean>>() {
                    override fun onNext(httpResponse: HttpResponse<LoginBean>) {
                        if (httpResponse.isSuccess()) {
                            liveData.value =
                                HttpResult(status = Status.SUCCESS, data = httpResponse.data)
                        } else {
                            liveData.value = HttpResult(
                                status = Status.FAILURE, errorMsg = httpResponse.errorMsg
                            )
                        }
                    }
                })
        compositeDisposable.add(disposable)
        return liveData
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}