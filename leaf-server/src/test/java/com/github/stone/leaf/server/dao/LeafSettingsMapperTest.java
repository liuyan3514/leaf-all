package com.github.stone.leaf.server.dao;

import java.util.List;

import com.github.stone.leaf.server.entity.LeafSettings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.*;

/**
 * leaf settings mapper test
 *
 * @author stone
 */
public class LeafSettingsMapperTest extends AbstractMapperTest {

    @Autowired
    LeafSettingsMapper leafSettingsMapper;

    @Test
    public void testInsert() {
        LeafSettings settings = createLeafSettings();
        assertEquals(1, leafSettingsMapper.insert(settings));
    }

    @Test
    public void testGetSettings() {
        LeafSettings settings = createLeafSettings();
        assertEquals(1, leafSettingsMapper.insert(settings));

        List<LeafSettings> settingsList = leafSettingsMapper.getSettings(leafName);
        assertNotNull(settingsList);
        assertEquals(1, settingsList.size());
    }

    @Test
    public void testGetSettingsAll() {
        LeafSettings settings = createLeafSettings();
        assertEquals(1, leafSettingsMapper.insert(settings));

        List<LeafSettings> all = leafSettingsMapper.getSettings(null);
        assertFalse(all == null);
        assertFalse(all.isEmpty());
    }

}
