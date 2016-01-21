package com.xhr.mvpdemo.repository;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by xhrong on 2016/1/12.
 *
 * 这个类提供了一种解决子线程中执行回调更新UI的实现模式。即线程中调用sendMessage，回调类通过Handler中转，实现子线种与UI线程的切换。
 */
public interface OnGetUserListener {

//    private Handler handler;
//    protected static final int SUCCESS_MESSAGE = 0;
//    protected static final int FAILURE_MESSAGE = 1;
//

    public  void onSuccess();

    public  void onFail();

//    public OnUserRepoListener() {
//        //通过handler实现子线程和主线程的切换
//        handler = new ResponderHandler (this, Looper.myLooper());
//    }


//    /**
//     * Helper method to create Message instance from handler
//     *
//     * @param responseMessageId   constant to identify Handler message
//     * @param responseMessageData object to be passed to message receiver
//     * @return Message instance, should not be null
//     */
//    protected Message obtainMessage(int responseMessageId, Object responseMessageData) {
//        return Message.obtain(handler, responseMessageId, responseMessageData);
//    }
//
//
//    final public void sendSuccessMessage() {
//        sendMessage(obtainMessage(SUCCESS_MESSAGE, null));
//    }
//
//    final public void sendFailMessage() {
//        sendMessage(obtainMessage(FAILURE_MESSAGE, null));
//    }
//
//    // Methods which emulate android's Handler and Message methods
//    protected void handleMessage(Message message) {
//        try {
//            switch (message.what) {
//                case SUCCESS_MESSAGE:
//                    onSuccess();
//                    break;
//                case FAILURE_MESSAGE:
//                    onFail();
//                    break;
//            }
//        } catch (Throwable error) {
//            onFail();
//        }
//    }
//
//    protected void sendMessage(Message msg) {
//        if (handler != null && !Thread.currentThread().isInterrupted()) { // do not send messages if request has been cancelled
//            handler.sendMessage(msg);
//        }
//    }
//
//
//    /**
//     * Avoid leaks by using a non-anonymous handler class.
//     */
//    private static class ResponderHandler extends Handler {
//        private final OnUserRepoListener mResponder;
//
//        ResponderHandler(OnUserRepoListener mResponder, Looper looper) {
//            super(looper);
//            this.mResponder = mResponder;
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            mResponder.handleMessage(msg);
//        }
//    }
}
