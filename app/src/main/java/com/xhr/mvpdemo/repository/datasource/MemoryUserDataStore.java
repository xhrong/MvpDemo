/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xhr.mvpdemo.repository.datasource;

import android.os.Looper;
import android.util.Log;

import com.google.common.collect.ImmutableList;
import com.xhr.mvpdemo.delivery.MessageDelivery;
import com.xhr.mvpdemo.repository.OnGetUserListener;
import com.xhr.mvpdemo.repository.model.Repository;
import com.xhr.mvpdemo.repository.model.User;

import org.apache.commons.lang3.NotImplementedException;

import java.net.ResponseCache;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * {@link IUserDataStore} implementation based on connections to the api (Cloud).
 */
public class MemoryUserDataStore implements IUserDataStore {


    public MemoryUserDataStore() {
    }

    @Override
    public void getUser(final String userName, final OnGetUserListener listener) {


        Observable.just(userName)
                .map(new Func1<String, User>() {
                    @Override
                    public User call(String s) {//这里在子线程中运行
                        if (Looper.myLooper() == Looper.getMainLooper())
                            Log.e("MemoryUserDataStore.getUser", "MainThread");
                        else
                            Log.e("MemoryUserDataStore.getUser", "SubThread");
                        if (!s.equals("xhrong")) {
                            User user = new User();
                            user.login = "test";
                            user.email = "test";
                            user.id = 1;
                            return user;
                        } else return null;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {//这里在主线程中运行
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail();
                    }

                    @Override
                    public void onNext(User s) {
                        if (Looper.myLooper() == Looper.getMainLooper())
                            Log.e("MemoryUserDataStore.getUser", "MainThread");
                        else
                            Log.e("MemoryUserDataStore.getUser", "SubThread");
                        if (s == null) {
                            listener.onFail();
                        } else {
                            listener.onSuccess();
                        }
                    }
                });
    }

    @Override
    public Observable<ImmutableList<Repository>> getRepositories(String userName) {
        throw new NotImplementedException("memory getRepositories");
    }
}
