package com.enlaps.m.and.i1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityHome extends AppCompatActivity {

    // Components
    @Bind(R.id.lvPhotos) ListView mLvPhotos;

    // Constants
    public static final String  CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";

    InstagramClient handler;
    PhotoArrayAdapter adapter;

    ArrayList<MediaObject> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        getPhotos();
        setAdapters();
    }

    protected void init() {

        ButterKnife.bind(this);

        // LV: Background color
        mLvPhotos.setBackgroundColor(Color.WHITE);

        photos = new ArrayList<>();
        adapter = new PhotoArrayAdapter( this, photos);
    }

    protected void setAdapters() {

        Log.d("Data Count 2", Integer.toString(photos.size()));

        mLvPhotos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    protected void getPhotos() {

        String url = "https://api.instagram.com/v1/media/popular?client_id=" +  CLIENT_ID;

        AsyncHttpClient client = new AsyncHttpClient();

        handler = new InstagramClient();

        client.get(url, null, handler);

        Log.d("Count", Integer.toString(handler.mediaObjects.size()));

        photos = handler.mediaObjects;

        Log.d("Data Count", Integer.toString(photos.size()));
        //adapter.notifyDataSetChanged();
    }
}
