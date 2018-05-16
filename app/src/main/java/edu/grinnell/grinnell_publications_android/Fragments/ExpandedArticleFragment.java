package edu.grinnell.grinnell_publications_android.Fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Implementation.PublicationsNetworkClient;
import edu.grinnell.grinnell_publications_android.Services.Implementation.RealmLocalClient;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.NetworkClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.OnNetworkCallCompleteListener;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static android.support.design.widget.Snackbar.make;
import static android.support.v4.content.ContextCompat.getDrawable;


/**
 * Fragment displays an article and allows users to mark article as favorited.
 *
 * @author Yazan Kittaneh
 */

public class ExpandedArticleFragment extends Fragment implements UserInterface {

    private ImageView mHeaderImage;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private FloatingActionButton mFavoriteButton;
    private TextView mArticleContent;
    private Toolbar mArticleToolbar;

    private LocalClientAPI localClient;
    private NetworkClientAPI networkClient;
    private OnNetworkCallCompleteListener listener = new OnNetworkCallCompleteListener() {
        @Override
        public void onNetworkCallSucceeded() {
            Toast.makeText(getContext(), "Full Story obtained.", Toast.LENGTH_SHORT).show();
            updateStory();
        }

        @Override
        public void onNetworkCallFailed(String message) {
            Toast.makeText(getContext(), "Unable to load article.", Toast.LENGTH_SHORT).show();
        }
    };

    private boolean isFavorited = false;
    private int colorFilter;
    private String snackBarMessage;
    private String storyId;
    private Story story;

    public ExpandedArticleFragment() {
        localClient = new RealmLocalClient();
        networkClient = new PublicationsNetworkClient(localClient, listener);
    }

    /**
     * Looks for the full story in the realm database.
     * If it doesn't exist, it downloads it from the network client.
     *
     * @param storyId
     */
    public void setStoryIdAndUpdate(String storyId) {
        this.storyId = storyId;
        Story story = localClient.getFullStoryById(storyId);
        if (!story.isFullStory()) {
            networkClient.getFullStoryById(story.getPublication(), storyId);
        } else {
            updateStory();
        }
    }

    public void updateStory() {
        story = localClient.getFullStoryById(storyId);
        setHeaderText(story.getTitle());
        Picasso.with(getContext())
                .load(story.getHeaderImage())
                .placeholder(R.drawable.grinnell_gates)
                .fit()
                .into(mHeaderImage);
        Log.d("updateStory", "updateStory: " + story.isFullStory());
        if (story.isFullStory()) {
            mArticleContent.setText(story.getContent());
        }
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
    public void initializeUI(View view) {
        Log.d("Nav icon", String.valueOf(getDrawable(getContext(), R.drawable.ic_action_back) == null));
        //mArticleToolbar.setNavigationIcon(getDrawable(getContext(), R.drawable.ic_action_back));
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
                make(view, getText(R.string.article_sample_favorited), LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Fills fragment with placeholder content
     */
    private void loadPlaceHolderData() {
        setHeaderText(getText(R.string.article_sample_title).toString());
        mArticleContent.setText(getText(R.string.article_sample_content).toString());
        mHeaderImage.setImageResource(R.drawable.grinnell_gates);
    }


    /**
     * Sets text into header field
     **/
    private void setHeaderText(String titleText) {
        this.mCollapsingToolbar.setTitle(titleText);
    }


}
