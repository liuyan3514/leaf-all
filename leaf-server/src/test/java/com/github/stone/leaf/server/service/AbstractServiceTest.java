package com.github.stone.leaf.server.service;

import java.util.Date;

import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.enums.LeafGenType;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * base service test
 *
 * @author stone
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-common.xml",
                                   "classpath*:spring/spring-mybatis.xml",
                                   "classpath*:spring/spring-redis.xml"})
public abstract class AbstractServiceTest {

    Long leafValue = 10000L;

    String leafName = "order";

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
