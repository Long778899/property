package com.goketech.smartcommunity.utils

import android.text.TextUtils
import java.net.URLEncoder
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MyUtils {

    companion object{
        val secret_key = "m\$c2Bk3kb^J$7MDE"

        var regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$"

        fun getSign(map:Map<String,String>):String?{
            var result = ""
            try {
                val infoIds = ArrayList<Map.Entry<String, String>>(map.entries)
                // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
                Collections.sort<Map.Entry<String, String>>(infoIds) { o1, o2 -> o1.key.toString().compareTo(o2.key) }

                // 构造签名键值对的格式
                var sb = StringBuilder()
                for (item in infoIds) {
                    if (item.key != null || item.key !== "") {
                        val key = item.key
                        val `val` = URLEncoder.encode(item.value)
                        if (!(`val` === "" || `val` == null)) {
                            sb.append(key + "=" + `val` + "&")
                        }
                    }
                }
                sb = sb.deleteCharAt(sb.length - 1)
                //			sb.append(PropertyManager.getProperty("SIGNKEY"));
                result = sb.toString()

                //进行MD5加密
                result = MD5Utils.MD5Encode(result + secret_key, "utf8")!!.toUpperCase()
            } catch (e: Exception) {
                return null
            }

            return result
        }

        /**
         * 检查手机号
         */
        fun checkMoblie(phone:String):Boolean{
            var bool = false;
            if(!TextUtils.isEmpty(phone) && phone.length == 11){
                var p = Pattern.compile(regex)
                var m = p.matcher(phone)
                bool = m.matches()
            }
            return bool;
        }
    }

}