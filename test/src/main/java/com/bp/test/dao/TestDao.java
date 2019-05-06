package com.bp.test.dao;

import com.bp.test.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Map;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/14 15:05
 */


public interface TestDao extends JpaRepository<TestUser,Long> {

    @Query(value = "select * from test_user",nativeQuery = true)
   List<Map>    selectList();




}
