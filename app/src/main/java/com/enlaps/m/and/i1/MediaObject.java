package com.enlaps.m.and.i1;

import java.net.URL;

/**
 * Created by vsatish on 2/5/2016.
 */
public class MediaObject {

    public enum MEDIA_OBJECT_TYPE {
        MEDIA_OBJECT_TYPE_DEFAULT,
        MEDIA_OBJECT_TYPE_PHOTO,
        MEDIA_OBJECT_TYPE_VIDEO,
    };

    public MEDIA_OBJECT_TYPE mObjectType = MEDIA_OBJECT_TYPE.MEDIA_OBJECT_TYPE_DEFAULT;

    public String   url;
    public String   username;
    public String   user_profile_pircture;
    public String   caption;
    public int      like_count;
}
