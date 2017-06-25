package com.github.stone.leaf.server.dao;

import com.github.stone.leaf.server.entity.LeafCurrent;

/**
 * leaf current mapper
 *
 * @author stone
 */
public interface LeafCurrentMapper {

    /**
     * insert leaf current
     *
     * @param leafCurrent
     * @return rows
     */
    int insert(LeafCurrent leafCurrent);

    /**
     * get leaf current value
     *
     * @param leafName leaf name
     * @return leaf current
     */
    LeafCurrent getCurrent(String leafName);

    /**
     * increment leaf current value
     *
     * @param leafName leaf name
     * @param delta    value delta
     * @param oldValue change when value is oldValue
     * @return rows
     */
    int increment(String leafName, Integer delta, Long oldValue);

}
