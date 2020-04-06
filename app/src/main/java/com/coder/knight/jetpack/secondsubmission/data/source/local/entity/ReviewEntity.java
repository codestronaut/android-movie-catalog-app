package com.coder.knight.jetpack.secondsubmission.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewEntity implements Parcelable {
    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("content")
    @Expose
    private String content;

    public ReviewEntity(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.content);
    }

    private ReviewEntity(Parcel in) {
        this.author = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<ReviewEntity> CREATOR = new Parcelable.Creator<ReviewEntity>() {
        @Override
        public ReviewEntity createFromParcel(Parcel source) {
            return new ReviewEntity(source);
        }

        @Override
        public ReviewEntity[] newArray(int size) {
            return new ReviewEntity[size];
        }
    };
}
