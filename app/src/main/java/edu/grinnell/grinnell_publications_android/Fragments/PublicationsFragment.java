package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.R;

/**
 * {@link Fragment} to display users' subscribed publications.
 * @author Larry Boateng Asante
 */
public class PublicationsFragment extends Fragment implements UserInterface, SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    List<RealmPublication> mPublications = new ArrayList<>();
    LinearLayoutManager mLayoutManager;

    public PublicationsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        initializeUI();
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_publications, container, false);
    }

    private void configureViews() {
        
    }

    @Override
    public void initializeUI() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        final MenuItem item = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search by Title");
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });
    }

    private List<RealmPublication> filter(List<RealmPublication> publications, String query) {
        query = query.toLowerCase();
        final List<RealmPublication> filteredModelList = new ArrayList<>();
        for (RealmPublication publication : publications) {
            final String publicationName = publication.getPublicationName().toLowerCase();
            if (publicationName.contains(query)) {
                filteredModelList.add(publication);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<RealmPublication> publications = filter(mPublications, query);
        recyclerView.scrollToPosition(0);
        return true;
    }
}