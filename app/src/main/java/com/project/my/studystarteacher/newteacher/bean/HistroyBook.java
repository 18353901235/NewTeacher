package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;
import java.util.List;

public class HistroyBook {

    /**
     * schoolbagbhao : B5392493
     * schoolbagname : B5392493
     * borrowstatus : 1
     * borrowtime : 1540906401000
     * books : [{"bookcategory":"奇思妙想","bookname":"大嘴鸟大嘴"}]
     */

    private String schoolbagbhao;
    private String schoolbagname;
    private int borrowstatus;
    private long borrowtime;
    private List<BooksBean> books;

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

    public int getBorrowstatus() {
        return borrowstatus;
    }

    public void setBorrowstatus(int borrowstatus) {
        this.borrowstatus = borrowstatus;
    }

    public long getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(long borrowtime) {
        this.borrowtime = borrowtime;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean implements Serializable {
        /**
         * bookcategory : 奇思妙想
         * bookname : 大嘴鸟大嘴
         */

        private String bookcategory;
        private String bookname;

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
    }
}
