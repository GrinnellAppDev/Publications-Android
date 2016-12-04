package edu.grinnell.grinnell_publications_android.Fragments;


import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;


/**
 * Fragment that displays the expanded article content.
 * Fragment allows for favoriting of the article.
 */

public class ExpandedArticleFragment extends Fragment implements UserInterface {
    @Bind(R.id.header_image) ImageView mHeaderImage;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.floating_action_button) FloatingActionButton mFavoriteButton;
    @Bind(R.id.article_content) TextView mArticleContent;
    @Bind(R.id.article_toolbar) Toolbar mArticleToolbar;

    public ExpandedArticleFragment() {}

    public static ExpandedArticleFragment newInstance() {
        return new ExpandedArticleFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View expandedArticleFragment = inflater.inflate(R.layout.fragment_extended_article, container, false);
        ButterKnife.bind(this, expandedArticleFragment);
        initializeUI();
        return expandedArticleFragment;
    }

    @Override
    public void initializeUI(){
        mArticleToolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_action_back));
        loadPlaceHolderData();
        setOnClickListeners();
    }


    public void setOnClickListeners() {
        mArticleToolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
        Drawable defaultImage = ContextCompat.getDrawable(getContext(), R.drawable.grinnell_gates);
        setHeaderText("Sample title");
        setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris feugiat diam sollicitudin est semper, eu porta libero pulvinar. Aenean at lacus rhoncus, pharetra elit ut, ultricies magna.");
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
