package com.example.restfulwebservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@NoArgsConstructor (default 생성자)
public class HelloWorldBean {
    private String msg;

//    (@Data) lombok 자동 생성 set, get, equals, toString, hashCode
//    public String getMsg() {
//        return this.msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    (@AllArgsConstructor) 자동 생성
//    public HelloWorldBean(String msg) {
//
//    }

}
