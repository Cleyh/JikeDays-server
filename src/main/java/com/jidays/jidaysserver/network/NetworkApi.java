package com.jidays.jidaysserver.network;

import com.jidays.jidaysserver.service.JiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetworkApi {
    @Autowired
    private JiService jiService;
    @RequestMapping(path = "/helloworld", method = RequestMethod.GET)
    public String get1() {
        return jiService.helloWorld();
    }
}
