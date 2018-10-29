package com.project.my.studystarteacher.newteacher.bean;

public class HomeShare {

    /**
     * link : https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx48693929516ef020&redirect_uri=http://47.105.74.69:8081/TheacherService/pay/activi&response_type=code&scope=snsapi_userinfo&state=7878cc01cc3371#wechat_redirect
     * pic : /pay/shared/thumb.jpg
     * smallPic : http://app.xuezhixing.net:8080/image/pay/shared/thumb.jpg
     * title : 研发专属分院一院亲子阅读活动开始啦！
     * content : 一班 王小林老师邀请您及宝宝一起参与！
     */

    private String link;
    private String pic;
    private String smallPic;
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

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
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
