package com.demo.cloud.hystrixservice.userservice.common;

import lombok.Getter;

public class  CommonResult<T>{
     public String message;
     public int code;
     public T t;
     public CommonResult(){

     }
     public CommonResult(T t){
          this.message = StatusCode.SUCCESSED.getMessage();
          this.code = StatusCode.SUCCESSED.getCode();
          this.t = t;
     }
     public CommonResult(String message, int code){
          this.message = message;
          this.code = this.code;
          this.t = null;
     }
}

@Getter
enum StatusCode{
     SUCCESSED("success", 200), FAILED("failed",400);
     String message;
     int code;
     StatusCode(String mes, int code){
          this.message = message;
          this.code = code;
     }
}