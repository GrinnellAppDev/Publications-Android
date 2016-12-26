package edu.grinnell.grinnell_publications_android.Fragments;


import android.graphics.PorterDuff;
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

import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;


/**
 * Fragment displays articles and allows users to mark article as favorited.
 * @author Yazan Kittaneh
 */

public class ExpandedArticleFragment extends Fragment implements UserInterface {

    private ImageView mHeaderImage;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private FloatingActionButton mFavoriteButton;
    private TextView mArticleContent;
    private Toolbar mArticleToolbar;

    private boolean isFavorited = false;
    private int colorFilter;
    private String snackBarMessage;

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
        setViewBindings(expandedArticleFragment);
        initializeUI();
        return expandedArticleFragment;
    }

    @Override
    public void initializeUI(){
        mArticleToolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_action_back));
        loadPlaceHolderData();
        setOnClickListeners();
    }

    public void setViewBindings(View v){
        mHeaderImage = (ImageView) v.findViewById(R.id.header_image);
        mCollapsingToolbar = (CollapsingToolbarLayout) v.findViewById(R.id.collapsing_toolbar);
        mFavoriteButton = (FloatingActionButton) v.findViewById(R.id.floating_action_button);
        mArticleContent = (TextView) v.findViewById(R.id.article_content);
        mArticleToolbar = (Toolbar) v.findViewById(R.id.article_toolbar);
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
                //check if article is already in favorites
                if(isFavorited) {
                    isFavorited = false;
                    colorFilter = ContextCompat.getColor(getContext(), R.color.white);
                    snackBarMessage = getContext().getString(R.string.article_remove_favorite_snackbar);
                } else {
                    isFavorited = true;
                    colorFilter = ContextCompat.getColor(getContext(), R.color.scarlet_red);
                    snackBarMessage = getContext().getString(R.string.article_favorite_snackbar);
                }
                mFavoriteButton.setColorFilter(colorFilter, PorterDuff.Mode.MULTIPLY);
                Snackbar.make(v, snackBarMessage, LENGTH_SHORT).show();
            }
        });
    }

    /** Fills fragment with placeholder content */
    private void loadPlaceHolderData(){
        Drawable defaultImage = ContextCompat.getDrawable(getContext(), R.drawable.grinnell_gates);
        setHeaderText(getContext().getString(R.string.article_title));
        setContentText(getContext().getString(R.string.article_content));
        setHeaderImage(defaultImage);
    }

    @Override
    public void onDestroyView(){
        mHeaderImage = null;
        mCollapsingToolbar = null;
        mFavoriteButton = null;
        mArticleContent = null;
        mArticleToolbar = null;
        super.onDestroyView();
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
