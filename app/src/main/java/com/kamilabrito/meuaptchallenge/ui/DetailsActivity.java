package com.kamilabrito.meuaptchallenge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kamilabrito.meuaptchallenge.R;
import com.kamilabrito.meuaptchallenge.api.model.Shots;
import com.kamilabrito.meuaptchallenge.base.BaseActivity;
import com.kamilabrito.meuaptchallenge.injection.DaggerViewsComponent;
import com.kamilabrito.meuaptchallenge.injection.ViewsModule;
import com.kamilabrito.meuaptchallenge.util.DateUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity implements DetailsView {

    private static final String SHOT_IT = "shot_id";
    private int shotId;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_created_at)
    TextView mCreatedAt;
    @BindView(R.id.tv_comment_count)
    TextView mCommentCount;
    @BindView(R.id.tv_description)
    TextView mDescription;
    @BindView(R.id.tv_views_count)
    TextView mViewsCount;
    @BindView(R.id.iv_image)
    ImageView mImage;

    @Inject
    DetailsPresenter mDetailsPresenter;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            shotId = b.getInt(SHOT_IT);
            mDetailsPresenter.getShot(shotId);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_details;
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
    public void loadInfoOnView(Shots items) {
        mTitle.setText(items.getTitle());
        mViewsCount.setText(getResources().getString(R.string.views) + " " + items.getViews_count()+"");
        try {
            mCreatedAt.setText(getResources().getString(R.string.created_at) + " " + DateUtils.formateDate(items.getCreated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mDescription.setText(Html.fromHtml(items.getDescription()));
        mCommentCount.setText(getResources().getString(R.string.comments) + " " + items.getComments_count()+"");
        mImage.setDrawingCacheEnabled(true);
        Picasso.with(this)
                .load(items.getImages().getNormal())
                .placeholder(R.mipmap.ic_launcher_round)
                .into(mImage);

    }

    @Override
    public void showToastMessage(int text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
