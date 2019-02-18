package com.example.nas19_friendsr;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Friend implements Serializable, Parcelable {
    private String name, bio;
    private int drawableId;
    private float rating;

    public Friend(String name, String bio, int drawableId) {
        this.name = name;
        this.bio = bio;
        this.drawableId = drawableId;
    }

    protected Friend(Parcel in) {
        name = in.readString();
        bio = in.readString();
        drawableId = in.readInt();
        rating = in.readFloat();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public void setName(String name) { this.name = name; }

    public void setBio(String bio) { this.bio = bio; }

    public int getDrawableId() {
        return drawableId;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(bio);
        dest.writeInt(drawableId);
        dest.writeFloat(rating);
    }
}
