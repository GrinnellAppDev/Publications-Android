package edu.grinnell.grinnell_publications_android.Fragments;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import edu.grinnell.grinnell_publications_android.Activities.MainActivity;
import edu.grinnell.grinnell_publications_android.R;



import java.util.Date;

/**
 * TODO: Fix getDrawable deprication
 *
 */

public class ArticleFragment extends Fragment {

    CollapsingToolbarLayout collapsingToolbar;
    Toolbar toolbar;

    private ImageView imageView;
    private FloatingActionButton inviteButton;
    private AppBarLayout appbar;



    ArticleFragment context = this;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleFragment() {
    }

    /**
     * Constructor created by the newInstance and takes in the Item ID
     */
    public static ArticleFragment newInstance(){
        ArticleFragment fragment = new ArticleFragment();
        return fragment;
    }
    /*
        public static DisplayFragment newInstance(Article article){
        DisplayFragment fragment = new DisplayFragment();
        return fragment;
    }
     */

    /**
     * OnCreate for the fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * OnCreateView that inflates and sets up the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View DisplayFragmentView = inflater.inflate(R.layout.fragment_article,
                container, false);

        /* Setup view */
        setViewItems(DisplayFragmentView);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_search)); //todo: Replace with backbutton
        appbar.setExpanded(false);
        loadData();

        /* logic setters */
        setOnClickListeners();

        return DisplayFragmentView;

    }



    /**
     * SETUP METHOD:
     * Takes the fragment view, sets each view item
     */
    private void setViewItems(View mView)
    {

        imageView = (ImageView) mView.findViewById(R.id.backdrop);
        toolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        collapsingToolbar =
                (CollapsingToolbarLayout) mView.findViewById(R.id.collapsing_toolbar);
        inviteButton = (FloatingActionButton) mView.findViewById(R.id.invite);
        appbar = (AppBarLayout) mView.findViewById(R.id.appbar);

    }



    /**
     * SETUP METHOD:
     * Sets the onclick listeners for each view item
     */
    public void setOnClickListeners() {

        //onClick for back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).openDrawer();
            }
        });

        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick for the FAB
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
