package com.klaskagan.zoo.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import com.klaskagan.zoo.R;
import com.klaskagan.zoo.adapters.ExhibitsAdapter;
import com.klaskagan.zoo.models.Animal;
import com.klaskagan.zoo.utils.AnimalApiInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public class ExhibitsListFragment extends ListFragment {

    private ExhibitsAdapter mAdapter;
    public static final String NAME = "Exhibits";

    public static ExhibitsListFragment getInstance() {
        ExhibitsListFragment instance = new ExhibitsListFragment();
        return instance;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListShown(false);
        mAdapter = new ExhibitsAdapter(getActivity(), 0);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getString(R.string.exhibits_feed)).build();

        AnimalApiInterface animalApiInterface = restAdapter.create(AnimalApiInterface.class);
        animalApiInterface.getStreams(new Callback<List<Animal>>() {
            @Override
            public void success(List<Animal> animals, Response response) {
                if (animals == null || animals.isEmpty()) {
                    return;
                }
                mAdapter.addAll(animals);
                mAdapter.notifyDataSetChanged();
                setListAdapter(mAdapter);
                setListShown(true);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Zoo", "Retrofit error" + error.getMessage());
            }
        });
    }
}
