package com.github.stone.leaf.protocol;

import java.io.Serializable;

/**
 * leaf value segment
 *
 * @author stone
 */
public class LeafSegment implements Serializable {

    private static final long serialVersionUID = 8185678090079120163L;

    /**
     * segment value size
     */
    private final int delta;

    /**
     * segment value begin
     */
    private final long begin;

    public LeafSegment(int delta, long begin) {
        this.delta = delta;
        this.begin = begin;
    }

    public int getDelta() {
        return delta;
    }

    public long getBegin() {
        return begin;
    }

}
