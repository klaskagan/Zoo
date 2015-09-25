package com.klaskagan.zoo.fragments;

import android.support.v4.app.ListFragment;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public class ExhibitsListFragment extends ListFragment {

    public static final String NAME = "Exhibits";

    public static ExhibitsListFragment getInstance() {
        ExhibitsListFragment instance = new ExhibitsListFragment();
        return instance;
    }

}
