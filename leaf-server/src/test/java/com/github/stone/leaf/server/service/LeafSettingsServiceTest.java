package com.github.stone.leaf.server.service;

import java.util.List;

import com.github.stone.leaf.server.entity.LeafSettings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.*;

/**
 * leaf setting service
 *
 * @author stone
 */
@Transactional
public class LeafSettingsServiceTest extends AbstractServiceTest {

    @Autowired
    private LeafSettingsService settingsService;

    @Test
    public void testGetAll() {
        LeafSettings settings = createLeafSettings();
        settingsService.addLeafSettings(settings);

        List<LeafSettings> all = settingsService.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
    }

    @Test
    public void testGetSettings() {
        LeafSettings settings = createLeafSettings();
        settingsService.addLeafSettings(settings);

        assertNotNull(settingsService.getSettings(leafName));
    }

    @Test
    public void testAddLeafSettings() {
        LeafSettings settings = createLeafSettings();
        settingsService.addLeafSettings(settings);
    }

    @Test
    public void testGetLocalSettings() {
        LeafSettings settings = createLeafSettings();
        settingsService.addLeafSettings(settings);

        assertNotNull(settingsService.getLocalSettings(leafName));
    }

}
