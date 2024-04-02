package com.jidays.jidaysserver.network;

import com.jidays.jidaysserver.service.JiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class NetworkApi {
    @RequestMapping(path = "/helloworld", method = RequestMethod.GET)
    public String get1() {
        return JiService.helloWorld();
    }
}
