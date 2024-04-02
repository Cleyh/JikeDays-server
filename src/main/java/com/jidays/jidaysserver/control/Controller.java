package com.jidays.jidaysserver.control;

import com.jidays.jidaysserver.dbprocess.JiDBController;

public class Controller {
    public static String hello(){
        return JiDBController.hello();
    }
    public static String world(){
        return JiDBController.world();
    }
}
