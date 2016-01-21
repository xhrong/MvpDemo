package com.xhr.mvpdemo.delivery;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by xhrong on 2016/1/14.
 */
public class MessageDelivery {

    private Handler mHandler;

    public MessageDelivery() {
        this(new Handler(Looper.getMainLooper()));
    }

    public MessageDelivery(Handler handler) {
        mHandler = handler;
    }

    public void deliveryMessage(Runnable runnable) {
        if (mHandler != null)
            mHandler.post(runnable);
    }
}
