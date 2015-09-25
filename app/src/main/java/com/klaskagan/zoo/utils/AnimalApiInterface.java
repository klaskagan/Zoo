package com.klaskagan.zoo.utils;

import com.klaskagan.zoo.models.Animal;
import retrofit.Callback;
import retrofit.http.GET;

import java.util.List;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public interface AnimalApiInterface {
    @GET("/Exhibits.json")
    void getStreams(Callback<List<Animal>> callback);
}
