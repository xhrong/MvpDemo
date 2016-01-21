package com.xhr.mvpdemo.view;

/**
 * Created by xhrong on 2016/1/12.
 */
public interface ILoginView {
    public String getUserName();
    public void showProgress();
    public void hideProgress();
    public void showLoginResultTips(boolean isSuccess);
    public void gotoListActivity();
}
