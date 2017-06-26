package com.github.stone.leaf.server.dao;

import com.github.stone.leaf.server.entity.LeafCurrent;
import org.apache.ibatis.annotations.Param;

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
     * @param newValue new leaf value
     * @param oldValue update when leaf value is oldValue
     * @return rows
     */
    int increment(@Param("name") String leafName,
                  @Param("newValue") Long newValue,
                  @Param("oldValue") Long oldValue);

}
