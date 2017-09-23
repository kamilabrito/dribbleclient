package com.kamilabrito.meuaptchallenge.ui;

import com.kamilabrito.meuaptchallenge.api.model.Shots;
import com.kamilabrito.meuaptchallenge.base.BaseView;

import java.util.List;

public interface HomeView extends BaseView {

    void setProgressBarStatus(int visible);

    void setUsersToList(List<Shots> shots);

    void showToastMessage(int text);
}
