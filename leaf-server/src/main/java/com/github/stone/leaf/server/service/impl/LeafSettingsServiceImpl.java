package com.github.stone.leaf.server.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.stone.leaf.server.dao.LeafSettingsMapper;
import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.service.LeafCurrentServiceFactory;
import com.github.stone.leaf.server.service.LeafSettingsService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * leaf setting service
 *
 * @author stone
 */
@Service
public class LeafSettingsServiceImpl implements LeafSettingsService, InitializingBean {

    @Autowired
    private LeafSettingsMapper settingsMapper;

    @Autowired
    private LeafCurrentServiceFactory currentServiceFactory;

    private LoadingCache<String, LeafSettings> settingsLoadingCache;

    public void afterPropertiesSet() throws Exception {
        settingsLoadingCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).refreshAfterWrite(10,
            TimeUnit.SECONDS).build(
            new CacheLoader<String, LeafSettings>() {

                @Override
                public LeafSettings load(String leafName) throws Exception {
                    return getSettings(leafName);
                }
            });

    }

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
        throw new IllegalArgumentException("not found settings");
    }

    public LeafSettings getLocalSettings(String leafName) {
        return settingsLoadingCache.getUnchecked(leafName);
    }

    @Transactional
    public void addLeafSettings(LeafSettings settings) {
        settings.setGmtCreate(new Date());
        settingsMapper.insert(settings);
        currentServiceFactory.getCurrentService(settings).resetLeafCurrent(settings);
    }
}
