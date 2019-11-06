package com.goketech.smartcommunity.view.login.activitys

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.goketech.smartcommunity.MainActivity
import com.goketech.smartcommunity.R
import com.goketech.smartcommunity.base.BaseActivity
import com.goketech.smartcommunity.constants.Constant
import com.goketech.smartcommunity.interfaces.login.LoginConstact
import com.goketech.smartcommunity.model.bean.CodeBean
import com.goketech.smartcommunity.model.bean.LoginBean
import com.goketech.smartcommunity.presenter.login.CodePresenter
import com.goketech.smartcommunity.utils.MyUtils
import kotlinx.android.synthetic.main.activity_code.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.sign

class CodeActivity : BaseActivity<LoginConstact.VerifyView, LoginConstact.VerifyPresenter>(), View.OnClickListener,
    LoginConstact.VerifyView {
    override fun getCodeReturn(result: CodeBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    var code = ""
    var mode = "2"
    var phone = ""
    override fun initView() {
        txt_resetVerify.setOnClickListener(this)
        txt_verifyLogin.setOnClickListener(this)
        phone = intent.getStringExtra("phone")
        mode = intent.getStringExtra("mode")

    }

    override fun initData() {
        phone = intent.getStringExtra("phone")
        if (!TextUtils.isEmpty(phone)) {
            var str = getString(R.string.tips_phone_sendverify).replace("$", phone)
            txt_tips_phone.setText(str)
        }
    }

    override fun initPresenter(): LoginConstact.VerifyPresenter {
        return CodePresenter()
    }

    override val layout: Int
        get() = R.layout.activity_code


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.txt_verifyLogin ->
                sendLogin()
            R.id.txt_resetVerify ->
                getVerify()
        }
    }

    fun getVerify() {
        if (!TextUtils.isEmpty(phone) && MyUtils.checkMoblie(phone)) {
            //   presenter!!.getCode(phone)
        } else {
            showTipsShort(getString(R.string.tips_phone))
        }
    }

    fun sendLogin() {
        code = verify_code.phoneCode

        if (mode.equals("2")) {
            if (!TextUtils.isEmpty(phone)) {
                if (!MyUtils.checkMoblie(phone)) {
                    Toast.makeText(this, getString(R.string.tips_phone), Toast.LENGTH_SHORT).show()
                    return
                }
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(this, getString(R.string.tips_phone), Toast.LENGTH_SHORT).show()
                    return
                }
                var param = HashMap<String, String>()
                param.put("mode", mode)
                param.put("phone", phone)
                param.put("code", code)

                val sign = MyUtils.getSign(param)
                param.put("sign", sign!!)
                sendPhoneLogin(param)
            } else {
                Toast.makeText(this, getString(R.string.tips_phone), Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun sendPhoneLogin(phoneMap: HashMap<String, String>) {
        presenter!!.sendPhone(phoneMap)
    }

    override fun getPhoneReturn(result: LoginBean) {
        // Toast.makeText(this, result.status, Toast.LENGTH_SHORT).show()
        if (result!!.status == 0 && result!!.data != null) {
            Constant.TOKEN = result.data.token


            var intent = Intent()
            intent.setClass(context, MainActivity::class.java)
//            intent.putExtra("phone", phone)
//            intent.putExtra("mode", mode)
//            intent.putExtra("code", code)

            startActivity(intent)
        } else {
            Toast.makeText(this, "" + result.msg, Toast.LENGTH_SHORT).show()
        }


    }


}