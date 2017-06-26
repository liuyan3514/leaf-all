package com.github.stone.leaf.server.service.impl;

import com.github.stone.leaf.protocol.LeafSegment;
import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.enums.LeafGenType;
import com.github.stone.leaf.server.service.LeafCurrentService;
import org.springframework.stereotype.Service;

/**
 * redis leaf current value service
 *
 * @author stone
 */
@Service
public class RedisLeafCurrentServiceImpl implements LeafCurrentService {

    public boolean support(LeafSettings settings) {
        return LeafGenType.REDIS.name().equals(settings.getType());
    }

    public LeafSegment nextSegment(LeafSettings settings) {
        return null;
    }

    public void resetLeafCurrent(LeafSettings settings) {

    }

    public Long getLeafCurrent(LeafSettings settings) {
        return null;
    }
}
