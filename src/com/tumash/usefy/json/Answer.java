package com.tumash.usefy.json;

import org.codehaus.jackson.map.ser.AnyGetterWriter;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.BeanSerializer;
import org.codehaus.jackson.type.JavaType;

public class Answer {
    private boolean err = false;
    private String mess = null;

    public Answer() {
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
    public boolean isErr() {
        return err;

    }
    public void setErr(boolean err) {
        this.err = err;
    }
}
