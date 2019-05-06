package com.bp.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/12 16:14
 */
@FeignClient(name = "test")
public interface TestClient {

    @GetMapping(value = "feign/test")
    @ResponseBody
    public String  feign();

}
