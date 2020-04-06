package com.coder.knight.jetpack.secondsubmission.ui.movie;

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
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.ui.detail.DetailActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class MovieBigCardAdapter extends RecyclerView.Adapter<MovieBigCardAdapter.MovieViewHolder> {
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private Context context;
    private ArrayList<MovieEntity> movieEntities = new ArrayList<>();

    MovieBigCardAdapter(Context context) {
        this.context = context;
    }

    void setMovieList(ArrayList<MovieEntity> movieList) {
        if (movieList == null) return;
        this.movieEntities.clear();
        this.movieEntities.addAll(movieList);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wide_item_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.onBind(movieEntities.get(position));
        // Click Listener
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, movieEntities.get(position).getMovieId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieEntities.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        MovieEntity movieEntity;
        private RoundedImageView imgWidePoster;
        private TextView tvTitle, tvRating, tvPopularity;
        private RatingBar rbRating;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imgWidePoster = itemView.findViewById(R.id.img_wide_poster);
            tvTitle = itemView.findViewById(R.id.title_text);
            tvRating = itemView.findViewById(R.id.rating_text);
            tvPopularity = itemView.findViewById(R.id.popularity_text);
            rbRating = itemView.findViewById(R.id.rb_rating);
        }

        void onBind(MovieEntity movies) {
            this.movieEntity = movies;
            tvTitle.setText(movies.getMovieTitle());
            tvRating.setText(String.valueOf(movies.getMovieRating() / 2));
            tvPopularity.setText(movies.getMoviePopularity());
            rbRating.setRating(movies.getMovieRating() / 2);
            GlideApp.with(context)
                    .load(IMG_BASE_URL + movies.getMovieBackdrop())
                    .apply(new RequestOptions().override(1920, 1080))
                    .into(imgWidePoster);
        }
    }
}
