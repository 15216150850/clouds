package com.bp.test.service;

import com.bp.common.base.BaseDao;
import com.bp.common.base.BaseService;
import com.bp.test.dao.TestDao;
import com.bp.test.entity.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/14 15:09
 */
@Service
public class TestService  extends BaseService<TestUser> {

    @Autowired
    private TestDao testDao;

    public List<Map> testUsers(){

        List<TestUser> list = new ArrayList<>();
             testDao.deleteInBatch(list);
       ExampleMatcher matcher = ExampleMatcher.matching()
               //开头
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())
               //全部模糊查询，即%{address}%
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.endsWith())

               //忽略字段，即不管password是什么值都不加入查询条件
                .withIgnorePaths("password");
        Example<TestUser> example = Example.of(new TestUser() ,matcher);




        return testDao.selectList();
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }



    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Example<TestUser> getExample(TestUser testUser) {

        return null;
    }
}
