package edu.grinnell.grinnell_publications_android.Fragments;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;



import java.util.Date;

/**
 * TODO: Fix getDrawable deprication
 *
 */

public class ExpandedArticleFragment extends Fragment implements UserInterface {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.backdrop) ImageView imageView;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.invite) FloatingActionButton inviteButton;
    @Bind(R.id.appbar) AppBarLayout appbar;


    ExpandedArticleFragment context = this;


    public ExpandedArticleFragment() {}

    public static ExpandedArticleFragment newInstance(){
        ExpandedArticleFragment fragment = new ExpandedArticleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View  expandedArticleFragment = inflater.inflate(R.layout.fragment_extended_article, container, false);
        ButterKnife.bind(this, expandedArticleFragment);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_search)); //todo: Replace with backbutton
        initializeUI();
        return expandedArticleFragment;
    }

    @Override
    public void initializeUI(){
        beginWithToolbarCollapsed(false);
        loadData();
        setOnClickListeners();
    }

    //if true, begins the fragment with the toolbar collapsed
    private void beginWithToolbarCollapsed(Boolean bool){
        appbar.setExpanded(!bool);
    }



    public void setOnClickListeners() {
        //onClick for back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FAB click", Snackbar.LENGTH_SHORT);
            }
        });

    }

    /**
     * loads the data from article
     */
    private void loadData(){
        //method for pulling data
    }


    /**
     * Sets
     */
    private void setContent(String name, Date startDate, Date endDate, String imageURL, String description, String location){

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels/4;
        int height = dm.heightPixels/10;

        collapsingToolbar.setTitle(name);
        Picasso
                .with(context.getContext())
                .load(imageURL)
                .resize(width, height)
                .centerCrop()
                .into(imageView);
    }


}
