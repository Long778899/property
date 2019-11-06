package com.goketech.smartcommunity.model.bean

class NoticeBean {

    /**
     * data : [{"content":"111","create_at":"2019-09-02 00:00:00","id":4,"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909021624583338.jpg","scope":3,"title":"app新版上线","type":1},{"content":"123 sd","id":1,"image":"http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909021545324020.jpg","scope":1,"title":"星期五停水通知","type":1}]
     * msg : success
     * status : 0
     */

    var msg: String? = null
    var status: Int = 0
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * content : 111
         * create_at : 2019-09-02 00:00:00
         * id : 4
         * image : http://smart-community-goke.oss-cn-beijing.aliyuncs.com/201909021624583338.jpg
         * scope : 3
         * title : app新版上线
         * type : 1
         */

        var content: String? = null
        var create_at: String? = null
        var id: Int = 0
        var image: String? = null
        var scope: Int = 0
        var title: String? = null
        var type: Int = 0
    }
}
