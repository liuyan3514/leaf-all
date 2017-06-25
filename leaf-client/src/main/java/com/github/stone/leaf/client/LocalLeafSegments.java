package com.github.stone.leaf.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

import com.github.stone.leaf.api.LeafCurrentFacade;

/**
 * all local leaf segment
 *
 * @author stone
 */
public class LocalLeafSegments {

    /**
     * just use static
     */
    private LocalLeafSegments() {}

    /**
     * thread pool for load new segment
     */
    private static volatile ExecutorService executorService;

    /**
     * facade for load new segment
     */
    private static volatile LeafCurrentFacade leafCurrentFacade;

    /**
     * leaf map segment
     */
    private static final ConcurrentMap<String, LocalLeafSegment> leafSegments
        = new ConcurrentHashMap<String, LocalLeafSegment>();

    /**
     * all local leaf segment init
     *
     * @param executorService   thread pool for load new segment
     * @param leafCurrentFacade facade for load new segment
     */
    public static synchronized void setUp(ExecutorService executorService, LeafCurrentFacade leafCurrentFacade) {
        if (executorService == null) {
            throw new NullPointerException("executorService is null");
        }
        if (leafCurrentFacade == null) {
            throw new NullPointerException("leafCurrentFacade is null");
        }
        LocalLeafSegments.executorService = executorService;
        LocalLeafSegments.leafCurrentFacade = leafCurrentFacade;
    }

    /**
     * all local leaf segment destroy
     */
    public static synchronized void destroy() {
        if (LocalLeafSegments.executorService != null) {
            ExecutorService executorService = LocalLeafSegments.executorService;
            LocalLeafSegments.executorService = null;
            executorService.shutdown();
        }
        if (LocalLeafSegments.leafCurrentFacade != null) {
            LocalLeafSegments.leafCurrentFacade = null;
        }
    }

    /**
     * next leaf id
     *
     * @param leafName
     * @return
     */
    public static final long nextId(String leafName) {
        try {
            return nextIdInterruptibly(leafName);
        } catch (InterruptedException ex) {
            throw new RuntimeException("thread interrupt", ex);
        }
    }

    /**
     * next leaf id maybe throw InterruptedException
     *
     * @param leafName leaf name
     * @return next id
     * @throws InterruptedException
     */
    public static final long nextIdInterruptibly(String leafName) throws InterruptedException {
        if (executorService == null) {
            throw new IllegalStateException("executorService is null");
        }
        if (leafCurrentFacade == null) {
            throw new IllegalStateException("leafCurrentFacade is null");
        }
        LocalLeafSegment segment = leafSegments.get(leafName);
        if (segment == null) {
            segment = new LocalLeafSegment(leafName, executorService, leafCurrentFacade);
            LocalLeafSegment previous = leafSegments.putIfAbsent(leafName, segment);
            if (previous != null) {
                segment = previous;
            }
        }
        return segment.next();
    }

}
