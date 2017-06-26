package com.github.stone.leaf.server.dao;

import java.util.List;

import com.github.stone.leaf.server.entity.LeafSettings;

/**
 * leaf settings mapper
 *
 * @author stone
 */
public interface LeafSettingsMapper {

    /**
     * insert leaf settings
     *
     * @param settings
     * @return rows
     */
    int insert(LeafSettings settings);

    /**
     * get leaf settings
     *
     * @param leafName
     * @return leaf settings
     */
    List<LeafSettings> getSettings(String leafName);
}
