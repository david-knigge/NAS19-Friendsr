package com.example.nas19_friendsr;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/*
 * Stores friend details. Implements Serializable to pass between intents, and Parcelable to
 * store in Bundle.
 */
public class Friend implements Serializable, Parcelable {
    private String name, bio;
    private int drawableId, likes;
    private float rating;

    /*
     * Create a new friend using name, bio and id of photo as input.
     */
    public Friend(String name, String bio, int drawableId) {
        this.name = name;
        this.bio = bio;
        this.drawableId = drawableId;
    }

    /*
     * Restore instance from parcel.
     */
    protected Friend(Parcel in) {
        name = in.readString();
        bio = in.readString();
        drawableId = in.readInt();
        rating = in.readFloat();
        likes = in.readInt();
    }

    /*
     * Create friend parcel.
     */
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

    public void setBio(String bio) { this.bio = bio; }

    /*
     * If likes is not set for this friend, instantiate with one like, otherwise increment number
     * of likes.
     */
    public void addLike() {
        if (0 != likes) {
            likes += 1;
        } else {
            likes = 1;
        }
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public int getDrawableId() {
        return drawableId;
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
        dest.writeInt(likes);
    }
}
