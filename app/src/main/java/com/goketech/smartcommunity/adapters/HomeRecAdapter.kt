package com.goketech.smartcommunity.adapters

import android.content.Context
import android.media.Image
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.goketech.smartcommunity.R
import com.goketech.smartcommunity.apps.MyApp
import com.goketech.smartcommunity.base.BaseAdapter
import com.goketech.smartcommunity.model.bean.IndexBean
import java.security.AccessControlContext

class HomeRecAdapter(mDatas: MutableList<IndexBean.DataBean.ActivityBean>) :
    BaseAdapter<IndexBean.DataBean.ActivityBean>(mDatas) {

    override val layoutId: Int

        get() = R.layout.layout_home_new_activity_item

    override fun bindData(holder: BaseViewHolder, position: Int, t: IndexBean.DataBean.ActivityBean) {
        val img = holder.getView(R.id.iv_activity) as ImageView
        val title = holder.getView(R.id.tv_activity_title) as TextView
        val remark = holder.getView(R.id.tv_activity_remark) as TextView
        val time = holder.getView(R.id.tv_activity_apply_time) as TextView
        Glide.with(mContext).load(mDatas!![position].image).into(img)
        title.text = mDatas!![position].title
        remark.text = mDatas!![position].remark
        time.text = mDatas!![position].begin_time + "-" + mDatas!![position].end_time
    }
}
