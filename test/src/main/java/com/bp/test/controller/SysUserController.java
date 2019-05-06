package com.bp.test.controller;

import com.bp.common.base.BaseController;
import com.bp.common.bean.ReturnBean;
import com.bp.test.entity.SysUser;
import com.bp.test.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/19 15:01
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController extends BaseController {


    @Autowired
    private SysUserService sysUserService;

    @PreAuthorize("hasAuthority('sys:selectList')")
    @GetMapping
    public ReturnBean<SysUser> selectList(int page,int limit,SysUser sysUser){

        return sysUserService.findList(page, limit, sysUser);
    }


    @PutMapping
    @PreAuthorize("hasAuthority('sys:insert')")
    public ReturnBean insert(@RequestBody SysUser sysUser){
        sysUserService.insert(sysUser);
        return ReturnBean.ok();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:update')")
    public ReturnBean update(@RequestBody SysUser sysUser){

        sysUserService.update(sysUser);
        return ReturnBean.ok();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:delete')")
    public ReturnBean delete(@PathVariable("id") Long id){

        sysUserService.delete(id);
        return ReturnBean.ok();
    }
}
