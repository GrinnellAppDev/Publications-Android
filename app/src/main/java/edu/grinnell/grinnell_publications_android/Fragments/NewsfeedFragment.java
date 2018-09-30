package edu.grinnell.grinnell_publications_android.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

    private List<JsonArticle> articles = new ArrayList<>();
    public NewsfeedFragment() {
    }


    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View newsfeedFragment = inflater.inflate(R.layout.fragment_newsfeed, container, false);

        initializeUI(newsfeedFragment);

        return newsfeedFragment;
    }

    @Override
    public void initializeUI(View view) {
//        RemoteClientAPI remoteClient = new PublicationsRemoteClient(getActivity());
        ListView listView = view.findViewById(R.id.newsfeed_listview);
        final PublicationsRemoteClient remoteClient = new PublicationsRemoteClient(getActivity());
        final NewsfeedAdapter newsfeedAdapter = new NewsfeedAdapter(getContext(), new ArrayList<JsonArticle>());
        listView.setAdapter(newsfeedAdapter);
        if (!haveLocalArticles(newsfeedAdapter)) {
            updateListView(remoteClient, newsfeedAdapter);
        }
        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swiperefresh);
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
                JsonArticle jsonArticle = new JsonArticle(article.getArticleID(),
                        article.getPublication(),
                        article.getArticleTitle(),
                        article.getDatePublished(),
                        getJsonAuthors(article.getRealmAuthors()),
                        article.getReadTimeMinutes(),
                        article.getHeaderImage());
                newsfeedAdapter.mArticles.add(jsonArticle);
            }
            return true;
        }
    }

    private List<JsonAuthor> getJsonAuthors(RealmList<RealmAuthor> authors) {
        List<JsonAuthor> jsonAuthors = new ArrayList<>();
        for (RealmAuthor realmAuthor : authors) {
            JsonAuthor jsonAuthor = new JsonAuthor(realmAuthor.getFullName(), realmAuthor.getAuthorContactInfo().getEmail());
            jsonAuthors.add(jsonAuthor);
        }
        return jsonAuthors;
    }

    private boolean ready = false;

    public void updateListView(final PublicationsRemoteClient remoteClient, final NewsfeedAdapter newsfeedAdapter) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading");
        progressDialog.show();
        remoteClient.getAllPublications();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!remoteClient.done) {
                }
                newsfeedAdapter.mArticles = remoteClient.getArticles();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsfeedAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
        progressDialog.dismiss();
    }

    public List<RealmStory> createStories() {
        ArrayList<RealmStory> ret = new ArrayList<>();
        RealmList authors = new RealmList<RealmAuthor>();
        authors.add(new RealmAuthor("This is a full name",
                new RealmAuthorContact("ampimdar@grinnell.edu", "641 260 0384", "other"),
                new RealmList<RealmPublication>(),
                new RealmList<RealmStory>()));
        for (int i = 0; i < 10; i++) {
            RealmStory story = new RealmStory(System.currentTimeMillis() - (long)20*i + "",
                    "This is brief " + i,
                    "",
                    "",
                    System.currentTimeMillis() - (long)40*i + "",
                    "",
                    "",
                    "This is the content",
                    authors);
            ret.add(story);
        }
        return ret;
    }



}
