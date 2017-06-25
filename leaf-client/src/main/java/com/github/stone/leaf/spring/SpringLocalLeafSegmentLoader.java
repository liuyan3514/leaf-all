package com.github.stone.leaf.spring;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.stone.leaf.api.LeafCurrentFacade;
import com.github.stone.leaf.client.LocalLeafSegments;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * spring local leaf segment init
 *
 * @author stone
 */
public class SpringLocalLeafSegmentLoader implements InitializingBean, DisposableBean {

    /**
     * thread pool for load new segment
     */
    private ExecutorService executorService;

    /**
     * facade for load new segment
     */
    private LeafCurrentFacade leafCurrentFacade;

    public void destroy() throws Exception {
        LocalLeafSegments.destroy();
    }

    public void afterPropertiesSet() throws Exception {
        if (executorService == null) {
            ThreadFactory threadFactory = new ThreadFactory() {
                private AtomicInteger threadId = new AtomicInteger();

                public Thread newThread(Runnable command) {
                    return new Thread(command, "leaf-thread-" + threadId.incrementAndGet());
                }
            };
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
            executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, workQueue,
                threadFactory);
        }
        LocalLeafSegments.setUp(executorService, leafCurrentFacade);
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setLeafCurrentFacade(LeafCurrentFacade leafCurrentFacade) {
        this.leafCurrentFacade = leafCurrentFacade;
    }
}
