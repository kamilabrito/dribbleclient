package com.kamilabrito.meuaptchallenge;

import android.app.Application;

import com.kamilabrito.meuaptchallenge.injection.AppComponent;
import com.kamilabrito.meuaptchallenge.injection.AppModule;
import com.kamilabrito.meuaptchallenge.injection.DaggerAppComponent;

public class App extends Application {
    private static App mInstance;
    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initializeApplicationComponent();

    }

    public static App getInstance() {
        return mInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initializeApplicationComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, getApplicationContext().getResources().getString(R.string.base_url)))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}

