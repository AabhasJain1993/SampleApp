package com.express.olaplayapp.api;

import com.express.olaplayapp.model.Song;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.Url;

/**
 * Created by root on 30/11/17.
 */

public interface SongsAPI {

    @GET("/studio")
    Call<List<Song>> songList();
}
