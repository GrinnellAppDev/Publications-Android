package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

/**
 * {@link Fragment} to show all Bookmarks
 * @author Larry Boateng Asante
 */
public class BookmarksFragment extends Fragment implements UserInterface {

    public BookmarksFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        initializeUI(view);
        return view;
    }

    @Override
    public void initializeUI(View view) {

    }
}
