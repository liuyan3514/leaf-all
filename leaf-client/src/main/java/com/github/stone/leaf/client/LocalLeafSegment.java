package com.github.stone.leaf.client;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.github.stone.leaf.api.LeafCurrentFacade;
import com.github.stone.leaf.protocol.LeafSegment;

/**
 * local leaf segment
 *
 * @author stone
 */
class LocalLeafSegment {

    /**
     * segment used
     */
    private volatile int used;

    /**
     * segment size
     */
    private volatile int maxUsed;

    /**
     * segment begin
     */
    private volatile long begin;

    /**
     * segment cache
     */
    private volatile Future<LeafSegment> segmentCache;

    /**
     * leaf name
     */
    private final String leafName;

    /**
     * thread pool for load new segment
     */
    private final ExecutorService executorService;

    /**
     * facade for load new segment
     */
    private final LeafCurrentFacade leafCurrentFacade;

    LocalLeafSegment(String leafName, ExecutorService executorService, LeafCurrentFacade leafCurrentFacade) {
        this.leafName = leafName;
        this.executorService = executorService;
        this.leafCurrentFacade = leafCurrentFacade;
    }

    synchronized long next() throws InterruptedException {
        // load next segment
        if (used == 1 && segmentCache == null) {
            loadSegmentCache();
        }
        // not enough id of current segment
        if (used == maxUsed) {
            LeafSegment newSegment = getNewLeafSegment();
            begin = newSegment.getBegin();
            maxUsed = newSegment.getDelta();
            used = 0;
        }
        return begin + (used++);
    }

    /**
     * load segment cache when segmentCache is null
     */
    private void loadSegmentCache() {
        segmentCache = executorService.submit(new Callable<LeafSegment>() {
            public LeafSegment call() throws Exception {
                return leafCurrentFacade.nextSegment(leafName);
            }
        });
    }

    /**
     * get new leaf segment
     */
    private LeafSegment getNewLeafSegment() throws InterruptedException {
        LeafSegment newSegment = null;
        if (segmentCache != null) {
            try {
                newSegment = segmentCache.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException threadInterrupted) {
                throw threadInterrupted;
            } catch (ExecutionException invokeApiEx) {
                // get segment cache failure
                segmentCache = null;
            } catch (TimeoutException invokeApiTimeoutEx) {
                // ignore and to be next segment when API is timeout
            }
        }
        if (newSegment == null) {
            newSegment = leafCurrentFacade.nextSegment(leafName);
        }
        return newSegment;
    }
}
