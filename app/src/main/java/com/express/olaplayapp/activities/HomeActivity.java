package com.express.olaplayapp.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.express.olaplayapp.R;
import com.express.olaplayapp.model.Song;
import com.express.olaplayapp.musicplayer.JcAudio;
import com.express.olaplayapp.musicplayer.JcPlayerView;
import com.express.olaplayapp.views.SongsListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16/12/17.
 */

public class HomeActivity extends AppCompatActivity implements SongsListView.Listener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,  JcPlayerView.JcPlayerViewServiceListener{

    @Bind(R.id.song_list_view)
    SongsListView mSongListView;

    @Bind(R.id.jcplayer)
    JcPlayerView jcPlayerView;

    private ArrayList<JcAudio> audioList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mSongListView.setListener(this);
        jcPlayerView.registerServiceListener(this);
     //   mSearchBar.addTextChangedListener(this);

    }

    @Override
    public void onSongClicked(Song song) {

        audioList.add(JcAudio.createFromURL(song.getSongName(), song.getSongUrl()));
        if(!jcPlayerView.isInitialized()) {
            jcPlayerView.initPlaylist(audioList);
        }

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onPreparedAudio(String audioName, int duration) {

    }

    @Override
    public void onCompletedAudio() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onContinueAudio() {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onTimeChanged(long currentTime) {

    }

    @Override
    public void updateTitle(String title) {

    }
}
