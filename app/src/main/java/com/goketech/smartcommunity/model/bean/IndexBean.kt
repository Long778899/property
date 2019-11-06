package com.goketech.smartcommunity.model.bean

class IndexBean {

    /**
     * data : {"activity":[{"apply_time":"2019-09-13 00:00:00","begin_time":"2019-09-03 00:00:00","end_time":"2019-10-06 00:00:00","id":2,"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909031242397699.png","remark":"请大家踊跃参加，晒出您的拿手菜！","title":"享受新时代"},{"apply_time":"2019-09-12 00:00:00","begin_time":"2019-09-03 00:00:00","end_time":"2019-09-12 00:00:00","id":1,"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909031241364768.png","remark":"请大家踊跃参加，体验智能生活！","title":"智能家居体验馆一日游"}],"banner":[{"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909021114121007.png","type":"1","url":"http://www.baidu.com"},{"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909021114544608.gif","type":"1","url":"http://www.baidu.com"}],"notice_list":[{"content":"水电费111","id":2,"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201910231814484807.jpg","scope":"1","show_time":"2019-10-23 00:00:00","title":"星期五停水通知","type":"3"}],"property_phone":"18612964055","weather":"27.9"}
     * msg : success
     * status : 0
     */

    var data: DataBean? = null
    var msg: String? = null
    var status: Int = 0

    class DataBean {
        /**
         * activity : [{"apply_time":"2019-09-13 00:00:00","begin_time":"2019-09-03 00:00:00","end_time":"2019-10-06 00:00:00","id":2,"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909031242397699.png","remark":"请大家踊跃参加，晒出您的拿手菜！","title":"享受新时代"},{"apply_time":"2019-09-12 00:00:00","begin_time":"2019-09-03 00:00:00","end_time":"2019-09-12 00:00:00","id":1,"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909031241364768.png","remark":"请大家踊跃参加，体验智能生活！","title":"智能家居体验馆一日游"}]
         * banner : [{"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909021114121007.png","type":"1","url":"http://www.baidu.com"},{"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909021114544608.gif","type":"1","url":"http://www.baidu.com"}]
         * notice_list : [{"content":"水电费111","id":2,"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201910231814484807.jpg","scope":"1","show_time":"2019-10-23 00:00:00","title":"星期五停水通知","type":"3"}]
         * property_phone : 18612964055
         * weather : 27.9
         */

        var property_phone: String? = null
        var weather: String? = null
        var activity: List<ActivityBean>? = null
        var banner: List<BannerBean>? = null
        var notice_list: List<NoticeListBean>? = null

        class ActivityBean {
            /**
             * apply_time : 2019-09-13 00:00:00
             * begin_time : 2019-09-03 00:00:00
             * end_time : 2019-10-06 00:00:00
             * id : 2
             * image : http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909031242397699.png
             * remark : 请大家踊跃参加，晒出您的拿手菜！
             * title : 享受新时代
             */

            var apply_time: String? = null
            var begin_time: String? = null
            var end_time: String? = null
            var id: Int = 0
            var image: String? = null
            var remark: String? = null
            var title: String? = null
        }

        class BannerBean {
            /**
             * image : http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909021114121007.png
             * type : 1
             * url : http://www.baidu.com
             */

            var image: String? = null
            var type: String? = null
            var url: String? = null
        }

        class NoticeListBean {
            /**
             * content : 水电费111
             * id : 2
             * image : http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201910231814484807.jpg
             * scope : 1
             * show_time : 2019-10-23 00:00:00
             * title : 星期五停水通知
             * type : 3
             */

            var content: String? = null
            var id: Int = 0
            var image: String? = null
            var scope: String? = null
            var show_time: String? = null
            var title: String? = null
            var type: String? = null
        }
    }
}
