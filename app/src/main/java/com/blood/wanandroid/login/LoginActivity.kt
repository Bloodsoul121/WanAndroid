package com.blood.wanandroid.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.Observer
import com.blood.wanandroid.R
import com.blood.wanandroid.base.BaseActivity
import com.blood.wanandroid.bean.LoginBean
import com.blood.wanandroid.home.HomeActivity
import com.blood.wanandroid.net.Status
import com.blood.wanandroid.util.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(), password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(), password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> login()
                }
                false
            }

            login.setOnClickListener {
                login()
            }
        }
    }

    private fun login() {
        loginViewModel.login(username.text.toString(), password.text.toString())
            .observe(this@LoginActivity, Observer {
                val loginResult = it ?: return@Observer
                when (loginResult.status) {
                    Status.LOADING -> loading.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        loading.visibility = View.GONE
                        updateUiWithUser(loginResult.data!!)
                    }
                    Status.FAILURE -> {
                        loading.visibility = View.GONE
                        showLoginFailed(loginResult.errorMsg)
                    }
                    else -> loading.visibility = View.GONE
                }

                setResult(Activity.RESULT_OK)

                finish()

                skipActivity(HomeActivity::class.java)
            })
    }

    private fun updateUiWithUser(model: LoginBean) {
        val welcome = getString(R.string.welcome)
        val displayName = model.username
        ToastUtil.toast("$welcome $displayName")
    }

    private fun showLoginFailed(errorString: String?) {
        errorString?.let { ToastUtil.toast(errorString) }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}