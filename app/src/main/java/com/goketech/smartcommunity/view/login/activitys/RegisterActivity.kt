package com.goketech.smartcommunity.view.login.activitys

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.goketech.smartcommunity.MainActivity
import com.goketech.smartcommunity.R
import com.goketech.smartcommunity.base.BaseActivity
import com.goketech.smartcommunity.interfaces.login.LoginConstact
import com.goketech.smartcommunity.model.bean.CodeBean
import com.goketech.smartcommunity.model.bean.LoginBean
import com.goketech.smartcommunity.presenter.login.LoginPresenter
import com.goketech.smartcommunity.utils.MyUtils
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.android.synthetic.main.layout_register.view.*

class RegisterActivity : BaseActivity<LoginConstact.View, LoginConstact.Presenter>(), LoginConstact.View,
    View.OnClickListener {
    override fun getPassWordReturn(result: CodeBean) {
        Toast.makeText(context, "" + result!!.msg, Toast.LENGTH_LONG).show()
    }

    var pw = ""
    var repw = ""
    var code = ""

    var phone = ""


    override val layout: Int = R.layout.layout_register


    override fun initView() {
        phone = intent.getStringExtra("phone")
        code = intent.getStringExtra("code")
        pw_txt_verifyLogin.setOnClickListener(this)
        pw_txt_skip.setOnClickListener(this)


    }

    override fun initData() {

    }

    override fun initPresenter(): LoginConstact.Presenter {
        return LoginPresenter()
    }

    override fun loginReturn(result: LoginBean) {

    }

    override fun getCodeReturn(result: CodeBean) {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.pw_txt_verifyLogin -> {
                pw = pw_txt_tips_pw.text.toString()
                repw = pw_txt_tips_repw.text.toString()
                if (pw.equals(repw)) {
                    Toast.makeText(context, "两次密码不一致", Toast.LENGTH_LONG).show()
                    return
                }
                var setpw = HashMap<String, String>()
                setpw.put("phone", phone)
                setpw.put("code", code)
                setpw.put("password", pw)
                setpw.put("c_password", repw)
                val sign = MyUtils.getSign(setpw)
                setpw.put("sign", sign!!)
                presenter!!.setPassWord(setpw)

            }

            R.id.pw_txt_skip -> {
                var intent = Intent()
                intent.setClass(context, MainActivity::class.java)
            }
        }


    }
}