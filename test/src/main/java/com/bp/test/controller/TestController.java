package com.bp.test.controller;


import com.bp.common.bean.ReturnBean;
import com.bp.common.utils.IdWorker;
import com.bp.test.client.TestClient;
import com.bp.common.entity.LoginUser;
import com.bp.common.utils.UserUtils;
import com.bp.test.entity.TestEntity;
import com.bp.test.service.TestService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @auther: 钟欣凯
 * @date: 2019/3/11 17:07
 */
@RestController
@Api(value = "测试接口API")
@JsonIgnoreProperties(ignoreUnknown = true)//忽略未知属性
public class TestController {

    @Autowired
    private TestClient testClient;

    @Autowired
    private IdWorker idWorker;


    @Autowired
    private TestService testService;
    @GetMapping("test")
    @PreAuthorize("hasAuthority('aaa')")
    public LoginUser test(){
        return UserUtils.getCurrentUser();

    }

    @GetMapping("feign/test")
    @ResponseBody
    @ApiOperation(value = "测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "Authorization", required = true)

    })
    public ReturnBean<List<TestEntity>> feign(@RequestBody TestEntity testEntity){
        TestEntity testEntity1 = new TestEntity();
        testEntity1.setId(1);
        testEntity1.setPassword("123456");
        List<TestEntity> testEntities = new ArrayList<>();
        testEntities.add(testEntity1);
        return ReturnBean.ok(testEntities);
    }

    @GetMapping("selectList")
    @ResponseBody
    public ReturnBean<List<Map>> selectListAll(){
        long l = idWorker.nextId();
        List<Map> testUsers = testService.testUsers();
        return ReturnBean.ok(testUsers);
    }
}
