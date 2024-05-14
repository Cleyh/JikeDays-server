package com.jidays.jidaysserver.entity;

import java.io.File;

/**
 * 订阅源实体化的数据结构
 */

public class Subsource {
    int sid;
    String tittle;
    String content;

    String script;

    String type;

    String url;

    String updateInerval;

    public Subsource(int sid, String tittle, String content) {
        this.sid = sid;
        this.tittle = tittle;
        this.content = content;
    }

    public Subsource() {
    }

    public Subsource(int sid, String tittle, String content, String script, String type, String url, String updateInerval) {
        this.sid = sid;
        this.tittle = tittle;
        this.content = content;
        this.script = script;
        this.type = type;
        this.url = url;
        this.updateInerval = updateInerval;
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

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpdateInerval() {
        return updateInerval;
    }

    public void setUpdateInerval(String updateInerval) {
        this.updateInerval = updateInerval;
    }
}
