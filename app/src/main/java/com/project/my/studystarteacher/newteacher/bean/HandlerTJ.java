package com.project.my.studystarteacher.newteacher.bean;

import com.project.my.studystarteacher.newteacher.adapter.BooksAdapter;

import java.io.Serializable;
import java.util.List;

public class HandlerTJ implements Serializable {

    /**
     * schoolbagcategory : B
     * categoryletter : B
     * bags : [{"schoolbagbhao":"B5392497","schoolbagname":"B5392497","bookList":[{"bookcategory":"道德品格","bookname":"还好是这样","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484707808649_f1q.jpg"}]},{"schoolbagbhao":"B5392496","schoolbagname":"B5392496","bookList":[{"bookcategory":"奇思妙想","bookname":"你需要一顶帽子","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1501577275878_s1r.jpg"}]},{"schoolbagbhao":"B5392495","schoolbagname":"B5392495","bookList":[{"bookcategory":"社交情感","bookname":"好梦！困困熊","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1501117538088_l2v.jpg"}]},{"schoolbagbhao":"B5392493","schoolbagname":"B5392493","bookList":[{"bookcategory":"奇思妙想","bookname":"大嘴鸟大嘴","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484705326570_z1y.jpg"}]},{"schoolbagbhao":"B5392492","schoolbagname":"B5392492","bookList":[{"bookcategory":"社交情感","bookname":"不，不行!","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484529985492_g6x.jpg"}]},{"schoolbagbhao":"B5392491","schoolbagname":"B5392491","bookList":[{"bookcategory":"社交情感","bookname":"我讨厌泰迪熊","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1502519631733_i8t.jpg"}]},{"schoolbagbhao":"B5392490","schoolbagname":"B5392490","bookList":[{"bookcategory":"奇思妙想","bookname":"两个怪物","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484384911445_a1l.jpg"}]},{"schoolbagbhao":"B5392489","schoolbagname":"B5392489","bookList":[{"bookcategory":"社交情感","bookname":"世界上最糟糕的爸爸","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1500704450141_k7g.jpg"}]},{"schoolbagbhao":"B5392487","schoolbagname":"B5392487","bookList":[{"bookcategory":"了解生命","bookname":"爷爷的天使","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484556873164_m6n.jpg"}]},{"schoolbagbhao":"B5392486","schoolbagname":"B5392486","bookList":[{"bookcategory":"家长形象","bookname":"爸爸和我","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1481269677640_c5g.jpg"}]},{"schoolbagbhao":"B5392484","schoolbagname":"B5392484","bookList":[{"bookcategory":"奇思妙想","bookname":"点点点","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1495613909947_e0s.jpg"},{"bookcategory":"奇思妙想","bookname":"小种子","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1495678750822_v2n.jpg"},{"bookcategory":"社交情感","bookname":"大大行，我也行","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1502957845197_r3i.jpg"}]},{"schoolbagbhao":"B5392482","schoolbagname":"B5392482","bookList":[{"bookcategory":"社交情感","bookname":"大卫惹麻烦","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1481273698703_p5j.jpg"},{"bookcategory":"社交情感","bookname":"爱是一捧浓浓的蜂蜜","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492489661601_c6z.jpg"},{"bookcategory":"科普认知","bookname":"拼拼凑凑的变色龙","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1499385694058_f0d.jpg"}]},{"schoolbagbhao":"B5392481","schoolbagname":"B5392481","bookList":[{"bookcategory":"奇思妙想","bookname":"吃不到的晚餐","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484703947164_p5x.jpg"},{"bookcategory":"社交情感","bookname":"猜猜我有多爱你","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492845378814_p7n.jpg"},{"bookcategory":"道德品格","bookname":"阿利的红斗篷","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1494663352696_u3s.jpg"}]},{"schoolbagbhao":"B5392480","schoolbagname":"B5392480","bookList":[{"bookcategory":"快乐成长","bookname":"乖乖小顽皮","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484722839461_y3y.jpg"},{"bookcategory":"社交情感","bookname":"爱是一捧浓浓的蜂蜜","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492489661601_c6z.jpg"},{"bookcategory":"社交情感","bookname":"猜猜我有多爱你","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492845378814_p7n.jpg"},{"bookcategory":"奇思妙想","bookname":"好饿的毛毛虫","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1498640990773_x4h.jpg"},{"bookcategory":"奇思妙想","bookname":"一半奖赏","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1515467273036_s5p.jpg"}]},{"schoolbagbhao":"B5392478","schoolbagname":"B5392478","bookList":[{"bookcategory":"奇思妙想","bookname":"大嘴鸟大嘴","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484705326570_z1y.jpg"},{"bookcategory":"科普认知","bookname":"小蓝和小黄","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492831142535_l5v.jpg"},{"bookcategory":"社交情感","bookname":"大卫，圣诞节到啦！","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1495607549557_n2m.jpg"},{"bookcategory":"生活常识","bookname":"魔法雪橇","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1505545105998_k9b.jpg"},{"bookcategory":"奇思妙想","bookname":"大海里的小提琴","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1515466931074_k1j.jpg"}]},{"schoolbagbhao":"B5391560","schoolbagname":"B5391560","bookList":[{"bookcategory":"社交情感","bookname":"妈妈在哪儿？","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1497857212512_u0t.jpg"},{"bookcategory":"科普认知","bookname":"1,2,3到动物园 ","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1499386559721_n9a.jpg"},{"bookcategory":"生活常识","bookname":"鲨鱼斗火车","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1500707651013_k4q.jpg"}]},{"schoolbagbhao":"B5391559","schoolbagname":"B5391559","bookList":[{"bookcategory":"奇思妙想","bookname":"弗洛拉的小毯子","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1489197502103_o2h.jpg"},{"bookcategory":"社交情感","bookname":"逃家小兔","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492831277981_s0b.jpg"},{"bookcategory":"奇思妙想","bookname":"小幽灵吓坏了","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1501748064766_a9y.jpg"}]},{"schoolbagbhao":"B5391558","schoolbagname":"B5391558","bookList":[{"bookcategory":"了解生命","bookname":"奶奶的护身符","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484727846867_k7x.jpg"},{"bookcategory":"生活常识","bookname":"小步走路","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1489210984982_d2c.jpg"},{"bookcategory":"社交情感","bookname":"毛头小鹰","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492763293718_l4b.jpg"}]},{"schoolbagbhao":"B5391557","schoolbagname":"B5391557","bookList":[{"bookcategory":"社交情感","bookname":"艾美丽和小鬼怪","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484621233195_w1n.jpg"},{"bookcategory":"道德品格","bookname":"阿立会穿裤子了","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492745579462_s7c.jpg"},{"bookcategory":"生活常识","bookname":"一根香蕉掉下来","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1500709510897_x6p.jpg"}]},{"schoolbagbhao":"B5391556","schoolbagname":"B5391556","bookList":[{"bookcategory":"奇思妙想","bookname":"绿绵羊在哪里","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484987731134_w2d.jpg"},{"bookcategory":"社交情感","bookname":"生气汤","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1494656625944_m7y.jpg"},{"bookcategory":"社交情感","bookname":"晚安，小鸡球球","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1497856813312_o1a.jpg"}]},{"schoolbagbhao":"B5391555","schoolbagname":"B5391555","bookList":[{"bookcategory":"奇思妙想","bookname":"小蜘蛛斯巴达克斯","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1485070168547_b2k.jpg"},{"bookcategory":"社交情感","bookname":"小鸡球球，,藏猫猫！","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1497857355392_x8o.jpg"}]},{"schoolbagbhao":"B5391554","schoolbagname":"B5391554","bookList":[{"bookcategory":"调节情绪","bookname":"罗伯生气了","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484530121555_m1l.jpg"},{"bookcategory":"奇思妙想","bookname":"乘着火车去旅行","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484986555769_h0q.jpg"},{"bookcategory":"社交情感","bookname":"猜猜我有多爱你","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1494486839244_s7w.jpg"}]},{"schoolbagbhao":"B5391553","schoolbagname":"B5391553","bookList":[{"bookcategory":"社交情感","bookname":"母鸡萝丝去散步","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1482380444394_m2l.jpg"},{"bookcategory":"奇思妙想","bookname":"狐狸先生的宴会","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484625215195_k1i.jpg"},{"bookcategory":"社交情感","bookname":"小鸡球球帮妈妈做事","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1497856362200_v9m.jpg"}]},{"schoolbagbhao":"B5391552","schoolbagname":"B5391552","bookList":[{"bookcategory":"心理健康","bookname":"我不想生气","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1481339584640_r6y.jpg"},{"bookcategory":null,"bookname":"波比是个万人迷","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484707572445_g5h.jpg"},{"bookcategory":"社交情感","bookname":"好朋友","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1497855860038_z2z.jpg"}]},{"schoolbagbhao":"B5391551","schoolbagname":"B5391551","bookList":[{"bookcategory":"科普认知","bookname":"燕子和春天","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484548176914_r7e.jpg"},{"bookcategory":"生活常识","bookname":"一园青菜成了精","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1492833197714_l3a.jpg"},{"bookcategory":"社交情感","bookname":"谢谢啦！","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1497856894040_i0w.jpg"}]},{"schoolbagbhao":"B5391550","schoolbagname":"B5391550","bookList":[{"bookcategory":"社交情感","bookname":"小熊的安眠曲","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484702786086_y1v.jpg"},{"bookcategory":"生活常识","bookname":"根本就不脏嘛","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484633385399_p1n.jpg"},{"bookcategory":"社交情感","bookname":"艾薇的礼物","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484699083805_t8z.jpg"}]},{"schoolbagbhao":"B5391548","schoolbagname":"B5391548","bookList":[{"bookcategory":"心理健康","bookname":"鳄鱼怕怕 牙医怕怕","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1482024483297_m7f.jpg"},{"bookcategory":"家长形象","bookname":"皮皮猪和爸爸","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484382213305_z7t.jpg"},{"bookcategory":"社交情感","bookname":"妈妈和我去探险","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484640727055_v7n.jpg"}]},{"schoolbagbhao":"B5391547","schoolbagname":"B5391547","bookList":[{"bookcategory":"社交情感","bookname":"找到你啦！","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1481335271484_o7n.jpg"},{"bookcategory":"心理健康","bookname":"我想要爱","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1483754816267_w5q.jpg"},{"bookcategory":"奇思妙想","bookname":"乘着校车去旅行","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484986771975_k3i.jpg"}]},{"schoolbagbhao":"B5391546","schoolbagname":"B5391546","bookList":[{"bookcategory":"心理健康","bookname":"我不怕孤独","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1481349655109_j7a.jpg"},{"bookcategory":"生活常识","bookname":"小兔快跑","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484706236086_r2h.jpg"},{"bookcategory":"科普认知","bookname":"从1\u201c鼠\u201d到10","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/1500710275159_e4n.jpg"}]}]
     * count : 29
     */

    private String schoolbagcategory;
    private String categoryletter;
    private int count;
    private List<BagsBean> bags;

    public String getSchoolbagcategory() {
        return schoolbagcategory;
    }

    public void setSchoolbagcategory(String schoolbagcategory) {
        this.schoolbagcategory = schoolbagcategory;
    }

    public String getCategoryletter() {
        return categoryletter;
    }

    public void setCategoryletter(String categoryletter) {
        this.categoryletter = categoryletter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BagsBean> getBags() {
        return bags;
    }

    public void setBags(List<BagsBean> bags) {
        this.bags = bags;
    }

    public static class BagsBean implements Serializable {
        /**
         * schoolbagbhao : B5392497
         * schoolbagname : B5392497
         * bookList : [{"bookcategory":"道德品格","bookname":"还好是这样","booklogourl":"http://app.xuezhixing.net:8080/image/sbook/a1484707808649_f1q.jpg"}]
         */

        private String schoolbagbhao;
        private String schoolbagname;
        private List<BookListBean> bookList;
        private BooksAdapter adapter;

        public BooksAdapter getAdapter() {
            return adapter;
        }

        public void setAdapter(BooksAdapter adapter) {
            this.adapter = adapter;
        }

        public String getSchoolbagbhao() {
            return schoolbagbhao;
        }

        public void setSchoolbagbhao(String schoolbagbhao) {
            this.schoolbagbhao = schoolbagbhao;
        }

        public String getSchoolbagname() {
            return schoolbagname;
        }

        public void setSchoolbagname(String schoolbagname) {
            this.schoolbagname = schoolbagname;
        }

        public List<BookListBean> getBookList() {
            return bookList;
        }

        public void setBookList(List<BookListBean> bookList) {
            this.bookList = bookList;
        }

        public static class BookListBean implements Serializable {
            /**
             * bookcategory : 道德品格
             * bookname : 还好是这样
             * booklogourl : http://app.xuezhixing.net:8080/image/sbook/a1484707808649_f1q.jpg
             */

            private String bookcategory;
            private String bookname;
            private String booklogourl;

            public String getBookcategory() {
                return bookcategory;
            }

            public void setBookcategory(String bookcategory) {
                this.bookcategory = bookcategory;
            }

            public String getBookname() {
                return bookname;
            }

            public void setBookname(String bookname) {
                this.bookname = bookname;
            }

            public String getBooklogourl() {
                return booklogourl;
            }

            public void setBooklogourl(String booklogourl) {
                this.booklogourl = booklogourl;
            }
        }
    }
}
