package edu.grinnell.grinnell_publications_android.Models.Interfaces;


import java.util.AbstractList;
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
     * @return the publication id the story belongs to.
     */
    String getPublication();

    /**
     * Returns the date that the story was published.
     *
     * <p>Note that by default the date is ISO8601 formatted @code{yyyy-MM-dd'T'HH:mm:ssZ}. </p>
     *
     * @return the date that the story was published.
     */
    String getPublicationDate();

    /**
     * Returns the date on which the story was last updated.
     *
     *  <p>Note that by default the date is ISO8601 formatted @code{yyyy-MM-dd'T'HH:mm:ssZ}. </p>
     *
     * @return the data that the story was lastUpdated
     */
    String getLastUpdated();

    /**
     * Returns all the @code{author}s of the story.
     *
     * <p>A list of size 1 indicates that there exists a single author for the story</p>
     *
     * @return  all the @code{author}s of the story.
     */
    AbstractList<? extends Author> getAuthor();

    /**
     * @return the reactions that have been made towards this article
     */
    Reactions getReactions();

    /**
     * @return the text body of the story
     */
    String getContent();

    /**
     * @return the summary text  of the story
     */
    String getBrief();

    /**
     * @return the title of the story
     */

    String getTitle();


    /**
     * @return the thumbnail image url for the story
     */

    String getThumbnailUrl();

    /**
     * @return the web url of the story
     */

    String getWebUrl();

    /**
     * @return the series the article is in
     */
    String getSeries();

    /**
     * @return the id of the article
     */

    /**
     * @return the tag of the article
     */
    String getTags();

    /**
     * @return the issue the story belongs to
     */
    String getIssue();

    int getArticleId();

}
