package com.github.stone.leaf.server.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * leaf settings
 *
 * @author stone
 */
public class LeafSettings implements Serializable {

    private static final long serialVersionUID = 7274958312451956809L;

    /**
     * leaf name
     */
    private String name;

    /**
     * leaf type
     */
    private String type;

    /**
     * leaf minimum value
     */
    private Long minimum;

    /**
     * leaf segment size
     */
    private Integer delta;

    /**
     * create date
     */
    private Date gmtCreate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getMinimum() {
        return minimum;
    }

    public void setMinimum(Long minimum) { this.minimum = minimum; }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}
