package com.github.stone.leaf.server.dao;

import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.stone.leaf.server.entity.LeafCurrent;
import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.enums.LeafGenType;

/**
 * base mapper test
 *
 * @author stone
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-common.xml",
                                   "classpath*:spring/spring-mybatis.xml",
                                   "classpath*:spring/spring-redis.xml"})
public abstract class AbstractMapperTest {

    Long leafValue = 10000L;

    String leafName = "order";

    LeafCurrent createLeafCurrent() {
        LeafCurrent current = new LeafCurrent();
        current.setName(leafName);
        current.setValue(leafValue);
        current.setGmtCreate(new Date());
        current.setGmtModify(new Date());
        return current;
    }

    LeafSettings createLeafSettings() {
        LeafSettings settings = new LeafSettings();
        settings.setName(leafName);
        settings.setType(LeafGenType.MYSQL.name());
        settings.setDelta(100);
        settings.setMinimum(100000L);
        settings.setGmtCreate(new Date());
        return settings;
    }
}
