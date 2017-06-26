package com.github.stone.leaf.server.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.github.stone.leaf.server.Constants;
import com.github.stone.leaf.server.dao.LeafSettingsMapper;
import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.service.LeafSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * leaf setting service
 *
 * @author stone
 */
@Service
public class LeafSettingsServiceImpl implements LeafSettingsService {

    @Autowired
    LeafSettingsMapper settingsMapper;

    public List<LeafSettings> getAll() {
        List<LeafSettings> allSettings = settingsMapper.getAll();
        if (!CollectionUtils.isEmpty(allSettings)) {
            return allSettings;
        }
        return Collections.emptyList();
    }

    public LeafSettings getSettings(String leafName) {
        LeafSettings settings = settingsMapper.getSettings(leafName);
        if (settings != null) {
            return settings;
        }
        throw new IllegalArgumentException("");
    }

    public LeafSettings getLocalSettings(String leafName) {
        return null;
    }

    public void addLeafSettings(LeafSettings settings) {
        settings.setGmtCreate(new Date());
        settingsMapper.insert(settings);
    }
}
