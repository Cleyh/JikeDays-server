package com.jidays.jidaysserver.midService;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Component
public class JavaScriptExecutor {
    private final ScriptEngine engine;

    public JavaScriptExecutor() {
        ScriptEngineManager manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("nashorn");
    }

    public Object executeScript(String script, String data) throws ScriptException {
        engine.put("jsonString", data);
        return engine.eval(script);
    }
}
