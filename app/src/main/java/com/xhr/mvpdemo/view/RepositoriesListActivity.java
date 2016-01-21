package com.xhr.mvpdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.common.collect.ImmutableList;
import com.xhr.mvpdemo.R;
import com.xhr.mvpdemo.presenter.IRepoListPresenter;
import com.xhr.mvpdemo.presenter.LoginPresenter;
import com.xhr.mvpdemo.presenter.RepoListPresenter;
import com.xhr.mvpdemo.repository.model.Repository;
import com.xhr.mvpdemo.view.adapter.RepositoriesListAdapter;

import java.util.ArrayList;



public class RepositoriesListActivity  extends Activity implements IRepoListView {

    ListView lvRepositories;

    ProgressBar pbLoading;

    IRepoListPresenter presenter;

  //  AnalyticsManager analyticsManager;

    private RepositoriesListAdapter repositoriesListAdapter;

    private String userName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);

        Intent intent=getIntent();
        if(intent!=null)
        userName=intent.getStringExtra("username");

        lvRepositories=(ListView)findViewById(R.id.lvRepositories);
        pbLoading=(ProgressBar)findViewById(R.id.pbLoading);
        presenter = new RepoListPresenter(this);
        presenter.loadRepositories(userName);

        repositoriesListAdapter = new RepositoriesListAdapter(this, new ArrayList<Repository>());
        lvRepositories.setAdapter(repositoriesListAdapter);
    }
//
//    @Override
//    protected void setupActivityComponent() {
//        GithubClientApplication.get(this).getUserComponent()
//                .plus(new RepositoriesListActivityModule(this))
//                .inject(this);
//    }

    @Override
    public void showLoading(boolean loading) {
        lvRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
    @Override
    public void setRepositories(ImmutableList<Repository> repositories) {
        repositoriesListAdapter.clear();
        repositoriesListAdapter.addAll(repositories);
    }

//    @OnItemClick(R.id.lvRepositories)
//    public void onRepositoryClick(int position) {
//        Repository repository = repositoriesListAdapter.getItem(position);
//        RepositoryDetailsActivity.startWithRepository(repository, this);
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        GithubClientApplication.get(this).releaseUserComponent();
//    }
}
