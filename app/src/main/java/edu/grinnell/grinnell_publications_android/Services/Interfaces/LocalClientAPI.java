package edu.grinnell.grinnell_publications_android.Services.Interfaces;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;

/**
 * Abstracts the core functionality needed in on device persistence.
 *
 * <p> Classes that implement this interface  must provide data storage  for on  device persistence
 * using SQLite or any other non SQLite technologies. </p>
 *
 *@author Albert Owusu-Asare
 *@version Thu May  5 14:42:22 CDT 2016
 */
public interface LocalClientAPI  extends NetworkClientAPI{
    /**
     * Indicates whether the local cache is empty.
     *
     * @return @code{true} if the cache is empty.
     */
    boolean isCacheEmpty();

    /**
     * Indicates whether the local cache is full.
     *
     * @return @code{true} if the cache is full.
     */
    boolean isCacheFull();


    /* PUBLICATIONS */

    /**
     * Adds a publication to the the subscribed publication.
     *
     * @param publicationId the publication to add.
     */
    void addToSubscribedPublications(String publicationId);


    /**
     * Saves all the publications to the local cache.
     *
     * @param publications  the collection of all the publications
     */

    void savePublications(List<Publication> publications);

    /**
     * Saves a particular publication to the local cache.
     */

    void savePublication(Publication publication);

    void addBookmark(String publicationId, String storyId);
}
