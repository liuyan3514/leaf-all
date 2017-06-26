package com.github.stone.leaf.server.service;

import java.util.List;

import com.github.stone.leaf.server.entity.LeafSettings;

/**
 * leaf setting service
 *
 * @author stone
 */
public interface LeafSettingsService {

    /**
     * get all leaf settings
     *
     * @return get all leaf settings
     */
    List<LeafSettings> getAll();

    /**
     * get leaf settings by leaf name
     *
     * @param leafName
     * @return minimum value and segment size
     */
    LeafSettings getSettings(String leafName);

    /**
     * get leaf settings with cache by leaf name
     *
     * @param leafName
     * @return minimum value and segment size
     */
    LeafSettings getLocalSettings(String leafName);

    /**
     * add leaf settings
     *
     * @param settings
     */
    void addLeafSettings(LeafSettings settings);

}
