package com.imdb.imdb.exception;

import java.io.Serializable;

public class RestErrorMessage implements Serializable {

    public RestErrorMessage(String message, Integer code){
        this.setMessage(message);
        this.setCode(code);
    }

    private String message;
    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
