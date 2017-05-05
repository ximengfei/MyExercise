package com.qanzone.mypreciousgift.utils;

/**
 * Created by admin on 2017/5/5.
 */

import java.util.concurrent.Semaphore;

import android.os.Handler;
import android.os.Looper;


public abstract class MainThread {
    public static final Handler mHandler = new Handler(Looper.getMainLooper());

    public static final boolean is() {
        return Thread.currentThread() == get();
    }
    public static final Thread get() {
        return Looper.getMainLooper().getThread();
    }
    public static final boolean run(final Runnable runnable) {
        return run(runnable, false);
    }
    public static final boolean run(final Runnable runnable, boolean waitReturn) {
        if (runnable == null)
            return true;

        if (is()) {
            runnable.run();
            return true;

        } else {
            if (waitReturn) {
                final Semaphore ret = new Semaphore(0);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            runnable.run();
                        } finally {
                            ret.release();
                        }
                    }
                });
                ret.acquireUninterruptibly();
                return true;

            } else {

                mHandler.post(runnable);
                return false;
            }
        }
    }
    public static final void runLater(Runnable runnable) {
        if (runnable == null)
            return;

        mHandler.post(runnable);
    }
    public static final void runLater(Runnable runnable, long delayMillis) {
        if (runnable == null)
            return;

        mHandler.postDelayed(runnable, delayMillis);
    }
    public static final void cancel(Runnable runnable) {
        if (runnable != null) {
            mHandler.removeCallbacks(runnable);
        }
    }
}
