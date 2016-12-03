package edu.grinnell.grinnell_publications_android.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.R;

/**
 * {@link Fragment} to display users' subscribed publications.
 * @author Larry Boateng Asante
 */
public class PublicationsFragment extends Fragment
        implements UserInterface, SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private ListView mListView;
    private List<RealmPublication> mPublications;
    private LinearLayoutManager mLayoutManager;
    private ArrayAdapter<String> mTestAdapter;

    public PublicationsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        initializeUI();
        setHasOptionsMenu(true);

        final View view = inflater.inflate(R.layout.fragment_publications, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.publications_rv);
        mListView = (ListView) view.findViewById(R.id.publications_lv);
        configureViews();

        return view;
    }

    private void configureViews() {
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPublications = new ArrayList<>();
        populateDummyData();

        mTestAdapter = createTestAdapter();
        mListView.setAdapter(mTestAdapter);
        //mRecyclerView.setAdapter(mTestAdapter);
    }

    private ArrayAdapter<String> createTestAdapter() {
        List<String> publicationNames = new ArrayList<>();
        for (RealmPublication p : mPublications) {
            publicationNames.add(p.getPublicationName());
        }
        return new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                publicationNames);
    }

    public void populateDummyData() {
        // create dummy values to test search filter
        RealmPublication larry = new RealmPublication();
        larry.setPublicationName("Larry");
        mPublications.add(larry);
        RealmPublication matt = new RealmPublication();
        matt.setPublicationName("Matt");
        mPublications.add(matt);
        RealmPublication mattori = new RealmPublication();
        mattori.setPublicationName("Mattori");
        mPublications.add(mattori);
        RealmPublication yazan = new RealmPublication();
        yazan.setPublicationName("Yazan");
        mPublications.add(yazan);
    }

    @Override
    public void initializeUI() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

        final MenuItem item = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search by Title");
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
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
        if (query.length() <= 0) {
            mListView.setAdapter(mTestAdapter);
        }
        final List<RealmPublication> publications = filter(mPublications, query);

        //mRecyclerView.scrollToPosition(0);
        mListView.smoothScrollToPosition(0);
        return true;
    }
}