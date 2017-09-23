package com.kamilabrito.meuaptchallenge.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kamilabrito.meuaptchallenge.R;
import com.kamilabrito.meuaptchallenge.api.model.Shots;
import com.kamilabrito.meuaptchallenge.util.DateUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShotsRecyclerViewAdapter extends RecyclerView.Adapter<ShotsRecyclerViewAdapter.ViewHolder> {

    private List<Shots> mItems = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public ShotsRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shot, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ShotsRecyclerViewAdapter.ViewHolder holder, int position) {
        final Shots currentItem = mItems.get(position);

        if (currentItem != null) {
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(currentItem);
                }
            };
            holder.card.setOnClickListener(listener);
            holder.title.setText(currentItem.getTitle());
            holder.viewsCount.setText(mContext.getResources().getString(R.string.views) + " " + currentItem.getViews_count()+"");
            try {
                holder.createAt.setText(mContext.getResources().getString(R.string.created_at) + " " + DateUtils.formateDate(currentItem.getCreated_at()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.image.setDrawingCacheEnabled(true);
            Picasso.with(mContext)
                    .load(currentItem.getImages().getTeaser())
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return (null != mItems ? mItems.size() : 0);
    }

    public void addUsers(List<Shots> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_iv_image)
        ImageView image;
        @BindView(R.id.row_tv_title)
        TextView title;
        @BindView(R.id.row_tv_views_count)
        TextView viewsCount;
        @BindView(R.id.tv_created_at)
        TextView createAt;
        @BindView(R.id.row_card)
        CardView card;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
