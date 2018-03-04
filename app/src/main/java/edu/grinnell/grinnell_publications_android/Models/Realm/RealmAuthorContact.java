package edu.grinnell.grinnell_publications_android.Models.Realm;

import java.util.Map;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.AuthorContact;
import edu.grinnell.grinnell_publications_android.Utils;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * This class implements @code{AuthorContact} using Realm persistence.
 *
 * @author Albert Owusu-Asare
 * @version 1.1 Fri May  6 02:40:59 CDT 2016
 * @see RealmObject
 * @see AuthorContact
 */
public class RealmAuthorContact extends RealmObject implements AuthorContact {
    private String email;
    private String phoneNumber;
    @Ignore
    private Map<Utils.AuthorContactUrl,String> contactUrlMap;

    public RealmAuthorContact() {}

    /* Default constructor needed by Realm*/
    public RealmAuthorContact(String email, String phoneNumber, String somethingElse){
    }

    /** Setters */
    public void setEmail(String email){this.email = email;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setContactUrlMap(Map<Utils.AuthorContactUrl,String> contactUrlMap){
        this.contactUrlMap = contactUrlMap;
    }
    /** Getters */
    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public Map<Utils.AuthorContactUrl, String> getContactUrlMap() {
        return this.contactUrlMap;
    }
}
