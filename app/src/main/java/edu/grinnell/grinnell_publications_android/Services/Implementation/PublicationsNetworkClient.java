package edu.grinnell.grinnell_publications_android.Services.Implementation;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Constants;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.Models.RemoteQueryResponse;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.NetworkClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.OnNetworkCallCompleteListener;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAll;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAllPublications;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAllStories;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonPublication;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonStory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Remote client that connects to remote end points through networking calls.
 * <p>
 * <p> All networking calls are made through calling on a retrofit networking service.
 * As an observable, updates to the remote calls are routed to the respective observers for either
 * changes to the UI or changes to the local data base.</p>
 *
 * @author Albert Owusu-Asare, Dennis Chan
 * @version 1.2 Thu May  5 15:57:45 CDT 2017
 */
public class PublicationsNetworkClient implements NetworkClientAPI {

    private Retrofit retrofit;
    private LocalClientAPI localClient;
    private PublicationsAPI publicationsApi;
    private OnNetworkCallCompleteListener onNetworkCallCompleteListener;
    private int pageSize;

    public PublicationsNetworkClient(OnNetworkCallCompleteListener listener) {
        this(new RealmLocalClient(), listener);
    }

    public PublicationsNetworkClient(
            LocalClientAPI localClient, OnNetworkCallCompleteListener listener) {
        this.onNetworkCallCompleteListener = listener;
        this.localClient = localClient;
        retrofit = new Retrofit.Builder().baseUrl(Constants.AWS_BASE_API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        publicationsApi = retrofit.create(PublicationsAPI.class);
        pageSize = 50;
    }

    @Override
    public void getSubscribedPublications(int userId) {
    }

    @Override
    public void getAllPublications() {
        Call<JsonAllPublications> call = publicationsApi.publications();
        call.enqueue(new Callback<JsonAllPublications>() {
            @Override
            public void onResponse(Call<JsonAllPublications> call,
                                   Response<JsonAllPublications> response) {
                List<Publication> publications = new ArrayList<>();
                for (JsonPublication jsonPublication : response.body().getPublications())
                    publications.add(RealmPublication.from(jsonPublication));
                localClient.savePublications(publications);

                onNetworkCallCompleteListener.onNetworkCallSucceeded();
            }

            public void onFailure(Call<JsonAllPublications> call, Throwable t) {
                onNetworkCallCompleteListener.onNetworkCallFailed("Unable to Retrieve Story from Server");
            }
        });
    }

    @Override
    public void getPublicationById(String publicationId) {

    }

    @Override
    public void getAllStoriesInPublication(String publicationId) {
        Call<JsonAllStories> call = publicationsApi.articles(publicationId, pageSize);
        call.enqueue(new Callback<JsonAllStories>() {
            @Override
            public void onResponse(Call<JsonAllStories> call, Response<JsonAllStories> response) {
                Log.d("hii", "onResponse: npt: " + response.body().getNextPageToken());
                Log.d("hi", "onResponse: " + response.body().toString());
                List<Story> stories = new ArrayList<>();
                for (JsonStory jsonStory : response.body().getPublications()) {
                    stories.add(RealmStory.from(jsonStory));
                }
                localClient.saveStories(stories);

                onNetworkCallCompleteListener.onNetworkCallSucceeded();
            }

            @Override
            public void onFailure(Call<JsonAllStories> call, Throwable t) {
                onNetworkCallCompleteListener.onNetworkCallFailed("Unable to Retrieve Story from Server");
            }
        });
    }

    @Override
    public void getRecentStories(List<String> subscribedPublicationIds, Date mostRecentStory) {

    }

    @Override
    public void getFullStoryById(String publicationId, String storyId) {
        Call<JsonStory> call = publicationsApi.article(publicationId, storyId);
        call.enqueue(new Callback<JsonStory>() {
            @Override
            public void onResponse(Call<JsonStory> call, Response<JsonStory> response) {
                localClient.saveFullStory(RealmStory.from(response.body()));
                onNetworkCallCompleteListener.onNetworkCallSucceeded();

            }

            public void onFailure(Call<JsonStory> call, Throwable t) {
                onNetworkCallCompleteListener.onNetworkCallFailed("Unable to Retrieve Story from Server");
            }
        });
    }

    @Override
    public void getAllStoriesInSeries(int publicationId, int seriesId, int page, int numStoriesPerPage) {

    }

    @Override
    public void getRecentStories(int seriesId, Date mostRecentStoryInSeries) {

    }

    @Override
    public void getBookmarkedStories() {

    }

    @Override
    public void getProfile() {

    }


    private interface PublicationsAPI {
        /* Publications */
        @Headers("Accept: application/json")
        @GET("publications/")
        Call<JsonAllPublications> publications();

        /* Articles */
        @Headers("Accept: application/json")
        @GET("publications/{publicationId}/articles/{articleId}")
        Call<JsonStory> article(
                @Path("publicationId") String publicationId,
                @Path("articleId") String articleId);

        @Headers("Accept: application/json")
        @GET("publications/{publicationId}/articles")
        Call<JsonAllStories> articles(
                @Path("publicationId") String publicationId,
                @Query("pageSize") int pageSize);

        /* Publications by id */
        @Headers("Accept: application/json")
        @GET("publications/{id}")
        Call<Publication> publication(@Path("id") String publicationId);

        /* Not currently supported by AWS endpoints*/
        /* Images */
        @Headers("Accept: application/json")
        @GET("images/{id}")
        Call<RemoteQueryResponse> image(@Path("id") int imageId);

        /* Series */
        @GET("series/{id}")
        @Headers("Accept: application/json")
        Call<RemoteQueryResponse> series(@Path("id") int seriesId);
    }
}
