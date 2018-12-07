package li.yifei4.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum ExecutorServiceHolder {
    INSTANCE;
    private ExecutorService service;
    ExecutorServiceHolder(){
        service = new ThreadPoolExecutor(16, 16,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
    public ExecutorService getInstance(){
        return service;
    }

}
