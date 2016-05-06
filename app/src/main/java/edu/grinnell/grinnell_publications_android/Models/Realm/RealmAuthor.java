package edu.grinnell.grinnell_publications_android.Models.Realm;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Author;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.AuthorContact;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Series;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * This class implements the @code{Author} interface using the Realm persistence.
 *
 * @author Albert Owusu-Asare
 * @version 1.1 Fri May  6 02:39:50 CDT 2016
 * @see RealmObject
 * @see Author
 */
public class RealmAuthor extends RealmObject implements Author {
    private String fullName;
    private AuthorContact authorContact;
    private List<Publication> publicationsFeatured;
    private RealmList<RealmSeries> seriesFeatured;
    /* Default constructor needed for Realm*/
    public RealmAuthor(){

    }
    /** Setters */
    public void setFullName(String fullName){ this.fullName = fullName;}
    public void setAuthorContact(AuthorContact authorContact){this.authorContact = authorContact;}
    public void setPublicationsFeatured(List<Publication> publicationsFeatured){
        this.publicationsFeatured = publicationsFeatured;}
    public void setSeriesFeatured(RealmList<RealmSeries> seriesFeatured){
        this.seriesFeatured = seriesFeatured;}
    /** Getters */
    @Override
    public String getFullName() {
        return this.fullName;
    }

    @Override
    public AuthorContact getAuthorContactInfo() {
        return this.authorContact;
    }

    @Override
    public List<Publication> getPublicationsFeatured() {
        return this.publicationsFeatured;
    }

    @Override
    public List<Series> getSeriesFeatured() {
        return (List) this.seriesFeatured;
    }
}
