package com.goketech.smartcommunity.view.home

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.goketech.smartcommunity.R
import com.goketech.smartcommunity.base.BaseFragment
import com.goketech.smartcommunity.constants.Constant
import com.goketech.smartcommunity.interfaces.home.HomeConstact
import com.goketech.smartcommunity.model.bean.IndexBean
import com.goketech.smartcommunity.presenter.home.HomePresenter
import com.goketech.smartcommunity.utils.MyUtils
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import android.widget.Toast

import android.widget.TextView
import com.goketech.smartcommunity.MainActivity
import com.goketech.smartcommunity.adapters.HomeRecAdapter
import com.sunfusheng.marqueeview.MarqueeView


class HomeFragment : BaseFragment<HomeConstact.View, HomeConstact.Presenter>(), HomeConstact.View {


    override val layout: Int
        get() = com.goketech.smartcommunity.R.layout.fragment_home

    override fun initView(view: View) {

    }

    override fun initData() {
       // Constant.TOKEN
        var prams = HashMap<String, String>()
        prams.put("company_id", "1")
        prams.put("community_id", "1")
        val sign = MyUtils.getSign(prams)
        prams.put("sign", sign!!)
        presenter!!.getHome(prams)

        entrance.setOnClickListener(View.OnClickListener {
            var intent = Intent()
            intent.setClass(context, KeyActivity::class.java)
            context!!.startActivity(intent)

        })
    }

    override fun initPresenter(): HomeConstact.Presenter {
        return HomePresenter()
    }

    override fun getIndexReturn(result: IndexBean) {
        val data = result!!.data
        val banner = data!!.banner
        val notice_list = data!!.notice_list
        val activity = data!!.activity

        // 通知
        var arrnotice = ArrayList<String>()

        for (noti: IndexBean.DataBean.NoticeListBean in notice_list!!) {
            arrnotice.add(noti!!.title!!)

        }
        tv_notify.startWithList(arrnotice)

        tv_notify.startWithList(
            arrnotice,
            R.anim.anim_bottom_in,
            R.anim.anim_top_out
        )
        tv_notify.setOnItemClickListener(MarqueeView.OnItemClickListener { position, textView ->
            Toast.makeText(
                context!!,
                notice_list!![position].title,
                Toast.LENGTH_SHORT
            ).show()
        })
        Log.i("tag", "" + banner!!.size)

        //轮播图
        bannerHome.setImages(banner)
        bannerHome.setImageLoader(bannerLoad())
        bannerHome.setDelayTime(3000)
        bannerHome.start()
        //最新活动rec
        rv_activity.layoutManager = LinearLayoutManager(context!!)
        val mutableList = activity as MutableList
        rv_activity.adapter = HomeRecAdapter(mutableList)


    }

    override fun onError(err: String) {

    }

    class bannerLoad : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            var bean: IndexBean.DataBean.BannerBean = path as IndexBean.DataBean.BannerBean


            Glide.with(context!!).load(bean!!.image).into(imageView!!)
        }
    }

}