package com.klaskagan.zoo.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
public class ZooMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    public static final double LATITUDE = 39.7500;
    public static final double LONGITUDE = -104.9500;

    public static ZooMapFragment getInstance() {
        return new ZooMapFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        CameraPosition position = CameraPosition.builder()
                .target(new LatLng(LATITUDE, LONGITUDE))
                .zoom(16f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setTrafficEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        MarkerOptions options = new MarkerOptions().position(new LatLng(LATITUDE, LONGITUDE));
        options.title(getString(R.string.zoo));
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(options);

        final RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getString(R.string.pins_feed)).build();
        PinsApiInterface pinsApiInterface = adapter.create(PinsApiInterface.class);

        pinsApiInterface.getStreams(new Callback<List<Pin>>() {
            @Override
            public void success(List<Pin> pins, Response response) {
                for (Pin pin : pins) {
                    MarkerOptions options = new MarkerOptions().position(new LatLng(Double.valueOf(pin.getLatitude()), Double.valueOf(pin.getLongitude())));
                    options.title(pin.getName());
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    googleMap.addMarker(options);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("ZooMapFragment", error.getMessage());
            }
        });
    }
}
