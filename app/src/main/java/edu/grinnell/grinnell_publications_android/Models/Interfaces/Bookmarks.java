package edu.grinnell.grinnell_publications_android.Models.Interfaces;

import java.util.List;

/**
 * Created by yazankittaneh on 4/28/17.
 */

public interface  Bookmarks {

    /**
     * Adds boomark
     */
    void addBookmark(String publicationId);

    /**
     * @return the author phone number
     */
    List<String> getBookmarks();

}
