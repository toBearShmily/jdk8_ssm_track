package com.shmily.model.system;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * 公共属性
 * Created by wuxubiao on 2017/5/19.
 */
@MappedSuperclass
public class BaseEntity {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(unique = true)
    private Integer id;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public BaseEntity() {
        Date date = new Date();
        if(this.createTime == null)this.createTime = date;
            this.createTime=createTime;
        if(this.createTime == null)this.updateTime = date;
        this.createTime=createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
