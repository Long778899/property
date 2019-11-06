package com.goketech.smartcommunity.wxapi

import android.app.Activity
import android.app.AppComponentFactory
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast


import com.goketech.smartcommunity.constants.Constant
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory

class WXEntryActivity : AppCompatActivity(), IWXAPIEventHandler {
    private var api: IWXAPI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        api = WXAPIFactory.createWXAPI(this, "wx9da9b470bcb8c751", false)
        api!!.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        api!!.handleIntent(intent, this)
        finish()
    }

    override fun onReq(baseReq: BaseReq) {}

    override fun onResp(baseResp: BaseResp) {
        var result = ""
        Toast.makeText(
            this,
            baseResp.errCode.toString() + "-" + baseResp.errStr + "openId" + baseResp.openId + "code",
            Toast.LENGTH_LONG
        ).show()
        when (baseResp.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                val code = (baseResp as SendAuth.Resp).code

                Constant.WXCODE = code
                val WxSp = applicationContext.getSharedPreferences("code", Context.MODE_PRIVATE)
                val WxSpEditor = WxSp.edit()
                WxSpEditor.putString("code", code)
                WxSpEditor.apply()
                val intent = Intent()//intent.setAction("authlogin");
                startActivity(intent)



                finish()
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                result = "发送取消"
                Toast.makeText(this, result, Toast.LENGTH_LONG).show()
                finish()
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                result = "发送被拒绝"
                Toast.makeText(this, result, Toast.LENGTH_LONG).show()
                finish()
            }
            else -> {
                result = "发送返回"
                Toast.makeText(this, result, Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }


}
