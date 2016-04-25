package edu.grinnell.grinnell_publications_android.Views.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.grinnell.grinnell_publications_android.Models.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

/**
 * A Fragment to display users' subscribed publications.
 */
public class Publications extends Fragment implements UserInterface{


    public Publications() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Build user interface
        initializeUI();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publications, container, false);
    }

    @Override
    public void initializeUI() {

    }
}
