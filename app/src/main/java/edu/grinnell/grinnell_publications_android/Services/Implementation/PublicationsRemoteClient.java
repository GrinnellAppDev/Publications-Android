package edu.grinnell.grinnell_publications_android.Services.Implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Set;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.RemoteQueryResponse;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.RemoteClientAPI;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Remote client that connects to remote end points through networking calls.
 *
 * <p> All networking calls are made through calling on a retrofit networking service.
 * As an observable, updates to the remote calls are routed to the respective observers for either
 * changes to the UI or changes to the local data base.</p>
 *
 * @author Albert Owusu-Asare
 * @version 1.1 Thu May  5 15:57:45 CDT 2016
 */
public class PublicationsRemoteClient implements RemoteClientAPI {
  private Retrofit retrofit;
  private LocalClientAPI localClient;
  private final PublicationsServiceAPI publicationsService;
  private final String BASE_API = "Insert Base API URL"; //TODO: Get base URL

  public PublicationsRemoteClient() {
    this(new RealmLocalClient());
  }

  public PublicationsRemoteClient(LocalClientAPI localClient) {
    this.localClient = localClient;
    this.retrofit = initializeRetrofitRestAdapter();
    this.publicationsService = this.retrofit.create(PublicationsServiceAPI.class);
  }

  @Override public List<Publication> getAllPublications() {
    return null;
  }

  @Override public Publication getPublication(String id) {
    return null;
  }

  @Override public List<Publication> getPublications(Set<String> publicationIds) {
    return null;
  }

    /* Public methods */

  //TODO : Add logging to the HTTP calls.
  // TODO : DRY out service calls.? Repetition
    /* Private classes  and interfaces */
  private interface PublicationsServiceAPI {
    /* Publications*/
    @GET("publications/{id}") Call<RemoteQueryResponse> publication(@Path("id") int publicationId);

    /* Articles */
    @GET("articles/{id}") Call<RemoteQueryResponse> article(@Path("id") int articleId);

    /* Images */
    @GET("images/{id}") Call<RemoteQueryResponse> image(@Path("id") int imageId);

    /* Series */
    @GET("series/{id}") Call<RemoteQueryResponse> series(@Path("id") int seriesId);
  }

  /* Private helper methods */
  private Retrofit initializeRetrofitRestAdapter() {
    Gson gson = new GsonBuilder().create();
    return new Retrofit.Builder().baseUrl(BASE_API)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
  }
}
