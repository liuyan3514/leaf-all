package com.github.stone.leaf.server.service;

import java.util.List;

import com.github.stone.leaf.server.entity.LeafSettings;

/**
 *
 *
 * @author stone
 */
public interface LeafSettingsService {

    /**
     * get all leaf settings
     *
     * @return
     */
    List<LeafSettings> getLeafSettings();

    /**
     * get leaf settings by leaf name
     *
     * @param leafName
     * @return minimum value and segment size
     */
    LeafSettings getLeafSettings(String leafName);

    /**
     * get leaf settings with cache by leaf name
     *
     * @param leafName
     * @return minimum value and segment size
     */
    LeafSettings getLeafSettingsWithCache(String leafName);

    /**
     * add leaf settings
     *
     * @param leafSettings
     */
    void addLeafSettings(LeafSettings leafSettings);

}
