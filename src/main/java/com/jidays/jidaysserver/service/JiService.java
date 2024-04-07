package com.jidays.jidaysserver.service;

import com.jidays.jidaysserver.dbprocess.JiDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiService {
    @Autowired
    private JiDBMapper jiDBMapper;
    public String helloWorld(){

        return jiDBMapper.hello() + jiDBMapper.world();
    }
}
