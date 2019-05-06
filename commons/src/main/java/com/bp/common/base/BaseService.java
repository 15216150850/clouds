package com.bp.common.base;

import com.bp.common.bean.ReturnBean;
import com.bp.common.utils.IdWorker;
import com.bp.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.*;

/**
 * 业务层基类
 *
 * @author: 钟欣凯
 * @date: 2019/3/15 09:53
 */
public abstract class BaseService<T extends BaseEntity> {

    @Autowired
    private IdWorker idWorker;


    public abstract BaseDao getBaseDao();

    /**
     * 添加
     *
     * @param entity 业务对象
     * @return 添加条数
     */
    public ReturnBean  insert(T entity) {
        entity.setId(idWorker.nextId());
        entity.setInserter(Objects.requireNonNull(UserUtils.getCurrentUser()).getId());
        entity.setInsertTime(new Date());
        return ReturnBean.ok(getBaseDao().save(entity));
    }


    public  void  update(T entity) {

        entity.setUpdater(Objects.requireNonNull(UserUtils.getCurrentUser()).getId());
        entity.setUpdateTime(new Date());
    }

    /**
     * 删除
     *
     * @param id 业务ID
     * @return 删除条数
     */
    public  void delete(Long id){
        getBaseDao().deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param  page  页
     * @param limit 页大小
     * @param t 查询条件
     * @return 查询结果
     */
    public ReturnBean findList(int page, int limit, T t){
        Example<T> example = getExample(t);
        Page dataPage = null;
        if (example == null){
            BaseDao baseDao = getBaseDao();
            PageRequest pageRequest = getpageRequest(page, limit);
            dataPage =   getBaseDao().findAll(pageRequest);

        } else {

            dataPage  = getBaseDao().findAll(example,getpageRequest(page,limit));
        }

        Long totalPages = dataPage.getTotalElements();
        return ReturnBean.list(dataPage.getContent(),totalPages);
    }
     public PageRequest getpageRequest(int page, int limit){

        if (getSort() == null){
            return PageRequest.of(page, limit);

        }
           return PageRequest.of(page, limit,getSort());
    }



    /**
     *   获取排序条件
     * @return 排序条件
     */
    public abstract Sort getSort();
    /**
     *  获取动态参数
     * @return 动态参数
     */
    public abstract Example<T> getExample(T t);


    public T findById(Long id){

        Optional optional = getBaseDao().findById(id);
        Object o = optional.get();
        return (T) o;
    }
}
