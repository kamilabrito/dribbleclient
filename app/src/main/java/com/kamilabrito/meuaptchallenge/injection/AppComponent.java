package com.kamilabrito.meuaptchallenge.injection;

import android.content.Context;

import com.kamilabrito.meuaptchallenge.api.RetrofitApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    RetrofitApi exposeRetrofitApi();
    Context exposeContext();
}
