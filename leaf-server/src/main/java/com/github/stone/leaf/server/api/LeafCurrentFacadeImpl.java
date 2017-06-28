package com.github.stone.leaf.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.stone.leaf.api.LeafCurrentFacade;
import com.github.stone.leaf.protocol.LeafSegment;
import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.service.LeafCurrentServiceFactory;
import com.github.stone.leaf.server.service.LeafSettingsService;

/**
 * leaf current value
 *
 * @author stone
 */
@Service
public class LeafCurrentFacadeImpl implements LeafCurrentFacade {

    @Autowired
    LeafSettingsService settingsService;

    @Autowired
    LeafCurrentServiceFactory currentServiceFactory;

    public LeafSegment nextSegment(String leafName) {
        LeafSettings settings = settingsService.getLocalSettings(leafName);
        if (settings != null) {
            return currentServiceFactory.getCurrentService(settings).nextSegment(settings);
        }
        throw new IllegalArgumentException("not found leaf");
    }
}
