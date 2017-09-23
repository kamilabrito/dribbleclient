package com.kamilabrito.meuaptchallenge.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kamilabrito.meuaptchallenge.App;
import com.kamilabrito.meuaptchallenge.injection.AppComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDaggerDependency();
    }

    protected AppComponent getApplicationComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void resolveDaggerDependency() {}

    protected abstract int getContentView();
}