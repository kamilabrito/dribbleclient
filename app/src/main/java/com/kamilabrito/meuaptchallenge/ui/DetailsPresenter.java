package com.kamilabrito.meuaptchallenge.ui;

import android.content.Context;

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

public class DetailsPresenter extends BasePresenter<DetailsView> {

    @Inject
    RetrofitApi mRetrofit;

    @Inject
    Context mContext;

    @Inject
    public DetailsPresenter() {
    }

    public void getShot(int shotId) {
        getView().setProgressBarStatus(VISIBLE);

        Observable<Shots> call = mRetrofit.getShot(shotId, mContext.getResources().getString(R.string.client_access_token));
        subscribe(call, new Observer<Shots>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Shots shotsResponse) {
                getView().setProgressBarStatus(GONE);
                if (shotsResponse != null) {
                    Shots item = shotsResponse;
                    getView().loadInfoOnView(item);
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
