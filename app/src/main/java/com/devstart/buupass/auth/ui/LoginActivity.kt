package com.devstart.buupass.auth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.devstart.buupass.R
import com.devstart.buupass.auth.viewModel.LoginViewModel
import com.devstart.buupass.data.model.Failure
import com.devstart.buupass.data.model.Success
import com.devstart.buupass.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.btnLogin.setOnClickListener {
            validateUserInput()
        }
    }

    private fun validateUserInput() {
        val username = binding.txtUsername.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()

        when {
            username.isBlank() -> {
                binding.userNameLayout.error = "Username field is required"
            }
            password.isBlank() -> {
                binding.passwordLayout.error = "Password field is required"
            }
            else -> {
                viewModel.login(username, password)
                observeResponse()
            }
        }
    }

    private fun observeResponse() {
        viewModel.getUserResponse().observe(this, Observer {
            when(it) {
                is Success<*> -> {
                    Log.i("Response", it.toString())
                }
                is Failure -> {
                    Log.i("Error", it.throwable.localizedMessage)
                }
            }
        })
    }
}