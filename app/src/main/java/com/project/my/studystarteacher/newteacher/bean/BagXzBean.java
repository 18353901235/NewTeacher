package com.project.my.studystarteacher.newteacher.bean;

import java.util.List;

public class BagXzBean {

    /**
     * bookList : [{"bookname":"环游世界做苹果派"},{"bookname":"一只好狼的故事"},{"bookname":"漏"}]
     * box_status : 2
     * card_no : 3330260653
     * decimal_no : 1
     * inner_box_no : 1
     * openCaseOrder : 8A0101119B
     * outer_box_no : 1
     * schoolbagbhao : C5393318
     * serial_no : 1
     * updatetime : 2018-10-26 08:53:30
     */

    private int box_status;
    private String card_no;
    private int decimal_no;
    private int inner_box_no;
    private String openCaseOrder;
    private int outer_box_no;
    private String schoolbagbhao;
    private int serial_no;
    private String updatetime;
    private List<BookListBean> bookList;

    public int getBox_status() {
        return box_status;
    }

    public void setBox_status(int box_status) {
        this.box_status = box_status;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public int getDecimal_no() {
        return decimal_no;
    }

    public void setDecimal_no(int decimal_no) {
        this.decimal_no = decimal_no;
    }

    public int getInner_box_no() {
        return inner_box_no;
    }

    public void setInner_box_no(int inner_box_no) {
        this.inner_box_no = inner_box_no;
    }

    public String getOpenCaseOrder() {
        return openCaseOrder;
    }

    public void setOpenCaseOrder(String openCaseOrder) {
        this.openCaseOrder = openCaseOrder;
    }

    public int getOuter_box_no() {
        return outer_box_no;
    }

    public void setOuter_box_no(int outer_box_no) {
        this.outer_box_no = outer_box_no;
    }

    public String getSchoolbagbhao() {
        return schoolbagbhao;
    }

    public void setSchoolbagbhao(String schoolbagbhao) {
        this.schoolbagbhao = schoolbagbhao;
    }

    public int getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(int serial_no) {
        this.serial_no = serial_no;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public List<BookListBean> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookListBean> bookList) {
        this.bookList = bookList;
    }

    public static class BookListBean {
        /**
         * bookname : 环游世界做苹果派
         */

        private String bookname;

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }
    }
}
