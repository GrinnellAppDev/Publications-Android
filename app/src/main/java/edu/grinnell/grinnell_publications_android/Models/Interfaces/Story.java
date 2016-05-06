package edu.grinnell.grinnell_publications_android.Models.Interfaces;


import java.util.List;

/**
 * @author Albert Owusu-Asare
 * @since 1.1 Thu May  5 23:29:12 CDT 2016
 * @see Publication
 * @see Series
 * @see Author
 * @see Reactions
 */
public interface Story {
    /**
     * Returns all the @code{publication}s the story belongs to.
     *
     * <p>If the story belongs to a single @code{publication} then the size of the list returned is
     * 1.</p>
     *
     * @return the publication the story belongs to.
     */
    List<Publication> getPublication();

    /**
     * Returns the date that the story was published.
     *
     * <p>Note that by default the date is ISO8601 formatted @code{yyyy-MM-dd'T'HH:mm:ssZ}. </p>
     *
     * @return the date that the story was published.
     */
    String getPublicationDate();

    /**
     * Returns all the @code{author}s of the story.
     *
     * <p>A list of size 1 indicates that there exists a single author for the story</p>
     *
     * @return  all the @code{author}s of the story.
     */
    List<Author> getAuthor();

    /**
     * @return the reactions that have been made towards this article
     */
    Reactions getReactions();

    /**
     * @return the text body of the story
     */
    String getFullText();

    /**
     * @return the summary text  of the story
     */
    String getSummaryText();

    /**
     * @return the title of the story
     */

    String getStoryTitle();

    /**
     * @return all the urls for the images associated with the text of the story.
     */
    List<String> getTextBodyImageUrls();

    /**
     * @return the thumbnail image url for the story
     */

    String getThumbnailUrl();

}
