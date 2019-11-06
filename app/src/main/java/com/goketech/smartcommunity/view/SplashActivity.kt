package com.goketech.smartcommunity.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.goketech.smartcommunity.MainActivity
import com.goketech.smartcommunity.R
import com.goketech.smartcommunity.view.login.activitys.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        openLogin()
    }

    fun openLogin(){
        var intent = Intent()
        intent.setClass(this@SplashActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
