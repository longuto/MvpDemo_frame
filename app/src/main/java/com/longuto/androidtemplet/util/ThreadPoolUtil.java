package com.longuto.androidtemplet.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuhonghai on 2016/5/31.
 */
public class ThreadPoolUtil {
    private static ThreadPoolUtil instance;
    private final int EXECUTOR_THREADS = Runtime.getRuntime().availableProcessors();
    private ExecutorService processorsPools,cachedPools;

    private ThreadPoolUtil(){

    }

    public synchronized static ThreadPoolUtil getInstance(){
        if (instance==null){
            instance=new ThreadPoolUtil();
        }
        return instance;
    }

    public ExecutorService getProcessorsPools(){
        if (processorsPools==null||processorsPools.isShutdown()){
            processorsPools = Executors.newFixedThreadPool(EXECUTOR_THREADS);
        }
        return processorsPools;
    }

    public ExecutorService getCachedPools(){
        if (cachedPools==null){
            cachedPools = Executors.newCachedThreadPool();
        }
        return cachedPools;
    }
}
