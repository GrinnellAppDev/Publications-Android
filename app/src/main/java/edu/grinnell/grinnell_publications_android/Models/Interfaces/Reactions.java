package edu.grinnell.grinnell_publications_android.Models.Interfaces;

/**
 *  An abstraction of the various reactions users can have to a story.
 *
 *  @author Albert Owusu-Asare
 *  @since 1.1 Thu May  5 23:44:43 CDT 2016
 *  @see Story
 */
public interface Reactions {
    /**
     * @return the number of favorites a story has received.
     */
    int getNumFavorites();

    /**
     * @return the number of times a story has been viewed.
     */
    int getNumViews();
}
