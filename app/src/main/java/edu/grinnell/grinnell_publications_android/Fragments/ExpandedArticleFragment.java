package edu.grinnell.grinnell_publications_android.Fragments;


import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

import static android.support.design.widget.Snackbar.make;
import static android.support.v4.content.ContextCompat.getDrawable;


/**
 * Fragment that displays the expanded article content.
 * Fragment allows for favoriting of the article.
 */

public class ExpandedArticleFragment extends Fragment implements UserInterface {

    private ImageView mHeaderImage;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private FloatingActionButton mFavoriteButton;
    private TextView mArticleContent;
    private Toolbar mArticleToolbar;


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

        initializeUI(expandedArticleFragment);
        return expandedArticleFragment;
    }

    @Override
    public void initializeUI(View view){
        mArticleToolbar.setNavigationIcon(getDrawable(getContext(), R.drawable.ic_action_back));
        bindView(view);
        loadPlaceHolderData();
        setOnClickListeners();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHeaderImage = null;
        mCollapsingToolbar = null;
        mFavoriteButton = null;
        mArticleContent = null;
        mArticleToolbar = null;
    }

    private void bindView(View view) {
        mHeaderImage = (ImageView) view.findViewById(R.id.header_image);
        mCollapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        mFavoriteButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        mArticleContent = (TextView) view.findViewById(R.id.article_content);
        mArticleToolbar = (Toolbar) view.findViewById(R.id.article_toolbar);
    }


    public void setOnClickListeners() {
        mArticleToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make(view, getText(R.string.article_sample_favorited), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /** Fills fragment with placeholder content */
    private void loadPlaceHolderData(){
        Drawable defaultImage = getDrawable(getContext(), R.drawable.grinnell_gates);
        setHeaderText(getText(R.string.article_sample_title).toString());
        setContentText(getText(R.string.article_sample_content).toString());
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
