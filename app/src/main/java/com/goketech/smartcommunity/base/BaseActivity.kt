package com.goketech.smartcommunity.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.goketech.smartcommunity.interfaces.IBaseView
import com.goketech.smartcommunity.interfaces.IPresenter

abstract class BaseActivity<V: IBaseView,P: IPresenter<V>>:AppCompatActivity(),
    IBaseView {

    protected abstract val layout:Int

    protected var presenter:P? = null

    protected lateinit var context:Context

    protected abstract fun initView()
    protected abstract fun initData()
    protected abstract fun initPresenter():P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(layout)
        initView()
        presenter = initPresenter()
        if(presenter != null){
            presenter!!.attachView(this as V)
        }
        initData()

    }

    protected fun showTipsShort(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    protected fun showTipsLong(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun onError(err: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        if(presenter != null){
            presenter!!.detachView()
        }
    }
}