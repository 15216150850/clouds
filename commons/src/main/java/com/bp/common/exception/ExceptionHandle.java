package com.bp.common.exception;

import com.bp.common.bean.ReturnBean;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/21 09:04
 */
@ControllerAdvice
public class ExceptionHandle {


     public ReturnBean bpExceptionHandle(BpException e){

           return null;
        }
}
