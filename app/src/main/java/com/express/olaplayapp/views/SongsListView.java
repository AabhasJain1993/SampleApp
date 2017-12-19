package com.express.olaplayapp.views;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.express.olaplayapp.R;
import com.express.olaplayapp.adapter.SongsListAdapter;
import com.express.olaplayapp.interfaces.ISongClickListener;
import com.express.olaplayapp.model.Song;
import com.express.olaplayapp.utils.ApiHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16/12/17.
 */

public class SongsListView extends RelativeLayout implements ISongClickListener, TextWatcher,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener{

    @Bind(R.id.searchbar)
    EditText searchBar;

    @Bind(R.id.songs_list)
    RecyclerView mSongListView;

    private Listener mListener = null;

    SongsListView mSongsListView;

    private SongsListAdapter mSongsListAdapter;
    private List<Song> mSongsList;

    public interface Listener {
        void onSongClicked(Song song);
    }


    public SongsListView(Context context) {
        this(context,null);
    }

    public SongsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    void initView(Context context) {
        inflate(context, R.layout.snippet_song_listview, this);
        ButterKnife.bind(this);

        mSongsListView = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSongListView.setLayoutManager(linearLayoutManager);

        setDataFromAPI();
        searchBar.addTextChangedListener(this);
    }

    void setDataFromAPI() {

        Call<List<Song>> call =  ApiHelper.getAPInterface().songList();

        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Response<List<Song>> response, Retrofit retrofit) {
                Log.d("Success : ", response.body() + "");
                mSongsList = response.body();

                mSongsListAdapter = new SongsListAdapter(mSongsListView, mSongsList);
                mSongListView.setAdapter(mSongsListAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Failure : ", "Failure");
            }
        });

    }

    public ArrayList<String> getAdapter() {
        ArrayList<String> songsList = new ArrayList<>();
        for(int i=0;i<mSongsList.size();i++) {
            songsList.add(mSongsList.get(i).getSongName());
        }
        return songsList;
    }

    @Override
    public void onSongClick(int position) {

        if(mListener!=null) {
            mListener.onSongClicked(mSongsList.get(position));
        }
//        Log.d("clicked","clicked");
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
//            mediaPlayer.setDataSource(mSongsList.get(position).getSongUrl());
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        mediaPlayer.prepareAsync();
//        mediaPlayer.setOnPreparedListener(this);
//        mediaPlayer.setOnErrorListener(this);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence query, int start, int before, int count) {
        List<Song> songsList = new ArrayList<>();
        for(int i=0;i<mSongsList.size();i++) {
            if(mSongsList.get(i).getSongName().toLowerCase().contains(query)) {
                songsList.add(mSongsList.get(i));
            }

        }
        mSongsListAdapter = new SongsListAdapter(mSongsListView, songsList);
        mSongListView.setAdapter(mSongsListAdapter);
        //mSongsListAdapter.notifyDataSetChanged();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
