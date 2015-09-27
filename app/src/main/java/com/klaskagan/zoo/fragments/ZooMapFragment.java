package com.klaskagan.zoo.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.maps.SupportMapFragment;
import com.klaskagan.zoo.R;
import com.klaskagan.zoo.models.Pin;
import com.klaskagan.zoo.utils.PinsApiInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public class ZooMapFragment extends SupportMapFragment {

    public static ZooMapFragment getInstance() {
        return new ZooMapFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getString(R.string.pins_feed)).build();
        PinsApiInterface pinsApiInterface = adapter.create(PinsApiInterface.class);

        pinsApiInterface.getStreams(new Callback<List<Pin>>() {
            @Override
            public void success(List<Pin> pins, Response response) {
                for (Pin pin : pins) {
                    Log.e("Zoo", pin.getName());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("ZooMapFragment", error.getMessage());
            }
        });

    }
}
