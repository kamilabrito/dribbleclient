package com.kamilabrito.meuaptchallenge.ui;

import com.kamilabrito.meuaptchallenge.api.model.Shots;
import com.kamilabrito.meuaptchallenge.base.BaseView;

public interface DetailsView extends BaseView{
    void setProgressBarStatus(int visible);

    void loadInfoOnView(Shots items);

    void showToastMessage(int no_shots_available);
}
