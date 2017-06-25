package com.github.stone.leaf.api;

import com.github.stone.leaf.protocol.LeafSegment;

/**
 * leaf current value
 *
 * @author stone
 */
public interface LeafCurrentFacade {

    /**
     * next leaf segment value
     *
     * @param leafName leaf name
     * @return segment value
     */
    LeafSegment nextSegment(String leafName);

}
