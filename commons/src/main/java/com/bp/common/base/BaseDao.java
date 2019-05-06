package com.bp.common.base;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @auther: 钟欣凯
 * @date: 2019/3/15 14:46
 */
public interface BaseDao<T , ID> extends JpaRepository<T,ID> {


}
