package com.express.olaplayapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.express.olaplayapp.R;
import com.express.olaplayapp.interfaces.ISongClickListener;
import com.express.olaplayapp.model.Song;
import com.express.olaplayapp.views.SongHolder;

import java.util.List;

/**
 * Created by root on 16/12/17.
 */

public class SongsListAdapter  extends RecyclerView.Adapter<SongHolder>  {

    public static final String TAG= "SONG LIST ADAPTER";
    private List<Song> mSongsList;
    private ISongClickListener mSongClickListener = null;

    public SongsListAdapter(ISongClickListener iSongClickListener, List<Song> list) {
        mSongClickListener = iSongClickListener;
        mSongsList = list;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.snippet_song_card, parent, false);
        return new SongHolder(itemView, mSongClickListener);
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        Song item = mSongsList.get(position);
        holder.bind(item.getSongName(), item.getArtists(), item.getCoverImageUrl());
    }

    @Override
    public int getItemCount() {
        return mSongsList.size();
    }

}
