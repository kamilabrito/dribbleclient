package com.kamilabrito.meuaptchallenge.ui;

import android.content.Context;
import android.util.Log;

import com.kamilabrito.meuaptchallenge.R;
import com.kamilabrito.meuaptchallenge.api.RetrofitApi;
import com.kamilabrito.meuaptchallenge.api.model.Shots;
import com.kamilabrito.meuaptchallenge.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HomePresenter extends BasePresenter<HomeView> {

    @Inject
    RetrofitApi mRetrofit;

    @Inject
    Context mContext;

    @Inject
    public HomePresenter() {
    }

    public void getShotsList() {
        getView().setProgressBarStatus(VISIBLE);

        Observable<List<Shots>> call = mRetrofit.getShots(30, mContext.getResources().getString(R.string.client_access_token));
        subscribe(call, new Observer<List<Shots>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Shots> shotsResponse) {
                getView().setProgressBarStatus(GONE);
                if (shotsResponse != null) {
                    List<Shots> items = shotsResponse;
                    getView().setUsersToList(items);
                } else {
                    getView().showToastMessage(R.string.no_shots_available);
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().setProgressBarStatus(GONE);
                getView().showToastMessage(R.string.connection_error);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
