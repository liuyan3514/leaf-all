package com.github.stone.leaf.server.service;

import com.github.stone.leaf.protocol.LeafSegment;
import com.github.stone.leaf.server.entity.LeafSettings;
import org.junit.Test;

import static junit.framework.TestCase.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * leaf current service
 *
 * @author stone
 */
@Transactional
public class LeafCurrentServiceTest extends AbstractServiceTest {

    @Autowired
    private LeafSettingsService settingsService;

    @Autowired
    private LeafCurrentServiceFactory currentServiceFactory;

    @Test
    public void testNextSegment() {
        LeafSettings settings = createLeafSettings();
        settingsService.addLeafSettings(settings);
        LeafCurrentService currentService =
            currentServiceFactory.getCurrentService(settings);
        LeafSegment segment = currentService.nextSegment(settings);
        assertEquals(settings.getMinimum().longValue(), segment.getBegin());
        LeafSegment newSegment = currentService.nextSegment(settings);
        assertEquals(segment.getBegin() + settings.getDelta(), newSegment.getBegin());
    }

    @Test
    public void testGetLeafCurrent() {
        LeafSettings settings = createLeafSettings();
        settingsService.addLeafSettings(settings);
        LeafCurrentService currentService =
            currentServiceFactory.getCurrentService(settings);
        Long current = currentService.getLeafCurrent(settings);
        assertEquals(settings.getMinimum().longValue(), current.longValue());
    }

}
