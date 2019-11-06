package com.goketech.smartcommunity.view.login.activitys

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.TextureView
import android.view.View
import android.widget.Toast
import com.goketech.smartcommunity.MainActivity


import com.goketech.smartcommunity.base.BaseActivity
import com.goketech.smartcommunity.constants.Constant
import com.goketech.smartcommunity.interfaces.login.LoginConstact
import com.goketech.smartcommunity.model.bean.CodeBean
import com.goketech.smartcommunity.model.bean.LoginBean
import com.goketech.smartcommunity.presenter.login.LoginPresenter
import com.goketech.smartcommunity.utils.MyUtils
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.RequestBody
import com.tencent.mm.opensdk.modelmsg.SendAuth
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager


class LoginActivity : BaseActivity<LoginConstact.View, LoginConstact.Presenter>(), LoginConstact.View,
    View.OnClickListener {
    override fun getPassWordReturn(result: CodeBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    var mode = "2"
    var phone = ""
    var password = ""
    var code = ""
    var api: IWXAPI? = null
    override fun initView() {
        txt_loginCode.setOnClickListener(this)
        txt_loginPw.setOnClickListener(this)
        txt_getverify.setOnClickListener(this)
        txt_loginOther.setOnClickListener(this)
        var intentFilter = IntentFilter()
        intentFilter.addAction("authlogin")
        var localReceiver = LocalReceiver()
        //注册本地接收器
        registerReceiver(localReceiver, intentFilter)


    }

    override fun initData() {

    }

    override fun initPresenter(): LoginConstact.Presenter {
        return LoginPresenter()
    }


    override val layout: Int
        get() = com.goketech.smartcommunity.R.layout.activity_login


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        when (v!!.id) {
            com.goketech.smartcommunity.R.id.txt_loginCode -> {
                mode = "2"
                layout_verify.visibility = View.VISIBLE
                layout_pw.visibility = View.GONE
                txt_loginCode.isEnabled = false
                txt_loginPw.isEnabled = true
            }
            com.goketech.smartcommunity.R.id.txt_loginPw -> {
                mode = "1"
                layout_verify.visibility = View.GONE
                layout_pw.visibility = View.VISIBLE
                txt_loginCode.isEnabled = true
                txt_loginPw.isEnabled = false

            }
            com.goketech.smartcommunity.R.id.txt_login -> {
                phone = txt_phone.text.toString()
                password = txt_pw.text.toString()
                code = txt_loginCode.text.toString()

                saveLogin()
            }
            com.goketech.smartcommunity.R.id.txt_getverify -> {
                phone = txt_codePhone.text.toString()
                var param = HashMap<String, String>()
                // param.put("mode", mode)
                param.put("phone", phone)

                //获取签名
                val sign = MyUtils.getSign(param)
                //封装body
                param.put("sign", sign!!)
                presenter!!.getCode(param)

            }
            com.goketech.smartcommunity.R.id.txt_loginOther -> {
                api = WXAPIFactory.createWXAPI(this, "wx9da9b470bcb8c751", false)
                if (!api!!.isWXAppInstalled()) {
                    Toast.makeText(this, "您还未安装微信客户端！", Toast.LENGTH_SHORT).show();
                    return;
                }
                val req = SendAuth.Req()
                req.scope = "snsapi_userinfo"
                req.state = "wx_login_duzun"
                api!!.sendReq(req)

            }
        }
    }

    fun saveLogin() {

        if (mode.equals("1")) {
            if (!TextUtils.isEmpty(phone)) {
                if (!MyUtils.checkMoblie(phone)) {
                    Toast.makeText(this, getString(com.goketech.smartcommunity.R.string.tips_phone), Toast.LENGTH_SHORT)
                        .show()
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, getString(com.goketech.smartcommunity.R.string.tips_phone), Toast.LENGTH_SHORT)
                        .show()
                    return;
                }
                var param = HashMap<String, String>()
                param.put("mode", mode)
                param.put("phone", phone)
                param.put("password", password)
                val sign = MyUtils.getSign(param)
                param.put("sign", sign!!)

                sendLoginData(param)
            } else {
                Toast.makeText(this, getString(com.goketech.smartcommunity.R.string.tips_phone), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun sendLoginData(request: Map<String, String>) {
        presenter!!.login(request)
    }

    override fun getCodeReturn(result: CodeBean) {
        if (result!!.status == 0) {
            var intent = Intent()
            intent.setClass(context, CodeActivity::class.java)
            intent.putExtra("phone", phone)
            intent.putExtra("mode", mode)
            startActivity(intent)
        } else {
            Toast.makeText(this, getString(com.goketech.smartcommunity.R.string.err_phone), Toast.LENGTH_SHORT).show()
        }
    }

    override fun loginReturn(result: LoginBean) {
        if (result.status == Constant.RESULT_CODE_SUCCESS) {
            //跳转界面
            var intent = Intent()
            intent.setClass(context, MainActivity::class.java)

        } else if (result.status == Constant.RESULT_CODE_NOSETPW) {
            //提示
            Toast.makeText(this, getString(com.goketech.smartcommunity.R.string.err_pw), Toast.LENGTH_SHORT).show()
        }
    }

    private inner class LocalReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "我问问", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Constant.WXCODE!="") {
//            var turnRegister=Intent()
//            if(Constant.WXSTATE==1010){
//                intent.setClass(context, RegisterActivity::class.java)
//
//                startActivity(turnRegister)
//            }else{
//                intent.setClass(context, LoginActivity::class.java)
//                startActivity(turnRegister)
//            }



        }

    }

}