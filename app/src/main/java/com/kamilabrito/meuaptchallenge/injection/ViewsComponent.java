package com.kamilabrito.meuaptchallenge.injection;

import com.kamilabrito.meuaptchallenge.ui.DetailsActivity;
import com.kamilabrito.meuaptchallenge.ui.HomeActivity;

import dagger.Component;

@ForApplication
@Component(modules = ViewsModule.class, dependencies = AppComponent.class)
public interface ViewsComponent {

    void inject(HomeActivity activity);
    void inject(DetailsActivity activity);

}