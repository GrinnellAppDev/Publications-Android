package edu.grinnell.grinnell_publications_android.Services.Implementation;

import android.support.design.widget.Snackbar;

import edu.grinnell.grinnell_publications_android.Constants;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthor;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthorContact;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.Models.RemoteQueryResponse;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.RemoteClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAuthor;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonPublication;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonStory;
import edu.grinnell.grinnell_publications_android.Utils;
import io.realm.Realm;
import io.realm.RealmList;
import java.util.List;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Remote client that connects to remote end points through networking calls.
 *
 * <p> All networking calls are made through calling on a mRetrofit networking service.
 * As an observable, updates to the remote calls are routed to the respective observers for either
 * changes to the UI or changes to the local data base.</p>
 *
 * @author Albert Owusu-Asare, Dennis Chan
 * @version 1.1 Thu May  5 15:57:45 CDT 2016
 */
public class PublicationsRemoteClient implements RemoteClientAPI {

  private Realm mRealm;
  private Retrofit mRetrofit;
  private LocalClientAPI mLocalClient;
  private PublicationsAPI mPubAPI;

  public PublicationsRemoteClient() {
    this(new RealmLocalClient());
  }

  public PublicationsRemoteClient(LocalClientAPI localClient) {
    this.mLocalClient = localClient;

    mRetrofit = new Retrofit.Builder().baseUrl(Constants.AWS_BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    mPubAPI = mRetrofit.create(PublicationsAPI.class);
  }

  @Override public void getAllPublications() {
    Call<List<JsonPublication>> call = mPubAPI.publications();
    call.enqueue(new Callback<List<JsonPublication>>() {
      @Override
      public void onResponse(Call<List<JsonPublication>> call, Response<List<JsonPublication>> response) {
        storeRealmPublication(response.body());
      }
      public void onFailure(Call<List<JsonPublication>> call, Throwable t) {
        //TODO: Throw Error
      }
    });
  }

  public void getStory(String publicationId, String articleId) {
    Call<JsonStory> call = mPubAPI.article(publicationId, articleId);
    call.enqueue(new Callback<JsonStory>() {
      @Override public void onResponse(Call<JsonStory> call, Response<JsonStory> response) {
        storeRealmStory(convertToRealmStory(response.body()));
      }
      public void onFailure(Call<JsonStory> call, Throwable t) {
        //TODO: Throw Error
      }
    });
  }

  private RealmStory convertToRealmStory(JsonStory story) {
    RealmList realmAuthorList = new RealmList<RealmAuthor>();
    for (JsonAuthor author : story.getAuthors()) {
      RealmAuthorContact contact = new RealmAuthorContact(author.getEmail(), null, null);
      realmAuthorList.add(new RealmAuthor(author.getName(), contact, null, null));
    }
    return new RealmStory(null, story.getDatePublished(), story.getDateEdited(), realmAuthorList,
            null, story.getContent(), story.getBrief(), story.getTitle(), story.getHeaderImage());
  }

  private void instantiateRealmPObject() {
    mRealm = Realm.getDefaultInstance();
    mRealm.beginTransaction();
  }

  private void storeRealmStory(RealmStory realmStory) {
    // TODO: store mRealm story into mRealm objects, will be implemented after pulling from update
    // Update mRealm publication with story
    instantiateRealmPObject();
  }

  private void storeRealmPublication(List<JsonPublication> list) {
    instantiateRealmPObject();
    for (JsonPublication item : list) {
      RealmPublication pub = new RealmPublication(item.getName(), item.getId(), null, null, null);
      mRealm.copyToRealm(pub);
      mRealm.commitTransaction();
    }
  }

  /* Not currently supported by AWS endpoints*/
  @Override public void getPublications(Set<Integer> publicationIds) { }
  public void getPublicationById(int id) { }

  private interface PublicationsAPI {
    /* Publications */
    @GET("publications/") Call<List<JsonPublication>> publications();

    /* Articles */
    @GET("publications/{publicationId}/articles/{articleId}") Call<JsonStory> article(
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
