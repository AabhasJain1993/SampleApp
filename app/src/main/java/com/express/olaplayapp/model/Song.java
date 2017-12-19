package com.express.olaplayapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 16/12/17.
 */

public class Song {

    @SerializedName("song")
    String mSongName;

    @SerializedName("url")
    String mSongUrl;

    @SerializedName("artists")
    String mArtists;

    @SerializedName("cover_image")
    String mCoverImage;



    public String getSongName() {
        return mSongName;
    }

    public String getSongUrl() {
        return mSongUrl;
    }

    public String getArtists() {
        return mArtists;
    }

    public String getCoverImageUrl() {
        return mCoverImage;
    }

}
