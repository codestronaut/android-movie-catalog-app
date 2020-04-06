package com.coder.knight.jetpack.secondsubmission.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreEntity implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    public GenreEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    private GenreEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<GenreEntity> CREATOR = new Parcelable.Creator<GenreEntity>() {
        @Override
        public GenreEntity createFromParcel(Parcel source) {
            return new GenreEntity(source);
        }

        @Override
        public GenreEntity[] newArray(int size) {
            return new GenreEntity[size];
        }
    };
}
