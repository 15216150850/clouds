package com.bp.test.service;

        import com.bp.common.base.BaseDao;
        import com.bp.common.base.BaseService;
        import com.bp.test.dao.SysUserDao;
        import com.bp.test.entity.SysUser;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Example;
        import org.springframework.data.domain.ExampleMatcher;
        import org.springframework.data.domain.Sort;
        import org.springframework.stereotype.Service;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/19 15:23
 */
@Service
public class SysUserService extends BaseService<SysUser> {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public BaseDao getBaseDao() {
        return sysUserDao;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Example<SysUser> getExample(SysUser sysUser) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains());

        System.out.println("aaaa");
        return Example.of(sysUser,matcher);

    }
}
