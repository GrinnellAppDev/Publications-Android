package edu.grinnell.grinnell_publications_android.Models.Interfaces;

import java.util.List;

/**
 * A model for the author of a @code{story}.
 *
 * <p>Implementors of this class have control of what local caching mechanism to apply to the model
 * </p>
 *
 * @author Albert Owusu-Asare
 * @since 1.1 Thu May  5 23:33:51 CDT 2016
 * @see Story
 * @see Publication
 * @see Series
 */
public interface Author {
    /**
     * @return the full name of the author of a story
     */
    String getFullName();

    /**
     * @return the contact information of the author.
     */
    AuthorContact getAuthorContactInfo();

    /**
     * @return all the publications the author has published to.
     */
    List<Publication> getPublicationsFeatured();

    /**
     * @return all the series the author has published to.
     */

    List<Series> getSeriesFeatured();
}
