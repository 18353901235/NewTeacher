package com.project.my.studystarteacher.newteacher.bean;

public class BookType {

    /**
     * dictionaryname : 诗歌
     * dictionaryvalue : 4
     */

    private String dictionaryname;
    private int dictionaryvalue;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getDictionaryname() {
        return dictionaryname;
    }

    public void setDictionaryname(String dictionaryname) {
        this.dictionaryname = dictionaryname;
    }

    public int getDictionaryvalue() {
        return dictionaryvalue;
    }

    public void setDictionaryvalue(int dictionaryvalue) {
        this.dictionaryvalue = dictionaryvalue;
    }
}
