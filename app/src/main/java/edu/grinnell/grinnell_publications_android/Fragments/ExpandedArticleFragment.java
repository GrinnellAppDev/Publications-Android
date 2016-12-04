package edu.grinnell.grinnell_publications_android.Fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    @Bind(R.id.article_fragment_toolbar) Toolbar mToolbar;
    @Bind(R.id.header_image) ImageView mHeaderImage;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.floating_action_button) FloatingActionButton mFavoriteButton;
    @Bind(R.id.article_content) TextView mArticleContent;

    public ExpandedArticleFragment() {}

    public static ExpandedArticleFragment newInstance() {
        ExpandedArticleFragment expandedArticleFragment = new ExpandedArticleFragment();
        expandedArticleFragment.setHasOptionsMenu(true);
        return expandedArticleFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.inflateMenu(R.menu.toolbar_settings_menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Oncreateview", "Inside the method");
        final View expandedArticleFragment = inflater.inflate(R.layout.fragment_extended_article, container, false);
        ButterKnife.bind(this, expandedArticleFragment);
        setHasOptionsMenu(true);
        initializeUI();
        return expandedArticleFragment;
    }

    @Override
    public void initializeUI(){
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_action_back));
        mToolbar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.scarlet_red));
        mCollapsingToolbar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.scarlet_red));
        mToolbar.inflateMenu(R.menu.toolbar_settings_menu);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        String TAG = "onCreateOptionsMenu: ";
        Log.d(TAG, "Inside the method");
        inflater.inflate(R.menu.toolbar_settings_menu, menu);
        Log.d(TAG, "after inflate");
        super.onCreateOptionsMenu(menu, inflater);
        Log.d(TAG, "Called super method");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String TAG = "onOptionsItemSelected: ";
        Log.d(TAG, "Inside the method");
        switch(item.getItemId()) {
            case R.id.article_settings:
                Log.d(TAG, "Switch activated");
                Snackbar.make(getView(), "Setting button pressed", Snackbar.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
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
