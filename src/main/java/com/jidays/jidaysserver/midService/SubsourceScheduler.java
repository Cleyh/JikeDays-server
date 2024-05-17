package com.jidays.jidaysserver.midService;

import com.jidays.jidaysserver.entity.Subsource;
import com.jidays.jidaysserver.service.SubsourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.script.ScriptException;
import java.util.List;

@Component
public class SubsourceScheduler {
    @Autowired
    private SubsourceService subsourceService;

    private final JavaScriptExecutor jsExecutor = new JavaScriptExecutor();
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 60 * 60 * 24 * 1000) // 每10秒执行一次
    public void fetchAndProcessApiData() {
        // 从数据库获取API信息和JavaScript代码
        List<Subsource> subsourceList = subsourceService.getAllSubsource();

        for (Subsource subsource : subsourceList) {
            //todo 获取url，url使用方法，formatrule

            //todo 从url获取数据

            //todo 处理数据
        }

//        try {
//            String processedData = (String) jsExecutor.executeScript(jsCode, responseData);
//            // 处理和保存数据
//            System.out.println("Processed Data: " + processedData);
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }
    }
}
