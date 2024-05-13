package com.jidays.jidaysserver.entity;

import java.io.File;

/**
 * 订阅源实体化的数据结构
 */

public class Subsource {
    int sid;
    String tittle;
    String content;

    public Subsource(int sid, String tittle, String content) {
        this.sid = sid;
        this.tittle = tittle;
        this.content = content;
    }

    public Subsource() {
    }

    public int getSid() {
        return sid;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}
