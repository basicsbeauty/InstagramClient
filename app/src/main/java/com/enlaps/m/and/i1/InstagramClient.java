package com.enlaps.m.and.i1;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by vsatish on 2/6/2016.
 */
public class InstagramClient extends JsonHttpResponseHandler {

    // Schema
        public static final String JSON_TAG_DATA = "data";

        // URL
            public static final String JSON_TAG_URL = "url";

        // Type - { "image"}
            public static final String JSON_TAG_TYPE = "type";

        // Username
            public static final String JSON_TAG_USER = "user";
            public static final String JSON_TAG_USERNAME = "username";

        // Image URL
            public static final String JSON_TAG_IMAGES = "images";
            public static final String JSON_TAG_IMAGE_STD = "standard_resolution";

        // Likes: Count
            public static final String JSON_TAG_LIKES = "likes";
            public static final String JSON_TAG_COUNT = "count";

        // Caption
            public static final String JSON_TAG_CAPTION = "caption";
            public static final String JSON_TAG_CAPTION_TEXT = "text";

    public ArrayList<MediaObject> mediaObjects;

    public InstagramClient() {
        mediaObjects = new ArrayList<MediaObject>();
    }

    protected MediaObject json2MediaObject(JSONObject object) {

        MediaObject rval = new MediaObject();

        try {
            // Username
                rval.username = object.getJSONObject(JSON_TAG_USER).getString(JSON_TAG_USERNAME);

            // Like Count
                rval.like_count = object.getJSONObject(JSON_TAG_LIKES).getInt(JSON_TAG_COUNT);

            // Image: URL
                rval.url = object.getJSONObject(JSON_TAG_IMAGES).getJSONObject(JSON_TAG_IMAGE_STD).getString(JSON_TAG_URL);

            // Caption:
                JSONObject temp = object.optJSONObject(JSON_TAG_CAPTION);

                if( temp != null) {
                    rval.caption = object.getJSONObject(JSON_TAG_CAPTION).getString(JSON_TAG_CAPTION_TEXT);
                }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return rval;
    }

    protected void jsonArray2MediaObjects(JSONArray jarray) {

        int index;
        int count;

        count = jarray.length();
        Log.d( "JSON Count", Integer.toString(count));
        for( index = 0; index<count; index++) {

            try {
                mediaObjects.add(json2MediaObject(jarray.getJSONObject(index)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d( "Object Count", Integer.toString(mediaObjects.size()));
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

        Log.d("Response Handler", response.toString());

        // Response -> data
            JSONArray jarrayPhoto = new JSONArray();
            try {
                jarrayPhoto = response.getJSONArray(JSON_TAG_DATA);
                jsonArray2MediaObjects(jarrayPhoto);
            }
            catch ( JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        //super.onFailure(statusCode, headers, throwable, errorResponse);
        Log.e("Error", errorResponse.toString());
    }

}