package com.bp.test.dao;

import com.bp.test.entity.TestRole;
import com.bp.test.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/14 15:28
 */
public interface TestRoleDao extends JpaRepository<TestRole,Integer> {

}
