package com.project.my.studystarteacher.newteacher.utils;

public class EventBusUtil {

    private int msgWhat;
    private Object msgStr;

    public EventBusUtil(int what) {
        this.msgWhat = what;
    }

    public EventBusUtil(int what, Object msg) {
        this.msgWhat = what;
        this.msgStr = msg;
    }

    public int getMsgWhat() {
        return this.msgWhat;
    }

    public Object getMsgStr() {
        return this.msgStr;
    }
}
