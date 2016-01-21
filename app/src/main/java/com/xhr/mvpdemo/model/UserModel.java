package com.xhr.mvpdemo.model;

import android.os.Looper;
import android.util.Log;

import com.google.common.collect.ImmutableList;
import com.xhr.mvpdemo.presenter.OnLoginListener;
import com.xhr.mvpdemo.repository.IUserRepository;
import com.xhr.mvpdemo.repository.OnGetUserListener;
import com.xhr.mvpdemo.repository.UserRepository;
import com.xhr.mvpdemo.repository.model.Repository;

import rx.Observable;

/**
 * Created by xhrong on 2016/1/12.
 */
public class UserModel implements IUser {

  //  private OnLoginListener loginListener;
    private IUserRepository userRepository;

    public UserModel() {
        userRepository = new UserRepository();
    }




    @Override
    public void login(final String userName,final  OnLoginListener loginListener) {

       userRepository.getUseDateStore().getUser(userName, new OnGetUserListener() {
           @Override
           public void onSuccess() {

               if (Looper.myLooper() == Looper.getMainLooper())
                   Log.e("UserModel.getUser", "MainThread");
               else
                   Log.e("UserModel.getUser", "SubThread");
               loginListener.onLoginSuccess();
           }

           @Override
           public void onFail() {

               loginListener.onLoginFail();
           }
       });

    }

    @Override
    public Observable<ImmutableList<Repository>> getRepositories(String userName) {
        return userRepository.getUseDateStore().getRepositories(userName);
    }
}
