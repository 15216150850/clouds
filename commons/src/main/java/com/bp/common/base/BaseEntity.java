package com.bp.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @Description: 实体基类
 * @date 2019年3月15日 09:53:06
 */

public class BaseEntity implements Serializable {

    /**
     * 使用IdWorker 生成ID
     */
    private Long id;
    /**
     * 录入人
     */
    private Long inserter;
    /**
     * 录入时间
     */

    private Date insertTime;
    /**
     * 更新人
     */
    private Long updater;
    /**
     * 更新时间
     */

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Long getInserter() {
        return inserter;
    }

    public void setInserter(Long inserter) {
        this.inserter = inserter;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
