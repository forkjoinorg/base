package org.forkjoin.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 设置一个间隔执行时间，每次大于这个时间的适合执行
 * 程序尽力保证，刚刚达到要求
 * @author zuoge85 on 14-3-10.
 */
public abstract class TimeThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(TimeThread.class);

    private final int frameTimeSpan;

    private volatile boolean isRun = true;

    public TimeThread(String name, int frameTimeSpan) {
        super(name);
        this.frameTimeSpan = frameTimeSpan;
    }

    @Override
    public void run() {
        threadMethod();
    }

    private void threadMethod() {
        long startTime = System.currentTimeMillis();
        int count = 0;
        while (isRun) {
            try {
                long time = System.currentTimeMillis();
                frame();

                count++;
                long sleep = (startTime + count * frameTimeSpan) - time;
                if(sleep>0){
                    Thread.sleep(sleep);
                }
            } catch (InterruptedException e) {
                break;
            } catch (Throwable e) {
                errorHandler(e);
            }
        }
    }

    protected abstract void frame();


    protected abstract void errorHandler(Throwable e);

    /**
     * 关闭线程，注意，这个回等待当前的frame执行完毕
     * @throws InterruptedException
     */
    public void close() throws InterruptedException {
        isRun = false;
        interrupt();
        join();
    }
}
