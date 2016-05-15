package edu.grinnell.grinnell_publications_android.Models.Interfaces;

import java.util.Map;

import edu.grinnell.grinnell_publications_android.Utils;

/**
 * Provides the core functionality needed for contacting story @code{author}s.
 * @author Albert Owusu-Asare
 * @since 1.1 Thu May  5 23:57:22 CDT 2016
 */
public interface AuthorContact {
    /**
     * @return the email of the author
     */
    String getEmail();

    /**
     * @return the author phone number
     */
    String getPhoneNumber();

    /**
     * Returns an association of various popular web services that an author could have an account
     * on and the URLs that their accounts map to.
     *
     * <p>For example @code{{FACEBOOK => "www.facebook.com/author_name"}},
     * @code {{PERSONAL_WEBSITE =>"www.authorLovesToBlog.com"}}. See Utils.AuthorContact</p>
     *
     * @return a mapping of all the urls for accounts the author can be contacted on.
     */
    Map<Utils.AuthorContactUrl,String> getContactUrlMap();
}
