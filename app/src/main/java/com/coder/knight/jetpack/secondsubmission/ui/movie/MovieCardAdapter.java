package com.coder.knight.jetpack.secondsubmission.ui.movie;

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
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.ui.detail.DetailActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class MovieCardAdapter extends RecyclerView.Adapter<MovieCardAdapter.MovieListViewHolder> {
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private Context context;
    private ArrayList<MovieEntity> movieEntities = new ArrayList<>();

    MovieCardAdapter(Context context) {
        this.context = context;
    }

    void setMovieList(ArrayList<MovieEntity> movieList) {
        if (movieList == null) return;
        this.movieEntities.clear();
        this.movieEntities.addAll(movieList);
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
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

    class MovieListViewHolder extends RecyclerView.ViewHolder {
        MovieEntity movieEntity;
        private RoundedImageView imgPoster;
        private TextView tvTitle, tvRating;

        MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.poster_image);
            tvTitle = itemView.findViewById(R.id.item_title_text);
            tvRating = itemView.findViewById(R.id.item_rating_text);
        }

        void onBind(MovieEntity movies) {
            this.movieEntity = movies;
            tvTitle.setText(movies.getMovieTitle());
            tvRating.setText(String.valueOf(movies.getMovieRating() / 2));
            GlideApp.with(context)
                    .load(IMG_BASE_URL + movies.getMoviePoster())
                    .apply(new RequestOptions().override(1920, 1080))
                    .into(imgPoster);
        }
    }
}
