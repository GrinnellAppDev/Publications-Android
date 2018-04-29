package edu.grinnell.grinnell_publications_android.Models.Interfaces;

/**
 * Represents a user of the application.
 *
 * @author Albert Owusu-Asare
 * @version 1.1 Fri May  6 02:14:47 CDT 2016
 */
public interface User {
    /**
     * @return the username of the current user
     */
    String getUserName();

    /**
     * @return the id of the current user.
     */
    int getUserId();
}
