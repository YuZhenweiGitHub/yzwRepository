package com.company.entity;

import java.io.Serializable;

/**
 * Created by YZW on 2017/10/13.
 */
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 3634412846969689145L;

    private int statusCode;
    private String message;
    private  T data;

    public ResponseEntity(){
    }

    public ResponseEntity(String message,int statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }

    public ResponseEntity(T data,String message,int statusCode){
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ResponseEntity<T> setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseEntity<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseEntity<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("ResponseEntity{")
                .append("statusCode=")
                .append(statusCode)
                .append(", message='")
                .append(message)
                .append('\'')
                .append(", data=")
                .append(data)
                .append('}');
        return sb.toString();
    }
}