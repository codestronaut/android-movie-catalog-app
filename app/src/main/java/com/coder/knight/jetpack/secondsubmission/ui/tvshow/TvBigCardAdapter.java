package com.coder.knight.jetpack.secondsubmission.ui.tvshow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.knight.jetpack.secondsubmission.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;
import com.coder.knight.jetpack.secondsubmission.R;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.ui.detail.DetailActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class TvBigCardAdapter extends RecyclerView.Adapter<TvBigCardAdapter.TvViewHolder> {
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private Context context;
    private ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();

    TvBigCardAdapter(Context context) {
        this.context = context;
    }

    void setTvShowList(ArrayList<TvShowEntity> tvShowList) {
        if (tvShowList == null) return;
        this.tvShowEntities.clear();
        this.tvShowEntities.addAll(tvShowList);
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wide_item_view, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        holder.onBind(tvShowEntities.get(position));
        // Click Listener
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_TV, tvShowEntities.get(position).getTvId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvShowEntities.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        TvShowEntity tvShowEntity;
        private RoundedImageView imgPoster;
        private TextView tvTitle, tvRating, tvPopularity;
        private RatingBar rbRating;

        TvViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_wide_poster);
            tvTitle = itemView.findViewById(R.id.title_text);
            tvRating = itemView.findViewById(R.id.rating_text);
            tvPopularity = itemView.findViewById(R.id.popularity_text);
            rbRating = itemView.findViewById(R.id.rb_rating);
        }

        void onBind(TvShowEntity tvShow) {
            this.tvShowEntity = tvShow;
            tvTitle.setText(tvShow.getTvTitle());
            tvRating.setText(String.valueOf(tvShow.getTvRating() / 2));
            tvPopularity.setText(tvShow.getTvPopularity());
            rbRating.setRating(tvShow.getTvRating() / 2);
            GlideApp.with(context)
                    .load(IMG_BASE_URL + tvShow.getTvBackdrop())
                    .apply(new RequestOptions().override(1920, 1080))
                    .into(imgPoster);
        }
    }
}
