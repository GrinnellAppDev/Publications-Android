package edu.grinnell.grinnell_publications_android.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import edu.grinnell.grinnell_publications_android.Adapters.ArticleAdapter;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthor;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;
import io.realm.RealmList;

/**
 * {@link Fragment} to show a user's Profile
 * @author Larry Boateng Asante
 */
public class ProfileFragment extends Fragment implements UserInterface {
    private RecyclerView mRecycler;
    private ImageView mHeaderImage;
    private CollapsingToolbarLayout mCollapsingToolbar;


    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.articles_recyclerview);
        mHeaderImage = (ImageView) view.findViewById(R.id.header_image);
        mCollapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        initializeUI();
        return view;
    }

    @Override
    public void initializeUI() {
        loadPlaceHolderData();

        initRecyclerView(mRecycler);
    }

    private void loadPlaceHolderData(){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.grinnell_gates);
        Drawable defaultImage = new BitmapDrawable(getResources(), image);
        setHeaderText("Sample title");
        setHeaderImage(defaultImage);
    }

    private void initRecyclerView(RecyclerView rv) {
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        rv.setAdapter(new ArticleAdapter(retrieveStories(), getContext()));
    }

    private List<RealmStory> retrieveStories() {
        // TODO: implement with network
        // for now, dummy data
        RealmPublication snb = new RealmPublication("S&B", 0, null, null, null);
        RealmList<RealmPublication> pubs = new RealmList<>();
        pubs.add(snb);
        RealmAuthor richard = new RealmAuthor("Rick and Morty", null, pubs, null);
        RealmList<RealmAuthor> auts = new RealmList<>();
        auts.add(richard);

        List<RealmStory> stories = new ArrayList<>();
        stories.add(new RealmStory(snb, "December 4, 2016", "December 4, 2016", auts, null,
                                   "people performed multiple songs!", "Songs were performed",
                                   "There Was A Performance and It was Great",
                                   "https://placeholdit.imgix.net/~text?txtsize=68&txt=720%C3%97500&w=720&h=500"));

        return stories;
    }

    public Drawable getHeaderImage() { return this.mHeaderImage.getDrawable(); }

    private void setHeaderImage(Drawable image) {
        this.mHeaderImage.setImageDrawable(image);
    }

    private void setHeaderText(String titleText) { this.mCollapsingToolbar.setTitle(titleText);}

}
