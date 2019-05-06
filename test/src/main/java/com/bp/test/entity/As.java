package com.bp.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/13 13:58
 */
@ApiModel("as")
@Data
public class As {


    @ApiModelProperty("id")
    private int id;

    @ApiModelProperty("傻逼")
    private String kdpa;
}
