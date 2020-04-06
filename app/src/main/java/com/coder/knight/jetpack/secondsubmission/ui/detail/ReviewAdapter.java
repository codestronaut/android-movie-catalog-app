package com.coder.knight.jetpack.secondsubmission.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.knight.jetpack.secondsubmission.R;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.ReviewEntity;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<ReviewEntity> reviewEntities = new ArrayList<>();

    void setReview(List<ReviewEntity> review) {
        if (review == null) return;
        this.reviewEntities.clear();
        this.reviewEntities.addAll(review);
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.onBind(reviewEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewEntities.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        ReviewEntity reviewEntity;
        private TextView tvAuthor, tvContent;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAuthor = itemView.findViewById(R.id.review_author_text);
            tvContent = itemView.findViewById(R.id.review_content_text);
        }

        void onBind(ReviewEntity reviewEntity) {
            this.reviewEntity = reviewEntity;
            tvAuthor.setText(reviewEntity.getAuthor());
            tvContent.setText(reviewEntity.getContent());
        }
    }
}
