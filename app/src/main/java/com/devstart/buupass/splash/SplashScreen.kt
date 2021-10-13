package com.devstart.buupass.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.devstart.buupass.R
import com.devstart.buupass.auth.ui.LoginActivity
import com.devstart.buupass.home.MainActivity
import com.devstart.buupass.prefs

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            if (userExist()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 4000)
    }

    private fun userExist(): Boolean {
        val user = prefs.userPref
        return !user.equals("")
    }
}