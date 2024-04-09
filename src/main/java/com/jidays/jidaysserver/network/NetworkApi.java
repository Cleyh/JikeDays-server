package com.jidays.jidaysserver.network;

import com.jidays.jidaysserver.entity.Subsource;
import com.jidays.jidaysserver.service.JiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NetworkApi {
    @Autowired
    private JiService jiService;
    @RequestMapping(path = "/helloworld", method = RequestMethod.GET)
    public List<String> get1() {
        return jiService.helloWorld();
    }

    @GetMapping("/getSubsource")
    public List<Subsource> getSubsource(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return jiService.getSubsource(page, size);
    }

    @PostMapping("/addSubsource")
    public boolean addSubsource(@RequestParam(name = "tittle",defaultValue = "无标题的订阅源") String tittle,
                                @RequestParam(name = "content",defaultValue = "空的简介") String content)
    {
        return jiService.addSubsource(tittle,content);
    }
}
