package edu.grinnell.grinnell_publications_android.Services.Interfaces;

import java.util.List;
import java.util.Set;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;

/**
 * Represents the core functionality that a remote client should provide to the application.
 *
 * @author Albert Owusu-Asare
 * @version 1.1 Thu May  5 14:34:42 CDT 2016
 */
public interface RemoteClientAPI {
    /**
     * @return all the available publications.
     */
    List<Publication> getAllPublications();

    /**
     * Fetches a publication by id
     *
     * @param id the unique identifier for that publication.
     * @return the publication that matches the requested id.
     */
    Publication getPublication(int id);

    /**
     * Returns a list of publications that matches a set of publication ids.
     *
     * <p> An example of a use case for this method is when obtaining all the publications a user is
     * subscribed to </p>
     *
     * @param publicationIds a collection of the ids of the publications desired.
     * @return all the publications matching the various publication ids
     */
    List<Publication> getPublications(Set<Integer> publicationIds);
}
