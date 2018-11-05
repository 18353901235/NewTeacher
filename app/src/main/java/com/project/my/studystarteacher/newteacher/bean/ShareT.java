package com.project.my.studystarteacher.newteacher.bean;

public class ShareT {

    /**
     * uuid : 91c6a51e-b25e-4213-b40e-9789947c6ab5
     * error_code : 0
     * msg : success
     * timestamp : 20181101110430
     * data : {"link":"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx48693929516ef020&redirect_uri=http://app.xuezhixing.net/TearcherService/pay/activi&response_type=code&scope=snsapi_userinfo&state=7878cc01cc3371#wechat_redirect","pic":"/pay/shared/thumb.jpg","title":"研发专属分院一院亲子阅读活动开始啦！","content":"一班 侯小迪老师邀请您及宝宝一起参与！"}
     */

    private String uuid;
    private int error_code;
    private String msg;
    private String timestamp;
    private DataBean data;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * link : https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx48693929516ef020&redirect_uri=http://app.xuezhixing.net/TearcherService/pay/activi&response_type=code&scope=snsapi_userinfo&state=7878cc01cc3371#wechat_redirect
         * pic : /pay/shared/thumb.jpg
         * title : 研发专属分院一院亲子阅读活动开始啦！
         * content : 一班 侯小迪老师邀请您及宝宝一起参与！
         */

        private String link;
        private String pic;
        private String title;
        private String content;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
