package com.kamilabrito.meuaptchallenge.injection;

import android.app.Activity;
import android.telecom.Call;

import com.kamilabrito.meuaptchallenge.ui.DetailsView;
import com.kamilabrito.meuaptchallenge.ui.HomeView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {

    private Activity mView;

    public ViewsModule(Activity view) {
        mView = view;
    }

    @ForApplication
    @Provides
    HomeView provideHomeActivityView() {
        return (HomeView) mView;
    }

    @ForApplication
    @Provides
    DetailsView provideDetailsActivityView() {
        return (DetailsView) mView;
    }

}
