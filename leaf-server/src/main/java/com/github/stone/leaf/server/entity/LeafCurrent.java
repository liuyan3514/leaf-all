package com.github.stone.leaf.server.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * leaf current value
 *
 * @author stone
 */
public class LeafCurrent implements Serializable {

    private static final long serialVersionUID = 5893077536155803160L;

    /**
     * leaf name
     */
    private String name;

    /**
     * leaf value
     */
    private Long value;

    /**
     * create date
     */
    private Date gmtCreate;

    /**
     * modify date
     */
    private Date gmtModify;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

}
