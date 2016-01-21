package com.xhr.mvpdemo.presenter;

import com.xhr.mvpdemo.model.IUser;
import com.xhr.mvpdemo.model.UserModel;
import com.xhr.mvpdemo.view.ILoginView;

/**
 * Created by xhrong on 2016/1/12.
 */
public class LoginPresenter implements ILoginPresenter, OnLoginListener {

    private ILoginView loginView;

    private IUser user;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        user = new UserModel();
    }

    @Override
    public void login() {
        String userName=loginView.getUserName();
        if(userName==null && userName.equals("")){
            return;
        }
        loginView.showProgress();
        user.login(userName,this);
    }

    @Override
    public void onLoginSuccess() {
        loginView.hideProgress();
        loginView.showLoginResultTips(true);
        loginView.gotoListActivity();
    }

    @Override
    public void onLoginFail() {
        loginView.hideProgress();
        loginView.showLoginResultTips(false);
    }
}
