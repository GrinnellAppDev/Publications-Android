package edu.grinnell.grinnell_publications_android.Views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.grinnell.grinnell_publications_android.R;

/**
 * A Fragment to display users' subscribed publications.
 */
public class MyPublications extends Fragment {


    public MyPublications() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_publications, container, false);
    }

}
