package com.github.stone.leaf.server.service;

import java.util.List;

import com.github.stone.leaf.server.entity.LeafSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * leaf current service factory
 *
 * @author stone
 */
@Service
public class LeafCurrentServiceFactory {

    @Autowired
    List<LeafCurrentService> currentServices;

    public LeafCurrentService getCurrentService(LeafSettings settings) {
        for (LeafCurrentService currentService : currentServices) {
            if (currentService.support(settings)) {
                return currentService;
            }
        }
        throw new IllegalArgumentException("not support leaf");
    }
    
}
