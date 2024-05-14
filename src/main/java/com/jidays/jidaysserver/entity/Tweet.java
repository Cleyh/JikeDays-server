package com.jidays.jidaysserver.entity;

/**
 * 动态信息实体化的数据结构
 */
public class Tweet {
    int tid;
    int sid;
    String title;
    String content;
    String type;
    String timeSlotA;
    String timeSlotB;
    String timeSlotC;

    public Tweet(int tid, int sid, String title, String content, String type, String timeSlotA, String timeSlotB, String timeSlotC) {
        this.tid = tid;
        this.sid = sid;
        this.title = title;
        this.content = content;
        this.type = type;
        this.timeSlotA = timeSlotA;
        this.timeSlotB = timeSlotB;
        this.timeSlotC = timeSlotC;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeSlotA() {
        return timeSlotA;
    }

    public void setTimeSlotA(String timeSlotA) {
        this.timeSlotA = timeSlotA;
    }

    public String getTimeSlotB() {
        return timeSlotB;
    }

    public void setTimeSlotB(String timeSlotB) {
        this.timeSlotB = timeSlotB;
    }

    public String getTimeSlotC() {
        return timeSlotC;
    }

    public void setTimeSlotC(String timeSlotC) {
        this.timeSlotC = timeSlotC;
    }
}
