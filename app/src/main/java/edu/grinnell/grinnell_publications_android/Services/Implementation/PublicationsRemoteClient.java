package edu.grinnell.grinnell_publications_android.Services.Implementation;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.grinnell.grinnell_publications_android.Constants;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmArticle;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthor;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthorContact;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.Models.RemoteQueryResponse;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.RemoteClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAllArticles;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAllPublications;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonArticle;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAuthor;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonPublication;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonStory;
import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


//CHANGED SO MUCH STUFF OMG WHY
//TODO: FIX THIS
/**
 * Remote client that connects to remote end points through networking calls.
 *
 * <p> All networking calls are made through calling on a mRetrofit networking service.
 * As an observable, updates to the remote calls are routed to the respective observers for either
 * changes to the UI or changes to the local data base.</p>
 *
 * @author Albert Owusu-Asare, Dennis Chan
 * @version 1.2 Thu May  5 15:57:45 CDT 2017
 */
public class PublicationsRemoteClient implements RemoteClientAPI {

  private Retrofit mRetrofit;
  private LocalClientAPI mLocalClient;
  private PublicationsAPI mPubAPI;
  private Realm mRealm;

  public PublicationsRemoteClient(Activity activity) {
    this(new RealmLocalClient(activity));
    context = activity.getApplicationContext();
  }

  public PublicationsRemoteClient(LocalClientAPI localClient) {
    this.mLocalClient = localClient;
    mRetrofit = new Retrofit.Builder().baseUrl(Constants.AWS_BASE_API)
        .addConverterFactory(GsonConverterFactory.create()).build();
    mPubAPI = mRetrofit.create(PublicationsAPI.class);
  }

  private Context context;

  private List<JsonArticle> articles;
  private String publicationString = "boooooo";
  private String articleString = "saddd";

  public List<JsonArticle> getArticles() {
    return articles;
  }
  public boolean done = false;

  @Override
  public void getAllPublications() {
    Call<JsonAllPublications> call = mPubAPI.publications();
    call.enqueue(new Callback<JsonAllPublications>() {
      @Override
      public void onResponse(Call<JsonAllPublications> call,
                             Response<JsonAllPublications> response) {
////        TODO: Still need to store in realm database
        publicationString = response.body().toString();
        Log.d("response", response.body().toString());
        articles = getAllArticles();


////        mLocalClient.saveresponse.body().getPublications().get(0);
//        storeRealmPublication(response.body().getPublications());
//        mLocalClient.savePublications(response.body().getPublications());
      }
      public void onFailure(Call<JsonAllPublications> call, Throwable t) {
        Toast.makeText(context, "Unable to retrieve articles from server", Toast.LENGTH_SHORT).show();
//        throw new IllegalStateException("Unable to Retrieve Story from Server");
      }
    });
  }


  public List<JsonArticle> getAllArticles() {
        Call<JsonAllArticles> call = mPubAPI.articles();
        call.enqueue(new Callback<JsonAllArticles>() {
            @Override
            public void onResponse(Call<JsonAllArticles> call, Response<JsonAllArticles> response) {
                Log.d("response", response.body().toString());
                articles = response.body().getArticles();
                done = true;
                storeRealmArticles(articles);
            }

            @Override
            public void onFailure(Call<JsonAllArticles> call, Throwable t) {
              Toast.makeText(context, "Unable to Retrieve Story from Server", Toast.LENGTH_SHORT).show();
//              throw new IllegalStateException("Unable to Retrieve Articles from Server");
            }
        });
        return articles;
    }

  private void storeRealmArticles(List<JsonArticle> articles) {
//    mRealm.
    for (JsonArticle jsonArticle : articles) {
      String articleID = jsonArticle.getId();
      String publication = jsonArticle.getPublication();
      String articleTitle = jsonArticle.getTitle();
      Long datePublished = jsonArticle.getDatePublished();
      RealmList<RealmAuthor> authors = new RealmList<>();
      if (jsonArticle.getAuthors().size() != 0) {
        authors.add(new RealmAuthor(jsonArticle.getAuthors().get(0).getFullName(),
                new RealmAuthorContact(jsonArticle.getAuthors().get(0).getEmail(), jsonArticle.getAuthors().get(0).getName(), ""), null, null));
      }
      Integer readTimeMinutes = jsonArticle.getReadTimeMinutes();
      String headerImage = jsonArticle.getHeaderImage();
      RealmArticle article = new RealmArticle(articleID,
              publication,
              articleTitle,
              datePublished,
              authors,
              readTimeMinutes,
              headerImage);
      storeRealmArticle(article);
    }
  }

//  public void getStory(String publicationId, String articleId) {
//    Call<JsonStory> call = mPubAPI.article(publicationId, articleId);
//    call.enqueue(new Callback<JsonStory>() {
//      @Override public void onResponse(Call<JsonStory> call, Response<JsonStory> response) {
//        // TODO: Call to save the story
//      }
//      public void onFailure(Call<JsonStory> call, Throwable t) {
//        throw new IllegalStateException("Unable to Retrieve Story from Server");
//      }
//    });
//  }

//  private RealmStory convertToRealmStory(JsonStory s) {
//    RealmList realmAuthorList = new RealmList<RealmAuthor>();
//    for (JsonAuthor author : s.getAuthors()) {
//      RealmAuthorContact contact = new RealmAuthorContact(author.getEmail(), null, null);
//      realmAuthorList.add(new RealmAuthor(author.getName(), contact, null, null));
//    }
//    return new RealmStory(s.getDatePublished(), s.getBrief(), s.getHeaderImage(), s.getPublication(),
//            s.getDateEdited(), s.getId(),s.getTitle(), s.getContent(), realmAuthorList);
//  }

  private void storeRealmStory(RealmStory realmStory) {
    if (realmStory == null) throw new NullPointerException();
    instantiateRealmObject();
    mRealm.copyToRealm(realmStory);
    mRealm.commitTransaction();
  }

  private void storeRealmArticle(RealmArticle realmArticle) {
    if (realmArticle == null) throw new NullPointerException();
    instantiateRealmObject();
    mRealm.copyToRealmOrUpdate(realmArticle);
    mRealm.commitTransaction();
  }


  private void storeRealmPublication(List<JsonPublication> jsonPublicationList) {
    RealmList realmPublicationList = new RealmList<RealmPublication>();
    for (JsonPublication jsonPublication : jsonPublicationList) {
//        RealmPublication realmPublication = new RealmPublication(jsonPublication.getName(),
//                jsonPublication.getId(),
//                jsonPublication.)
    }
  }

  private void instantiateRealmObject() {
    mRealm = Realm.getDefaultInstance();
    mRealm.beginTransaction();
  }

//  String id = "s-and-b";
  /* Not currently supported by AWS endpoints*/
  @Override public void getPublications(Set<Integer> publicationIds) { }
  public void getPublicationById(int id) { }

  public String getPublicationString() {
    return publicationString;
  }

  public String getArticleString() {
    return articleString;
  }

  private interface PublicationsAPI {
    /* Publications */
    @Headers("accept: application/json")
    @GET("publications") Call<JsonAllPublications> publications();

    /* Articles */
    @Headers("accept: application/json")
    @GET("publications/s-and-b/articles") Call<JsonAllArticles> articles();
//    @GET("publications/{id}/articles") Call<JsonAllArticles> articles(@Path("id") String publicationId);


    @GET("/publications/{publicationId}/articles/{articleId}") Call<JsonArticle> article(
        @Path("publicationId") String publicationId, @Path("articleId") String articleId);

    /* Not currently supported by AWS endpoints*/
    /* Publications by id */
    @GET("publications/{id}") Call<Publication> publication(@Path("id") String publicationId);

    /* Images */
    @GET("images/{id}") Call<RemoteQueryResponse> image(@Path("id") int imageId);

    /* Series */
    @GET("series/{id}") Call<RemoteQueryResponse> series(@Path("id") int seriesId);
  }
}
