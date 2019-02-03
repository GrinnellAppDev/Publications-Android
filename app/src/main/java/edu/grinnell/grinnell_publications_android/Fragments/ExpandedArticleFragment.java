package edu.grinnell.grinnell_publications_android.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.grinnell.grinnell_publications_android.Constants;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmArticle;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Implementation.PublicationsRemoteClient;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAllPublications;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonArticle;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAuthor;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonStory;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
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
    private TextView mArticleTitle;
    private TextView mArticleAuthor;
    private TextView mArticleDatePublished;
    private TextView mArticlePublication;
    private JsonStory jsonStory;
    private Realm realm;



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
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            articleID = getArguments().getString("articleID");
        }
        mRetrofit = new Retrofit.Builder().baseUrl(Constants.AWS_BASE_API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        mPubAPI = mRetrofit.create(PublicationsAPI.class);
        jsonStory = null;
        progressDialog = new ProgressDialog(getContext());
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
        bindView(view);
        progressDialog.setMessage("Loading");
        progressDialog.show();
//        loadPlaceHolderData();
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
        jsonStory = null;
    }

    private void bindView(View view) {
        mHeaderImage = (ImageView) view.findViewById(R.id.header_image);
        mCollapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        mFavoriteButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        mArticleContent = (TextView) view.findViewById(R.id.article_content);
        mArticleToolbar = (Toolbar) view.findViewById(R.id.article_toolbar);
        mArticleTitle = (TextView) view.findViewById(R.id.article_title_tv);
        mArticleAuthor = (TextView) view.findViewById(R.id.article_author_tv);
        mArticleDatePublished = (TextView) view.findViewById(R.id.article_date_published_tv);
        mArticlePublication = (TextView) view.findViewById(R.id.article_publication_tv);
    }

    public void setOnClickListeners() {
        mArticleToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        mFavoriteButton.setSelected(true);

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                if (mFavoriteButton.isSelected()) {
                    mFavoriteButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.scarlet_red)));
                    mFavoriteButton.setRippleColor(getResources().getColor(R.color.black));
                    RealmStory realmStory = realm.where(RealmStory.class).equalTo("mArticleId", articleID).findFirst();
                    if (realmStory != null) {
                        realmStory.setBookmarked(false);
                        realmStory.deleteFromRealm();
                    }
                } else {
                    mFavoriteButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
                    mFavoriteButton.setRippleColor(getResources().getColor(R.color.scarlet_red));
                    RealmStory realmStory = realm.where(RealmStory.class).equalTo("mArticleId", articleID).findFirst();
                    if (realmStory == null) {
                        realmStory = new RealmStory(jsonStory.getDatePublished(),
                                jsonStory.getBrief(),
                                jsonStory.getHeaderImage(),
                                jsonStory.getPublication(),
                                jsonStory.getDateEdited(),
                                jsonStory.getId(),
                                jsonStory.getTitle(),
                                jsonStory.getContent(),
                                null);
                        realmStory.setBookmarked(true);
                        realm.copyToRealmOrUpdate(realmStory);
                        Toast.makeText(getContext(), "Made bookmark", Toast.LENGTH_SHORT).show();
                    }
                }
                mFavoriteButton.setSelected(!mFavoriteButton.isSelected());
                realm.commitTransaction();
//                make(view, getText(R.string.article_sample_favorited), LENGTH_SHORT).show();
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
                jsonStory = response.body();
                setStory(jsonStory);
            }

            @Override
            public void onFailure(Call<JsonStory> call, Throwable t) {

            }
        });
    }

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Palette p = Palette.from(bitmap).generate();//createPaletteSync(bitmap);
            Palette.Swatch vibrantSwatch = p.getVibrantSwatch();
            mHeaderImage.setImageBitmap(bitmap);
//            if(vibrantSwatch != null){
//                mArticleTitle.setTextColor(vibrantSwatch.getTitleTextColor());
//                mHeaderImage.setBackgroundColor(vibrantSwatch.getRgb());
//            }
            if (vibrantSwatch != null) {
                mHeaderImage.setBackgroundColor(vibrantSwatch.getRgb());
            }
            progressDialog.dismiss();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            progressDialog.dismiss();
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }

    };
    //todo
    private void setStory(JsonStory jsonStory) {
        setHeaderText(jsonStory.getTitle());
        setContentText(jsonStory.getContent());

        Picasso.with(getContext()).load(jsonStory.getHeaderImage()).into(target);

//        Picasso.with(getContext()).load(jsonStory.getHeaderImage()).fit().centerInside().into(this.mHeaderImage);
//        , new com.squareup.picasso.Callback() {
//            @Override
//            public void onSuccess() {
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onError() {
//                progressDialog.dismiss();
//            }
//        });
        setAuthorsText(jsonStory.getAuthors());
        setDatePublishedText(jsonStory.getDatePublished());
        setPublicationText(jsonStory.getPublication());
    }

    private void setPublicationText(String publication) {
        if (publication.equals("s-and-b")) {
            mArticlePublication.setText("Scarlet & Black");
        } else {
            mArticlePublication.setText("The GUM");
        }
    }

    @Override
    public void onDestroy() {  // could be in onPause or onStop
        Picasso.with(getContext()).cancelRequest(target);
        super.onDestroy();
    }

    private void setDatePublishedText(String datePublished) {
        Date date = new Date(Long.parseLong(datePublished));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        mArticleDatePublished.setText(simpleDateFormat.format(date));
    }

    private void setAuthorsText(List<JsonAuthor> authors) {
        StringBuilder text = new StringBuilder(mArticleAuthor.getText());
        for (JsonAuthor jsonAuthor : authors) {
            text.append(jsonAuthor.getFullName() + "\n");
        }
        if (text.length() == 0) {
            text.append("Anonymous\n");
        }
        mArticleAuthor.setText(text.substring(0, text.length()-1));
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
    private void setHeaderText(String titleText) {
//        this.mCollapsingToolbar.setTitle(titleText);
        this.mArticleTitle.setText(titleText);
    }

    /** Sets text into content field **/
    private void setContentText(String content) { this.mArticleContent.setText(content);}

    private interface PublicationsAPI {
        /* Publications */
        @Headers("accept: application/json")
        @GET("publications/{publicationId}/articles/{articleId}")
        Call<JsonStory> getStory(
                @Path("publicationId") String publicationId, @Path("articleId") String articleId);
    };
}
