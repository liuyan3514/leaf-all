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
     * get all leaf settings
     *
     * @return all leaf settings
     */
    List<LeafSettings> getAll();

    /**
     * get one leaf settings
     *
     * @param leafName
     * @return leaf settings
     */
    LeafSettings getSettings(String leafName);

}
