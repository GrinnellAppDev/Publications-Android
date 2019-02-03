package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Adapters.BookmarksAdapter;
import edu.grinnell.grinnell_publications_android.Adapters.NewsfeedAdapter;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmArticle;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthor;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Implementation.PublicationsRemoteClient;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonArticle;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAuthor;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * {@link Fragment} to show all Bookmarks
 * @author Larry Boateng Asante
 */
public class BookmarksFragment extends Fragment implements UserInterface {

    private Realm realm;
    private BookmarksAdapter bookmarksAdapter;
    public BookmarksFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        View bookmarkFragment = inflater.inflate(R.layout.fragment_bookmark, container, false);
        initializeUI(bookmarkFragment);
        return bookmarkFragment;
    }

    @Override
    public void initializeUI(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.bookmarks_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookmarksAdapter = new BookmarksAdapter(getActivity(), new ArrayList<RealmStory>());
        if (!haveBookmarkedArticles(bookmarksAdapter)) {
            Toast.makeText(getContext(), "No bookmarked articles", Toast.LENGTH_SHORT).show();
        }
        recyclerView.setAdapter(bookmarksAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        haveBookmarkedArticles(bookmarksAdapter);
        bookmarksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        bookmarksAdapter.mArticles.clear();
        bookmarksAdapter.notifyDataSetChanged();
    }

    private boolean haveBookmarkedArticles(BookmarksAdapter bookmarksAdapter) {
        realm = Realm.getDefaultInstance();
        final RealmResults<RealmStory> articles = realm.where(RealmStory.class).findAll();
        if (articles.size() == 0) {
            return false;
        } else {
            bookmarksAdapter.mArticles.addAll(articles);
            bookmarksAdapter.notifyDataSetChanged();
            return true;
        }
    }
}
