package edu.grinnell.grinnell_publications_android.Fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Adapters.NewsfeedAdapter;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmArticle;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthor;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthorContact;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Implementation.PublicationsRemoteClient;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonArticle;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAuthor;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * {@link Fragment} to display all news in user's Newsfeed.
 * @author Larry Boateng Asante
 */
public class NewsfeedFragment extends Fragment implements UserInterface {

    public NewsfeedFragment() {
    }

    private Realm realm;
    private ProgressDialog progressDialog;
    private boolean done;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View newsfeedFragment = inflater.inflate(R.layout.fragment_newsfeed, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading");
        progressDialog.show();
        initializeUI(newsfeedFragment);
        done = false;
        return newsfeedFragment;
    }

    @Override
    public void initializeUI(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.newsfeed_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final PublicationsRemoteClient remoteClient = new PublicationsRemoteClient(getActivity());
        final NewsfeedAdapter newsfeedAdapter = new NewsfeedAdapter(getActivity(), new ArrayList<JsonArticle>());
        if (!haveLocalArticles(newsfeedAdapter)) {
            updateListView(remoteClient, newsfeedAdapter);
        }
        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swiperefresh);
        recyclerView.setAdapter(newsfeedAdapter);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateListView(remoteClient, newsfeedAdapter);
                pullToRefresh.setRefreshing(false);
            }
        });
    }

    private boolean haveLocalArticles(NewsfeedAdapter newsfeedAdapter) {
        realm = Realm.getDefaultInstance();
        final RealmResults<RealmArticle> articles = realm.where(RealmArticle.class).findAll();
        if (articles.size() == 0) {
            return false;
        } else {
            for (RealmArticle article : articles) {
                JsonArticle jsonArticle = new JsonArticle(article);
                newsfeedAdapter.mArticles.add(jsonArticle);
            }
            progressDialog.dismiss();
            return true;
        }
    }

    public void updateListView(final PublicationsRemoteClient remoteClient, final NewsfeedAdapter newsfeedAdapter) {
        remoteClient.getAllPublications();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!remoteClient.done) {
                }
                newsfeedAdapter.mArticles = remoteClient.getArticles();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsfeedAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}
