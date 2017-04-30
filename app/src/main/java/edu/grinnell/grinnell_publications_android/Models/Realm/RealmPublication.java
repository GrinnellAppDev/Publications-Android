package edu.grinnell.grinnell_publications_android.Models.Realm;


import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * This class implements @code{Publication} using Realm persistence.
 *
 * @author Albert Owusu-Asare
 * @version 1.1 Fri May  6 02:40:59 CDT 2016
 * @see RealmObject
 * @see Publication
 */
public class RealmPublication extends RealmObject implements Publication {
    private String publicationName;
    private String publicationId;
    private RealmList<RealmSeries> series;
    private RealmList<RealmStory> stories;
    private String publicationImageUrl;
    private boolean isFavoriteButtonToggled = false;

    /* Default constructor required by Realm*/
    public RealmPublication(){}

    public RealmPublication(String publicationName, String publicationId,
                            RealmList<RealmSeries> series, RealmList<RealmStory> stories,
                            String publicationImageUrl) {
        this.publicationName = publicationName;
        this.publicationId = publicationId;
        this.series = series;
        this.stories = stories;
        this.publicationImageUrl = publicationImageUrl;
    }

    /** Setters */
    public void setPublicationName(String publicationName){this.publicationName = publicationName;}
    public void setPublicationId(String publicationId){this.publicationId = publicationId;}
    public void setSeries(RealmList<RealmSeries> series){
        this.series =series;}
    public void setStories(RealmList<RealmStory> stories){
        this.stories = stories;
    }
    public void setPublicationImageUrl(String publicationImageUrl){
        this.publicationImageUrl = publicationImageUrl;
    }
    public void setIsFavoriteButtonToggled() {
        this.isFavoriteButtonToggled = !isFavoriteButtonToggled;
    }

    /** Getters */
    @Override
    public String getPublicationName() {return this.publicationName;}

    @Override
    public String getPublicationId() {
        return this.publicationId;
    }

    @Override
    public RealmList<RealmSeries> getSeries() {
        return this.series;
    }

    @Override
    public RealmList<RealmStory> getStories() {
        return  this.stories;
    }

    @Override
    public String getPublicationImageUrl() {
        return this.publicationImageUrl;
    }

    public boolean getIsFavoriteButtonToggled() { return this.isFavoriteButtonToggled; }
}
