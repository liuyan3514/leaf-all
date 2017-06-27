package com.github.stone.leaf.server.service.impl;

import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.github.stone.leaf.protocol.LeafSegment;
import com.github.stone.leaf.server.dao.LeafCurrentMapper;
import com.github.stone.leaf.server.entity.LeafCurrent;
import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.enums.LeafGenType;
import com.github.stone.leaf.server.service.LeafCurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * sql leaf current value service
 *
 * @author stone
 */
@Service
public class SqlLeafCurrentServiceImpl implements LeafCurrentService {

    @Autowired
    LeafCurrentMapper currentMapper;

    public boolean support(LeafSettings settings) {
        return LeafGenType.MYSQL.name().equals(settings.getType());
    }

    public LeafSegment nextSegment(LeafSettings settings) {
        for (int i = 0; i < 10; i++) {
            LeafCurrent current = currentMapper.getCurrent(settings.getName());
            Long newValue = current.getValue() + settings.getDelta();
            int updates = currentMapper.increment(settings.getName(), newValue, current.getValue());
            if (updates == 1) {
                return new LeafSegment(settings.getDelta(), current.getValue());
            }
        }
        throw new IllegalStateException("retry 10 times failure");
    }

    public void resetLeafCurrent(LeafSettings settings) {
        Date gmtModify = new Date();
        LeafCurrent current = new LeafCurrent();
        current.setName(settings.getName());
        current.setValue(settings.getMinimum());
        current.setGmtCreate(gmtModify);
        current.setGmtModify(gmtModify);
        currentMapper.insert(current);
    }

    public Long getLeafCurrent(LeafSettings settings) {
        LeafCurrent current = currentMapper.getCurrent(settings.getName());
        if (current != null) {
            return current.getValue();
        }
        return 0L;
    }
}
