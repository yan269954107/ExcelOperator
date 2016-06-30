package com.generator.common;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by yanxinwei on 16/6/29.
 */
public final class Handlers {

    private final static Handler MAIN = new Handler(Looper.getMainLooper());

    public static void postMain(Runnable runnable) {
        MAIN.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long delayMillis) {
        MAIN.postDelayed(runnable, delayMillis);
    }

}
