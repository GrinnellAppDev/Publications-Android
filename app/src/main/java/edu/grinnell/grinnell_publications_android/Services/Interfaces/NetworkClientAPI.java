package edu.grinnell.grinnell_publications_android.Services.Interfaces;

import java.util.Date;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.User;

/**
 * Provides an application level abstraction of all the data needs of the application.
 *
 * <p> Classes that implement this interface must have composed objects of classes that implement
 * both the @code{LocalClientAPI} and @code{RemoteClientAPI}.</p>
 *
 * @author Albert Owusu-Asare
 * @version 1.1 Thu May  5 14:57:32 CDT 2016
 * @see LocalClientAPI
 */
public interface NetworkClientAPI {

    /* Publications */

    /**
     * @return  all the publications the current user is subscribed to
     */
    void getSubscribedPublications(int userId);

    /**
     * @return all the existing publications.
     */
    void getAllPublications();

    /**
     * @return a publication by its Id.
     */
    void getPublicationById(String publicationId);

    /* Stories */

    /**
     * Returns all the stories that make up a given page.
     *
     * <p> Note that a page represents a segment of the local database with a number of stories</p>
     * @return all the stories for the given page.
     */
    void getAllStoriesInPublication(String publicationId);

    /**
     * Fetches the most recent stories.
     *
     * <p>This method can be utilized when refreshing the application's main feed.</p>
     * @param subscribedPublicationIds
     * @param mostRecentStory
     * @return
     */
    void getRecentStories(List<String> subscribedPublicationIds,Date mostRecentStory);

    /**
     * Fetches more details about a given story.For example the  full body text and the body image
     * urls of story.
     *
     * @param publicationId  the identifier for the publication
     * @param storyId the identifier for the story
     * @return a full fledged story with all the details of the story.
     */
    void getFullStoryById(String publicationId, String storyId);


    /* Series*/

    /**
     * Fetches all the stories in a particular series.
     *
     * @param publicationId the  identifier for the publication that the series belongs to.
     * @param seriesId the identifier for the series.
     * @param page the page of stories to retrieve
     * @param numStoriesPerPage the number of stories that make up a page.
     * @return all the stories in the series.
     */
    void getAllStoriesInSeries(int publicationId,int seriesId,int page,int numStoriesPerPage);

    /**
     * Fetches the most recent stories in a particular series.
     *
     * @param mostRecentStoryInSeries most recent story.
     * @return a list of all the most recent stories in the series.
     */
    void getRecentStories(int seriesId, Date mostRecentStoryInSeries);

    /* Bookmarks */

    /**
     * @return a list of all the bookmarked stories
     */
    void getBookmarkedStories();

    /* Profile */
    /**
     *
     * @return  the profile information of the current User.
     */

    void getProfile();

    /* Search */
    //TODO add any methods that will be useful for searching. Eg. Autocomplete features?


}
