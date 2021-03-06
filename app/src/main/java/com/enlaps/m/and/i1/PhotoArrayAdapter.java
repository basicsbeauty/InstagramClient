package com.enlaps.m.and.i1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vsatish on 2/7/2016.
 */
public class PhotoArrayAdapter extends ArrayAdapter<MediaObject> {


    public PhotoArrayAdapter( Context context, ArrayList<MediaObject> objects) {
        super( context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MediaObject object = getItem(position);

        if( convertView == null) {
            convertView = LayoutInflater.from( getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView tvCaption          = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView tvUsername         = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvLikeCount        = (TextView) convertView.findViewById(R.id.tvLikeCount);
        ImageView ivPhoto           = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ImageView ivProfilePhoto    = (ImageView) convertView.findViewById(R.id.ivPofilePicture);

        // Caption
            if( 0 < object.caption.length()) {
                tvCaption.setText(object.caption);
            }

        // Username
            tvUsername.setText( object.username);

        // User: Profile picture
            ivProfilePhoto.setImageResource(0);
            Picasso.with(getContext()).load(object.user_profile_pircture).into(ivProfilePhoto);

        // Like Count
            tvLikeCount.setText( Integer.toString(object.like_count));

        // Image
            ivPhoto.setImageResource(0);
            Picasso.with(getContext()).load(object.url).into(ivPhoto);

        return convertView;
    }
}
