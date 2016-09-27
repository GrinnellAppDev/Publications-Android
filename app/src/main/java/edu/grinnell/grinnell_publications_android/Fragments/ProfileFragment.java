package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

/**
 * A Fragment to show a user's Profile
 */
public class ProfileFragment extends Fragment implements UserInterface{


    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initializeUI();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void initializeUI() {

    }
}
