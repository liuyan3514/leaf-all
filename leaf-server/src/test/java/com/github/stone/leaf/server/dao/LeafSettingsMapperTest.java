package com.github.stone.leaf.server.dao;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.stone.leaf.server.entity.LeafSettings;

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
