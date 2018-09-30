package edu.grinnell.grinnell_publications_android.Fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.os.Bundle;

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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.grinnell.grinnell_publications_android.Constants;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Implementation.PublicationsRemoteClient;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAllPublications;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonArticle;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonStory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static android.support.design.widget.Snackbar.make;
import static android.support.v4.content.ContextCompat.getDrawable;
import static android.support.design.widget.Snackbar.LENGTH_SHORT;


/**
 * Fragment displays an article and allows users to mark article as favorited.
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
    private String articleID;

    public ExpandedArticleFragment() {}

    public static ExpandedArticleFragment newInstance() {
        return new ExpandedArticleFragment();
    }

    private Retrofit mRetrofit;
    private PublicationsAPI mPubAPI;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            articleID = getArguments().getString("articleID");
        }
        mRetrofit = new Retrofit.Builder().baseUrl(Constants.AWS_BASE_API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        mPubAPI = mRetrofit.create(PublicationsAPI.class);
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
        Log.d("Nav icon", String.valueOf(getDrawable(getContext(), R.drawable.ic_action_back)==null));
        //mArticleToolbar.setNavigationIcon(getDrawable(getContext(), R.drawable.ic_action_back));
        bindView(view);
        loadPlaceHolderData();
        getArticle();
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

    /** Fills fragment with placeholder content */
    private void loadPlaceHolderData(){
        Drawable defaultImage = getDrawable(getContext(), R.drawable.grinnell_gates);
        setHeaderText(getText(R.string.article_sample_title).toString());
        setContentText(getText(R.string.article_sample_content).toString());
        setHeaderImage(defaultImage);
    }

    private void getArticle() {
        Call<JsonStory> jsonStoryCall = mPubAPI.getStory("s-and-b", articleID);
        jsonStoryCall.enqueue(new Callback<JsonStory>() {
            @Override
            public void onResponse(Call<JsonStory> call, Response<JsonStory> response) {
                Log.d("yay", response.body().toString());
                JsonStory jsonStory = response.body();
                setStory(jsonStory);
            }

            @Override
            public void onFailure(Call<JsonStory> call, Throwable t) {

            }
        });
    }

    private void setStory(JsonStory jsonStory) {
        try {
            setHeaderFromUrl(jsonStory.getHeaderImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            setHeaderImage(drawableFromUrl(jsonStory.getHeaderImage()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        setHeaderText(jsonStory.getTitle());
        setContentText(jsonStory.getContent());

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

    private interface PublicationsAPI {
        /* Publications */
        @Headers("accept: application/json")
        @GET("publications/{publicationId}/articles/{articleId}")
        Call<JsonStory> getStory(
                @Path("publicationId") String publicationId, @Path("articleId") String articleId);
        };

    public void setHeaderFromUrl(final String url) throws IOException {
        final Bitmap[] x = new Bitmap[1];

        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) new URL(url).openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    x[0] = BitmapFactory.decodeStream(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setHeaderImage(new BitmapDrawable(getContext().getResources(), x[0]));
                    }
                });
            }
        }.start();
    }
}
