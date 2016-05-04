package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.grinnell.grinnell_publications_android.Models.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

/**
 * A Fragment to display all news in user's Newsfeed.
 */
public class NewsfeedFragment extends Fragment implements UserInterface {

    public NewsfeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Build user interface
        initializeUI();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newfeed, container, false);
    }

    @Override
    public void initializeUI() {

    }
}
