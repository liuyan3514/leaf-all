package com.github.stone.leaf.server.service;

import com.github.stone.leaf.protocol.LeafSegment;
import com.github.stone.leaf.server.entity.LeafSettings;

/**
 * leaf current value service
 *
 * @author stone
 */
public interface LeafCurrentService {

    /**
     * is support settings
     *
     * @param settings
     * @return
     */
    boolean support(LeafSettings settings);

    /**
     * get next leaf segment
     *
     * @param settings leaf settings
     * @return begin and segment size
     */
    LeafSegment nextSegment(LeafSettings settings);

    /**
     * reset leaf current value
     *
     * @param settings leaf settings
     * @return
     */
    void resetLeafCurrent(LeafSettings settings);

    /**
     * get leaf current
     *
     * @param settings
     * @return
     */
    Long getLeafCurrent(LeafSettings settings);

}
