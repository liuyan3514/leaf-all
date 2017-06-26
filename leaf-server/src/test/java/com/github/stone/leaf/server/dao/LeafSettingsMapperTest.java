package com.github.stone.leaf.server.dao;

import java.util.List;

import com.github.stone.leaf.server.entity.LeafSettings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.*;

/**
 * leaf settings mapper test
 *
 * @author stone
 */
@Transactional
public class LeafSettingsMapperTest extends AbstractMapperTest {

    @Autowired
    LeafSettingsMapper leafSettingsMapper;

    @Test
    public void testInsert() {
        LeafSettings settings = createLeafSettings();
        assertEquals(1, leafSettingsMapper.insert(settings));
    }

    @Test
    public void testGetAll() {
        LeafSettings settings = createLeafSettings();
        assertEquals(1, leafSettingsMapper.insert(settings));

        List<LeafSettings> allSettings = leafSettingsMapper.getAll();
        assertNotNull(allSettings);
        assertFalse(allSettings.isEmpty());
    }

    @Test
    public void testGetSettings() {
        LeafSettings insertSettings = createLeafSettings();
        assertEquals(1, leafSettingsMapper.insert(insertSettings));

        LeafSettings querySettings = leafSettingsMapper.getSettings(leafName);
        assertNotNull(querySettings);
        assertEquals(insertSettings.getDelta(), querySettings.getDelta());
    }

}
