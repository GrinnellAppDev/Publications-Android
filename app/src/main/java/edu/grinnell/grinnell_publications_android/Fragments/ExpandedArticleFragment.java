package edu.grinnell.grinnell_publications_android.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;



import java.util.Date;
import java.util.logging.StreamHandler;

/**
 * Fragment that displays the expanded article content.
 * Fragment allows for favoriting of the article.
 */

public class ExpandedArticleFragment extends Fragment implements UserInterface {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.header_image) ImageView mHeaderImage;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.floating_action_button) FloatingActionButton mFavoriteButton;
    @Bind(R.id.article_content) TextView mArticleContent;

    public ExpandedArticleFragment() {}

    public static ExpandedArticleFragment newInstance(){
        ExpandedArticleFragment expandedArticleFragment = new ExpandedArticleFragment();
        return expandedArticleFragment;
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
        initializeUI();
        return expandedArticleFragment;
    }

    @Override
    public void initializeUI(){
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_action_back));
        loadPlaceHolderData();
        setOnClickListeners();
    }


    public void setOnClickListeners() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Sample article added to Favorites", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /** Fills fragment with placeholder content */
    private void loadPlaceHolderData(){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.grinnell_gates);
        Drawable defaultImage = new BitmapDrawable(getResources(), image);
        setHeaderText("Sample title");
        setContentText(                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris feugiat diam sollicitudin est semper, eu porta libero pulvinar. Aenean at lacus rhoncus, pharetra elit ut, ultricies magna.");
        setHeaderImage(defaultImage);
    }

    /** Gets image from Header**/
     public Drawable getHeaderImage() { return this.mHeaderImage.getDrawable(); }

    /** Gets text from content **/
    public String getTextContent() { return this.mArticleContent.getText().toString();}

    /** Sets the heading image **/
    private void setHeaderImage(Drawable image) {
        this.mHeaderImage.setImageDrawable(image);
    }

    /** Sets text into header field **/
    private void setHeaderText(String titleText) { this.mCollapsingToolbar.setTitle(titleText);}

    /** Sets text into content field **/
    private void setContentText(String content) { this.mArticleContent.setText(content);}



}
