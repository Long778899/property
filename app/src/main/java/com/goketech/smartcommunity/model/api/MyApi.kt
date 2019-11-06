package com.goketech.smartcommunity.model.api

import com.goketech.smartcommunity.model.bean.CodeBean
import com.goketech.smartcommunity.model.bean.IndexBean
import com.goketech.smartcommunity.model.bean.LoginBean
import com.goketech.smartcommunity.model.bean.NoticeBean
import io.reactivex.Flowable
import retrofit2.http.*

interface MyApi {
    //--------------------登录使用接口----------------------


    @POST("login")
    @FormUrlEncoded
    fun login(@FieldMap param: Map<String, String>): Flowable<LoginBean>

    @POST("smsCode")
    @FormUrlEncoded
    fun getSmsCode(@FieldMap param: Map<String, String>): Flowable<CodeBean>


    @POST("setPassword")
    @FormUrlEncoded
    fun setPassWord(@FieldMap param: Map<String, String>): Flowable<CodeBean>

    //----------------------首页使用接口---------------------
//首页数据
    @POST("appInxde")
    @FormUrlEncoded
    fun getHome(@FieldMap param: Map<String, String>): Flowable<IndexBean>

    //物业公告列表
    @POST("getNoticeMessage")
    @FormUrlEncoded
    fun getNotice(@FieldMap param: Map<String, String>): Flowable<NoticeBean>

    //添加保修
    @POST("repair/create")
    @FormUrlEncoded
    fun addRepairs(@FieldMap param: Map<String, String>): Flowable<CodeBean>


}