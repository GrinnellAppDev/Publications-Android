package edu.grinnell.grinnell_publications_android.Fragments;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import edu.grinnell.grinnell_publications_android.R;



import java.util.Date;

public class ArticleFragment extends Fragment {

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView imageView;
    private FloatingActionButton inviteButton;
    private AppBarLayout appBarLayout;



    ArticleFragment context = this;


    public ArticleFragment() {
    }

    public static ArticleFragment newInstance(){
        ArticleFragment fragment = new ArticleFragment();
        return fragment;
    }

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
        final View view = inflater.inflate(R.layout.fragment_article, container, false);

        /* Setup view */
        imageView = (ImageView) view.findViewById(R.id.backdrop);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        inviteButton = (FloatingActionButton) view.findViewById(R.id.invite);
        loadData();
        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);

        appBarLayout.setExpanded(false);
        /* logic setters */
        setOnClickListeners();

        return view;

    }


    /**
     * Sets the onclick listeners for each view item
     */
    public void setOnClickListeners() {

        //onClick for back button

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
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
