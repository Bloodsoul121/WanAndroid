package com.blood.wanandroid.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blood.wanandroid.R
import com.blood.wanandroid.base.BaseActivity
import com.blood.wanandroid.home.HomeActivity
import com.blood.wanandroid.net.Status
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
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

                //Complete and destroy login activity once successful
                finish()

                skipActivity(HomeActivity::class.java)
            })
    }

    private fun updateUiWithUser(model: LoginBean) {
        val welcome = getString(R.string.welcome)
        val displayName = model.username

        Toast.makeText(applicationContext, "$welcome $displayName", Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(errorString: String?) {
        errorString?.let {
            Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * Extension function to simplify setting an afterTextChanged action to EditText components.
     */
    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}