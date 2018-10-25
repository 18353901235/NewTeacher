package com.project.my.studystarteacher.newteacher.bean;

import java.util.List;

public class BorBookRecodeBean {

    /**
     * schoolbagbhao : C5393317
     * borrowsuretime : 2018-10-15
     * borrowuser : 575633
     * studentname : 刘可可
     * nianjiname : 初一
     * classname : 一班
     * bookList : [{"bookname":"金盘子"},{"bookname":"小鱼散步"},{"bookname":"小老鼠的漫长一夜"}]
     */

    private String schoolbagbhao;
    private String borrowsuretime;
    private int borrowuser;
    private String studentname;
    private String nianjiname;
    private String classname;
    private List<BookListBean> bookList;

    public String getSchoolbagbhao() {
        return schoolbagbhao;
    }

    public void setSchoolbagbhao(String schoolbagbhao) {
        this.schoolbagbhao = schoolbagbhao;
    }

    public String getBorrowsuretime() {
        return borrowsuretime;
    }

    public void setBorrowsuretime(String borrowsuretime) {
        this.borrowsuretime = borrowsuretime;
    }

    public int getBorrowuser() {
        return borrowuser;
    }

    public void setBorrowuser(int borrowuser) {
        this.borrowuser = borrowuser;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getNianjiname() {
        return nianjiname;
    }

    public void setNianjiname(String nianjiname) {
        this.nianjiname = nianjiname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<BookListBean> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookListBean> bookList) {
        this.bookList = bookList;
    }

    public static class BookListBean {
        /**
         * bookname : 金盘子
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
