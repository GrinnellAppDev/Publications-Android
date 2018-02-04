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
 * @see RemoteClientAPI
 */
public interface NetworkClientAPI {

    /* Publications */

    /**
     * @return  all the publications the current user is subscribed to
     */
    List<Publication> getSubscribedPublications(int userId);

    /**
     * @return all the existing publications.
     */
    List<Publication> getAllPublications();

    /**
     * @return a publication by its Id.
     */
    Publication getPublicationById(String publicationId);

    /* Stories */

    /**
     * Returns all the stories that make up a given page.
     *
     * <p> Note that a page represents a segment of the local database with a number of stories</p>
     * @param page the page number to retrieve
     * @param numStoriesPerPage the number of stories that make up a page.
     * @return all the stories for the given page.
     */
    List<Story>getAllStories(int page,int numStoriesPerPage);

    /**
     * Fetches the most recent stories.
     *
     * <p>This method can be utilized when refreshing the application's main feed.</p>
     * @param subscribedPublicationIds
     * @param mostRecentStory
     * @return
     */
    List<Story> getRecentStories(List<Integer> subscribedPublicationIds,Date mostRecentStory);

    /**
     * Fetches more details about a given story.For example the  full body text and the body image
     * urls of story.
     *
     * @param storyId the identifier for the story
     * @return a full fledged story with all the details of the story.
     */
    Story getFullStoryById(int storyId);


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
    List<Story> getAllStoriesInSeries(int publicationId,int seriesId,int page,int numStoriesPerPage);

    /**
     * Fetches the most recent stories in a particular series.
     *
     * @param seriesId the identifier for the series.
     * @param mostRecentStoryInSeries most recent story.
     * @return a list of all the most recent stories in the series.
     */
    List<Story> getRecentStories(int seriesId,Date mostRecentStoryInSeries);

    /* Bookmarks */

    /**
     * @return a list of all the bookmarked stories
     */
    List<Story> getBookmarkedStories();

    /* Profile */
    /**
     *
     * @return  the profile information of the current User.
     */

    User getProfile();

    /* Search */
    //TODO add any methods that will be useful for searching. Eg. Autocomplete features?


}
