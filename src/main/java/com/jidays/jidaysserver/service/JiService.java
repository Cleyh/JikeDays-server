package com.jidays.jidaysserver.service;

import com.jidays.jidaysserver.dbprocess.JiDBMapper;
import com.jidays.jidaysserver.entity.Subsource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JiService {
    @Autowired
    private JiDBMapper jiDBMapper;

    public List<String> helloWorld(){
        return jiDBMapper.world();
    }

    public List<Subsource> getSubsource(int page, int size) {
        // 计算分页的offset
        int offset = page * size;
        return jiDBMapper.getSubsource(offset, size);
    }

    public boolean addSubsource(String tittle, String content) {
        jiDBMapper.addSubsource(tittle,content);
        return true;
    }
}
