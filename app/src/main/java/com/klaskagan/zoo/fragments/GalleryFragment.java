package com.klaskagan.zoo.fragments;

import android.support.v4.app.ListFragment;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public class GalleryFragment extends ListFragment {

    public static GalleryFragment getInstance() {
        GalleryFragment instance = new GalleryFragment();
        return instance;
    }
}
