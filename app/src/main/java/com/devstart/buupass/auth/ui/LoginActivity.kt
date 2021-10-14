package com.devstart.buupass.auth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.devstart.buupass.R
import com.devstart.buupass.auth.viewModel.LoginViewModel
import com.devstart.buupass.data.model.Failure
import com.devstart.buupass.data.model.PrefUser
import com.devstart.buupass.data.model.Success
import com.devstart.buupass.data.model.UserResponse
import com.devstart.buupass.databinding.ActivityLoginBinding
import com.devstart.buupass.home.MainActivity
import com.devstart.buupass.prefs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private val mContext = this

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
            validateUserInput(false)
        }

        binding.btnFireLogin.setOnClickListener {
            validateUserInput(true)
        }
    }



    private fun validateUserInput(isFirebase: Boolean) {
        val username = binding.txtUsername.text.toString().trim()
        val password: String = if(isFirebase) {
            binding.txtPassword.text.toString().trim()
        }else{
            "secret"
        }


        when {
            username.isBlank() -> {
                binding.userNameLayout.error = getString(R.string.user_name_required)
                binding.txtUsername.addTextChangedListener {
                    binding.userNameLayout.error = null
                }
            }
            password.isBlank() -> {
                binding.passwordLayout.error = getString(R.string.password_required)
                binding.txtPassword.addTextChangedListener {
                    binding.passwordLayout.error = null
                }
            }
            else -> {
                if (!isFirebase) {
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.login(username, password)
                    observeResponse()
                }else {
                    binding.progressBar.visibility = View.VISIBLE
                    loginFirebase(username, password)
                }
            }
        }
    }

    private fun loginFirebase(email: String, password: String) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            val username = email.substringBefore("@")
            if (it.isSuccessful){
                val gson = Gson()
                val user : String = gson.toJson(PrefUser(username, "",email))
                prefs.userPref = user
                binding.progressBar.visibility = View.GONE
                navigateSuccessLogin()
            }else {
                if (userNotExistException(it.exception?.message)){
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { res ->
                        if (res.isSuccessful){
                            val gson = Gson()
                            val user : String = gson.toJson(PrefUser(username, "",email))
                            prefs.userPref = user
                            binding.progressBar.visibility = View.GONE
                            navigateSuccessLogin()
                        }else{
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(mContext, res.exception?.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(mContext, "Wrong Username or Password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun userNotExistException(string: String?) : Boolean {
        return string == "There is no user record corresponding to this identifier. The user may have been deleted."
    }

    private fun navigateSuccessLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun observeResponse() {
        viewModel.getUserResponse().observe(this, Observer {
            when(it) {
                is Success<*> -> {
                    val res = it.data as UserResponse
                    val username = res.data.first_name + " " + res.data.last_name
                    val gson = Gson()
                    val user : String = gson.toJson(PrefUser(username, res.data.avatar,res.data.email))
                    prefs.userPref = user
                    binding.progressBar.visibility = View.GONE
                    navigateSuccessLogin()
                }
                is Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "An error occurred while trying to log in, please try again later", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}