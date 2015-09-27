package com.klaskagan.zoo.utils;

import com.klaskagan.zoo.models.Pin;
import retrofit.Callback;
import retrofit.http.GET;

import java.util.List;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/27.
 */
public interface PinsApiInterface {

    @GET("/pins.json")
    void getStreams(Callback<List<Pin>> callback);

}
