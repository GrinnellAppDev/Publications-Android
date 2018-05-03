package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Adapters.NewsfeedAdapter;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Implementation.PublicationsNetworkClient;
import edu.grinnell.grinnell_publications_android.Services.Implementation.RealmLocalClient;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.NetworkClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.OnNetworkCallCompleteListener;

/**
 * {@link Fragment} to display all news in user's Newsfeed.
 * @author Prabir Pradhan
 */
public class NewsFeedFragment extends Fragment implements UserInterface {

    List<Story> stories;
    ListView listView;
    NewsfeedAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    NetworkClientAPI networkClient;
    LocalClientAPI localClient;

    OnNetworkCallCompleteListener networkcallback = new OnNetworkCallCompleteListener() {
        @Override
        public void onNetworkCallSucceeded() {
            Toast.makeText(getContext(), "Network call succeeded.", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
            loadStories();
        }

        @Override
        public void onNetworkCallFailed(String message) {
            Toast.makeText(getContext(), "Network call failed.", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);

        }
    };

    public NewsFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        localClient = new RealmLocalClient();
        networkClient = new PublicationsNetworkClient(localClient, networkcallback);
        stories = new ArrayList<>();
        final View  newsFeedFragment = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        initializeUI(newsFeedFragment);
        loadStories();

        return newsFeedFragment;
    }


    @Override
    public void initializeUI(View view) {
        listView = view.findViewById(R.id.newsfeed_listview);
        adapter = new NewsfeedAdapter(getContext(), stories);
        listView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    /**
     * Load stories from the local client to be displayed.
     */
    private void loadStories() {
        stories = localClient.getAllStories();
        adapter.updateStories(stories);
    }

    private void refreshContent() {
        Toast.makeText(getContext(), "Refreshing...", Toast.LENGTH_SHORT).show();

        // TODO: 4/29/18 Loop though all publiations
        networkClient.getAllStoriesInPublication("s-and-b");
        //networkClient.getAllPublications();
    }

}
