package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

/**
 * {@link Fragment} to display all news in user's Newsfeed.
 * @author Larry Boateng Asante
 */
public class NewsfeedFragment extends Fragment implements UserInterface {

    public NewsfeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View  newsfeedFragment = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        initializeUI(newsfeedFragment);
        return newsfeedFragment;
    }

    @Override
    public void initializeUI(View view) {
    }


}
