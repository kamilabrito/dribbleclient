package com.kamilabrito.meuaptchallenge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kamilabrito.meuaptchallenge.R;
import com.kamilabrito.meuaptchallenge.api.model.Shots;
import com.kamilabrito.meuaptchallenge.base.BaseActivity;
import com.kamilabrito.meuaptchallenge.injection.DaggerViewsComponent;
import com.kamilabrito.meuaptchallenge.injection.ViewsModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements HomeView, OnItemClickListener {

    private static final String SHOT_IT = "shot_id";
    @BindView(R.id.recycler_view)
    RecyclerView mShotsListRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Inject
    HomePresenter mPresenter;

    ShotsRecyclerViewAdapter mAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mAdapter = new ShotsRecyclerViewAdapter(this);
        mAdapter.setOnItemClickListener(this);
        mShotsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mShotsListRecyclerView.setAdapter(mAdapter);
        mPresenter.getShotsList();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }


    @Override
    protected void resolveDaggerDependency() {
        DaggerViewsComponent.builder()
                .appComponent(getApplicationComponent())
                .viewsModule(new ViewsModule(this))
                .build().inject(this);
    }

    @Override
    public void setProgressBarStatus(int visible) {
        mProgressBar.setVisibility(visible);
    }

    @Override
    public void setUsersToList(List<Shots> items) {
        mAdapter.addUsers(items);
    }

    @Override
    public void showToastMessage(int text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(Shots item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        Bundle b = new Bundle();
        b.putInt(SHOT_IT, item.getId());
        intent.putExtras(b);
        startActivity(intent);
    }
}
