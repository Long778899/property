package com.goketech.smartcommunity.presenter.login

import com.goketech.smartcommunity.base.BasePresenter
import com.goketech.smartcommunity.commonents.CommonSubscriber
import com.goketech.smartcommunity.commonents.RxUtils
import com.goketech.smartcommunity.interfaces.login.LoginConstact
import com.goketech.smartcommunity.model.HttpManager
import com.goketech.smartcommunity.model.bean.CodeBean
import com.goketech.smartcommunity.model.bean.LoginBean

class CodePresenter : BasePresenter<LoginConstact.VerifyView>(), LoginConstact.VerifyPresenter {

    override fun getCode(phone: Map<String, String>) {
        addSubscribe(
            HttpManager.getMyApi().getSmsCode(phone)
                .compose(RxUtils.rxScheduler<CodeBean>())
                .subscribeWith(object : CommonSubscriber<CodeBean>(mView!!) {
                    override fun onNext(t: CodeBean?) {
                        mView!!.getCodeReturn(t!!)
                    }

                })
        )
    }

    override fun sendPhone(phone: Map<String, String>) {
        addSubscribe(
            HttpManager.getMyApi().login(phone)
                .compose(RxUtils.rxScheduler<LoginBean>())
                .subscribeWith(object : CommonSubscriber<LoginBean>(mView!!) {
                    override fun onNext(t: LoginBean?) {
                        mView!!.getPhoneReturn(t!!)
                    }

                })
        )
    }

}