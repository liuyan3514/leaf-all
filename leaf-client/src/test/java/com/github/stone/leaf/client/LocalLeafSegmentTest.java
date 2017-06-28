package com.github.stone.leaf.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import com.github.stone.leaf.api.LeafCurrentFacade;
import com.github.stone.leaf.protocol.LeafSegment;

import junit.framework.TestCase;

/**
 * local leaf segment test
 *
 * @author stone
 */
public class LocalLeafSegmentTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        LeafCurrentFacade leafCurrentFacade = new LeafCurrentFacade() {
            int delta = 10;
            AtomicLong leafId = new AtomicLong(100);

            public LeafSegment nextSegment(String leafName) {
                return new LeafSegment(delta, leafId.getAndAdd(delta));
            }
        };
        LocalLeafSegments.setUp(executorService, leafCurrentFacade);
    }

    @Override
    protected void tearDown() throws Exception {
        LocalLeafSegments.destroy();
    }

    /**
     * test leaf next id
     *
     * @throws InterruptedException
     */
    public void testNextId() {
        String leafName = "order";
        for (int i = 0; i < 1000; i++) {
            LocalLeafSegments.nextId(leafName);
        }
    }
}
