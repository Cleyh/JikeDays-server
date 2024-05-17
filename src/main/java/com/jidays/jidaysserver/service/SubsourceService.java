package com.jidays.jidaysserver.service;

import com.jidays.jidaysserver.dbprocess.JiDBMapper;
import com.jidays.jidaysserver.entity.Subsource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsourceService {
    @Autowired
    private JiDBMapper jiDBMapper;

    public List<Subsource> getAllSubsource()
    {
        return jiDBMapper.getAllSubsource();
    }

    public List<Subsource> getAllTypeSubsource(String type)
    {
        return jiDBMapper.getAllTypeSubsource(type);
    }

}
