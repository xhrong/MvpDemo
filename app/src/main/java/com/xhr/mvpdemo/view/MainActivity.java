package com.xhr.mvpdemo.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xhr.mvpdemo.R;
import com.xhr.mvpdemo.presenter.ILoginPresenter;
import com.xhr.mvpdemo.presenter.LoginPresenter;


public class MainActivity extends ActionBarActivity implements ILoginView {


    private Button login;
    private EditText etName;
    private ProgressBar progressBar;

    private ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login);
        etName=(EditText)findViewById(R.id.etName);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginPresenter = new LoginPresenter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.login();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public String getUserName() {
        return etName.getText().toString();
    }

    @Override
    public void showProgress() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        this.progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoginResultTips(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void gotoListActivity(){
        Intent intent=new Intent(MainActivity.this,RepositoriesListActivity.class);
        intent.putExtra("username",getUserName());
        startActivity(intent);
    }
}
