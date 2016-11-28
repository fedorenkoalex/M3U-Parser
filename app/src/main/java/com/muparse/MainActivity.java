package com.muparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mPlaylistName;
    private TextView mPlaylistParams;
    private RecyclerView mPlaylistList;

    private PlaylistAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlaylistName = (TextView) findViewById(R.id.playlist_name);
        mPlaylistParams = (TextView) findViewById(R.id.playlist_params);
        mPlaylistList = (RecyclerView) findViewById(R.id.playlist_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mPlaylistList.setLayoutManager(layoutManager);


        mAdapter = new PlaylistAdapter(this);
        mPlaylistList.setAdapter(mAdapter);

        try {
            long start = getTime();
            InputStream is = getAssets().open("iptvs.m3u8");
            M3UParser parser = new M3UParser();
            M3UPlaylist playlist = parser.parseFile(is);
            long end = getTime();
            mPlaylistName.setText(playlist.getPlaylistName().concat(" (").concat("" + (end - start)).concat("ms)"));
            mPlaylistParams.setText(playlist.getPlaylistParams());
            mAdapter.update(playlist.getPlaylistItems());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long getTime() {
        Calendar c = Calendar.getInstance();
        return c.getTimeInMillis();
    }
}
