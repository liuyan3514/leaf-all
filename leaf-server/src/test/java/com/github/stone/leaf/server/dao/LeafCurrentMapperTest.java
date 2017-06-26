package com.github.stone.leaf.server.dao;

import java.util.Date;

import com.github.stone.leaf.server.entity.LeafCurrent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;

/**
 * leaf current mapper test
 *
 * @author stone
 */
@Transactional
public class LeafCurrentMapperTest extends AbstractMapperTest {

    @Autowired
    LeafCurrentMapper leafCurrentMapper;

    @Test
    public void testInsert() {
        assertEquals(1, leafCurrentMapper.insert(createLeafCurrent()));
    }

    @Test
    public void testGetCurrent() {
        LeafCurrent insertCurrent = createLeafCurrent();
        assertEquals(1, leafCurrentMapper.insert(insertCurrent));

        LeafCurrent queryCurrent = leafCurrentMapper.getCurrent(leafName);
        assertNotNull(queryCurrent);
        assertEquals(insertCurrent.getValue(), queryCurrent.getValue());
    }

    @Test
    public void testIncrement() {
        int delta = 10;
        LeafCurrent current = createLeafCurrent();
        assertEquals(1, leafCurrentMapper.insert(current));

        assertEquals(1, leafCurrentMapper.increment(leafName, current.getValue() + delta, current.getValue()));
        LeafCurrent afterIncrement = leafCurrentMapper.getCurrent(leafName);
        assertEquals(current.getValue() + delta, afterIncrement.getValue().longValue());
    }

}
