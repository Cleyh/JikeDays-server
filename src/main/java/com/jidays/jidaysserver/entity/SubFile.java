package com.jidays.jidaysserver.entity;

import org.springframework.web.multipart.MultipartFile;

public class SubFile {
    public class SubsourceRequest extends Subsource {
        MultipartFile scriptFile;

        public MultipartFile getScriptFile() {
            return scriptFile;
        }

        public void setScriptFile(MultipartFile scriptFile) {
            this.scriptFile = scriptFile;
        }
    }
}
