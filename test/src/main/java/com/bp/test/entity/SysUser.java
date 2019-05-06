package com.bp.test.entity;

import com.bp.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/19 15:14
 */
@ApiModel(value = "系统用户")
@Entity
@Data
public class SysUser extends BaseEntity {

    @Id
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;


    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "用户角色(多角色)")
    private String roles;


    @ApiModelProperty(value = "账户是否可用")
    private Integer isEnable;


    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;
}
