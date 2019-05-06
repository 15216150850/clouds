package com.bp.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/13 09:45
 */
@ApiModel(value = "测试类")

@Data
public class TestEntity implements Serializable {

   @ApiModelProperty(value = "测试类ID")
    private int id;
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "性别")
    private Boolean sex;



}
