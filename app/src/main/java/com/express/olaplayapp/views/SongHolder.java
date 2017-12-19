package com.express.olaplayapp.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.express.olaplayapp.R;
import com.express.olaplayapp.interfaces.ISongClickListener;

/**
 * Created by root on 16/12/17.
 */

public class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView mSongName, mArtistsName;
    ImageView mImageView;
    Context mContext;
    ISongClickListener mSongClickListener = null;

    public SongHolder(View view, ISongClickListener iSongClickListener) {
        super(view);
        mSongName = (TextView) view.findViewById(R.id.song_name);
        mArtistsName = (TextView)view.findViewById(R.id.composer);
        mImageView =(ImageView)view.findViewById(R.id.songcover_img);
        mContext = view.getContext();
        itemView.setOnClickListener(this);
        mSongClickListener = iSongClickListener;
    }

    public void bind(String name, String artistName, String imageUrl) {
        mSongName.setText(name);
        artistName.replaceAll(",","\r\n");
        mArtistsName.setText(artistName);
        Glide.
                with(mContext).
                load(imageUrl).
                centerCrop().
                into(mImageView);
    }

    @Override
    public void onClick(View v) {
        Log.d("clicked","clicked");
        int position = getAdapterPosition();
        if(mSongClickListener !=null)
            mSongClickListener.onSongClick(position);
        else
            Toast.makeText(mContext, "Null click Listener", Toast.LENGTH_LONG).show();
    }
}
