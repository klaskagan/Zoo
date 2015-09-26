package com.klaskagan.zoo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.klaskagan.zoo.R;
import com.klaskagan.zoo.adapters.GalleryImageAdapter;
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
public class GalleryFragment extends Fragment {

    private GridView mGridView;
    private GalleryImageAdapter mAdapter;

    public static GalleryFragment getInstance() {
        GalleryFragment instance = new GalleryFragment();
        return instance;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new GalleryImageAdapter(getActivity(), 0);
        mGridView.setAdapter(mAdapter);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getString(R.string.gallery_feed)).build();
        GalleryApiInterface galleryApiInterface = restAdapter.create(GalleryApiInterface.class);
        galleryApiInterface.getStreams(new Callback<List<GalleryImage>>() {
            @Override
            public void success(List<GalleryImage> galleryImages, Response response) {
                if (galleryImages == null || galleryImages.isEmpty()) {
                    return;
                }

                mAdapter.addAll(galleryImages);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.w("GalleryFragment", error.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridView = (GridView) view.findViewById(R.id.grid);
    }
}
