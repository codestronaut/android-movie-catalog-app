package com.coder.knight.jetpack.secondsubmission.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrailerEntity implements Parcelable {
    @SerializedName("key")
    @Expose
    private String key;

    public TrailerEntity(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
    }

    private TrailerEntity(Parcel in) {
        this.key = in.readString();
    }

    public static final Parcelable.Creator<TrailerEntity> CREATOR = new Parcelable.Creator<TrailerEntity>() {
        @Override
        public TrailerEntity createFromParcel(Parcel source) {
            return new TrailerEntity(source);
        }

        @Override
        public TrailerEntity[] newArray(int size) {
            return new TrailerEntity[size];
        }
    };
}
