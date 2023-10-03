package com.mystore.spring.boot.fakestore.exception;

import org.springframework.http.HttpStatus;

public class NoRecordFoundException extends Exception{

    public NoRecordFoundException(String msg){
        super(msg);
    }

}
