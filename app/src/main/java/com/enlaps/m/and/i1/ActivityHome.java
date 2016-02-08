package com.enlaps.m.and.i1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ActivityHome extends AppCompatActivity {

    // Components
    ListView mLvPhotos;

    // Constants
    public static final String  CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";

    private InstagramClient handler;
    private PhotoArrayAdapter adapter;

    private ArrayList<MediaObject> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        //
            ButterKnife.bind(this);

            mLvPhotos = (ListView) findViewById(R.id.lvPhotos);

            // LV: Background color
            mLvPhotos.setBackgroundColor(Color.WHITE);

            photos = new ArrayList<>();
            adapter = new PhotoArrayAdapter( this, photos);

        //
            mLvPhotos.setAdapter(adapter);
            //adapter.notifyDataSetChanged();

        getPhotos();

        //photos = handler.mediaObjects;
        Log.d("Data Count", Integer.toString(photos.size()));

        setAdapters();
    }

    protected void init() {

    }

    protected void setAdapters() {

        Log.d("Data Count 2", Integer.toString(photos.size()));

    }

    protected void getPhotos() {

        String url = "https://api.instagram.com/v1/media/popular?client_id=" +  CLIENT_ID;

        AsyncHttpClient client = new AsyncHttpClient();

        handler = new InstagramClient();

        //client.get(url, null, handler);
        client.get( url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

                for (int i = 0; i < photosJSON.length(); i++) {
                    try {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);

                        MediaObject object = handler.json2MediaObject(photoJSON);

                        Log.d( "Object", object.toString());

                        photos.add(object);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        Log.d("Count", Integer.toString(handler.mediaObjects.size()));

        //photos = handler.mediaObjects;

        Log.d("Data Count", Integer.toString(photos.size()));
        //adapter.notifyDataSetChanged();
    }
}
