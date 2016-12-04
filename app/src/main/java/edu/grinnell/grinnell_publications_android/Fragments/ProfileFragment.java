package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.grinnell.grinnell_publications_android.Adapters.ArticleAdapter;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

/**
 * {@link Fragment} to show a user's Profile
 * @author Larry Boateng Asante
 */
public class ProfileFragment extends Fragment implements UserInterface {

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initializeUI(view);
        return view;
    }

    @Override
    public void initializeUI(View view) {
        initRecyclerView((RecyclerView) view.findViewById(R.id.articles_recyclerview));
    }

    private void initRecyclerView(RecyclerView rv) {
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        rv.setAdapter(new ArticleAdapter());
    }
}
