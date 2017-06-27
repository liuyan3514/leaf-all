package com.github.stone.leaf.server.service.impl;

import com.github.stone.leaf.protocol.LeafSegment;
import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.enums.LeafGenType;
import com.github.stone.leaf.server.enums.LeafRedisKey;
import com.github.stone.leaf.server.service.LeafCurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * redis leaf current value service
 *
 * @author stone
 */
@Service
public class RedisLeafCurrentServiceImpl implements LeafCurrentService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public boolean support(LeafSettings settings) {
        return LeafGenType.REDIS.name().equals(settings.getType());
    }

    public LeafSegment nextSegment(LeafSettings settings) {
        String rawKey = LeafRedisKey.LEAF_CURRENT.getKey(settings.getName());
        int delta = settings.getDelta();
        Long newValue = redisTemplate.opsForValue().increment(rawKey, settings.getDelta());
        return new LeafSegment(delta, newValue - delta);
    }

    public void resetLeafCurrent(LeafSettings settings) {
        String rawKey = LeafRedisKey.LEAF_CURRENT.getKey(settings.getName());
        redisTemplate.opsForValue().set(rawKey, String.valueOf(settings.getMinimum()));
    }

    public Long getLeafCurrent(LeafSettings settings) {
        String rawKey = LeafRedisKey.LEAF_CURRENT.getKey(settings.getName());
        String value = redisTemplate.opsForValue().get(rawKey);
        if (value != null) {
            return new Long(value);
        }
        return 0L;
    }
}
