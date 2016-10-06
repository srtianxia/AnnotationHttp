package com.srtianxia.httplibs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by srtianxia on 2016/10/3.
 */

public class Dispatcher {
    private static final int CPU_COUNT = Runtime.getRuntime()
        .availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final long KEEP_ALIVE = 10L;

    private ExecutorService mExecutorService;

    private ThreadFactory mThreadFactory = new ThreadFactory() {
        private final AtomicInteger count = new AtomicInteger(1);

        @Override public Thread newThread(Runnable r) {
            return new Thread(r, "Dispatcher#" + count.getAndIncrement());
        }
    };


    public synchronized ExecutorService executorService() {
        if (mExecutorService == null) {
            mExecutorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), mThreadFactory);
        }
        return mExecutorService;
    }


    public void enqueue(RealCall.AsyncCall asyncCall) {
        executorService().execute(asyncCall);
    }

}
