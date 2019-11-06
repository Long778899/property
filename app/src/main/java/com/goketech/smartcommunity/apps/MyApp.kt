package com.goketech.smartcommunity.apps

import android.app.Application
import android.content.Context

class MyApp : Application() {

    //创建静态变量
    companion object {
        //创建一个初始化的变量
        lateinit var myApp: MyApp
        lateinit var mycontext: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        myApp = this
        mycontext = this
    }


}