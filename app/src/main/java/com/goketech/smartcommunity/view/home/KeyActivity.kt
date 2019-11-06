package com.goketech.smartcommunity.view.home

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.goketech.smartcommunity.R
import com.goketech.smartcommunity.utils.MyUtils
import java.util.HashMap

class KeyActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        initView()
    }

    fun initView() {
        val findViewById = findViewById<ImageView>(R.id.iv_qr_code)
        var si = HashMap<String, String>()
        si.put("wocao", "卧槽,好残忍")


        val sign = MyUtils.getSign(si)
        Glide.with(this).load(
            "http://qr.topscan.com/api.php?text=" + sign
                    + "ZbgD4vQLntiZ0xLqizJJEfMdEwcBpO-xehydtS6ZoWPQTtNKvFSGQ11BAVrKAdbK6MzSq8RPMnc="
        ).into(findViewById)
    }
}