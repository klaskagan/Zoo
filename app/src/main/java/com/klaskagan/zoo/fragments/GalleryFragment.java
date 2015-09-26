package com.klaskagan.zoo.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import com.klaskagan.zoo.R;
import com.klaskagan.zoo.models.GalleryImage;
import com.klaskagan.zoo.utils.GalleryApiInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public class GalleryFragment extends ListFragment {

    public static GalleryFragment getInstance() {
        GalleryFragment instance = new GalleryFragment();
        return instance;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getString(R.string.gallery_feed)).build();
        GalleryApiInterface galleryApiInterface = restAdapter.create(GalleryApiInterface.class);
        galleryApiInterface.getStreams(new Callback<List<GalleryImage>>() {
            @Override
            public void success(List<GalleryImage> galleryImages, Response response) {
                if (galleryImages == null || galleryImages.isEmpty()) {
                    return;
                }

                for (GalleryImage image : galleryImages) {
                    Log.i("GalleryFragment", image.getThumbnail());
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.w("GalleryFragment", error.getMessage());
            }
        });
    }
}
