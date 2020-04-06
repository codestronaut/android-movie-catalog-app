package com.coder.knight.jetpack.secondsubmission.ui.tvshow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TvCardAdapter extends RecyclerView.Adapter<TvCardAdapter.TvViewHolder> {
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private Context context;
    private ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();

    TvCardAdapter(Context context) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
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
        private TextView tvTitle, tvRating;

        TvViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.poster_image);
            tvTitle = itemView.findViewById(R.id.item_title_text);
            tvRating = itemView.findViewById(R.id.item_rating_text);
        }

        void onBind(TvShowEntity tvShow) {
            this.tvShowEntity = tvShow;
            tvTitle.setText(tvShow.getTvTitle());
            tvRating.setText(String.valueOf(tvShow.getTvRating() / 2));
            GlideApp.with(context)
                    .load(IMG_BASE_URL + tvShow.getTvPoster())
                    .apply(new RequestOptions().override(1920, 1080))
                    .into(imgPoster);
        }
    }
}
