package com.klaskagan.zoo.utils;

import com.klaskagan.zoo.models.GalleryImage;
import retrofit.Callback;
import retrofit.http.GET;

import java.util.List;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/26.
 */
public interface GalleryApiInterface {

    @GET("/gallery.json")
    void getStreams(Callback<List<GalleryImage>> callback);

}
