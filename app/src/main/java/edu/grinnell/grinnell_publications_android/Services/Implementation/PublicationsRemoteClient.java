package edu.grinnell.grinnell_publications_android.Services.Implementation;

import edu.grinnell.grinnell_publications_android.Models.RemoteQueryResponse;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.RemoteClientAPI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Remote client that connects to remote end points through networking calls.
 *
 * <p> All networking calls are made through calling on a retrofit networking service.
 * As an observable, updates to the remote calls are routed to the respective observers for either
 * changes to the UI or changes to the local data base.</p>
 * @author Albert Owusu-Asare
 * @version 1.1 Thu May  5 15:57:45 CDT 2016
 */
public class PublicationsRemoteClient implements RemoteClientAPI {

    //TODO : Add logging to the HTTP calls.
    // TODO : DRY out service calls.? Repetition
    /* Private classes  and interfaces */
    private interface PublicationsServiceAPI {
        /* Publications*/
        @GET("publications/{id}")
        Call<RemoteQueryResponse> publication( @Path("id")int publicationId);
        /* Articles */
        @GET("articles/{id}")
        Call<RemoteQueryResponse> article( @Path("id")int articleId);
        /* Images */
        @GET("images/{id}")
        Call<RemoteQueryResponse> image( @Path("id")int imageId);
        /* Series */
        @GET("series/{id}")
        Call<RemoteQueryResponse> series( @Path("id")int seriesId);
    }
}
