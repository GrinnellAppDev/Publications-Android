package edu.grinnell.grinnell_publications_android.Models.Realm;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Series;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
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
    private int publicationId;
    private RealmList<RealmSeries> featuredSeries;
    private RealmList<RealmStory> featuredStories;
    private String publicationImageUrl;

    /* Default constructor required by Realm*/
    public RealmPublication(){

    }

    /** Setters */
    public void setPublicationName(String publicationName){this.publicationName = publicationName;}
    public void setPublicationId(int publicationId){this.publicationId = publicationId;}
    public void setFeaturedSeries(RealmList<RealmSeries> featuredSeries){
        this.featuredSeries =featuredSeries;}
    public void setFeaturedStories(RealmList<RealmStory> featuredStories){
        this.featuredStories = featuredStories;
    }
    public void setPublicationImageUrl(String publicationImageUrl){
        this.publicationImageUrl = publicationImageUrl;
    }

    /** Getters */
    @Override
    public String getPublicationName() {return this.publicationName;}

    @Override
    public int getPublicationId() {
        return this.publicationId;
    }

    @Override
    public List<Series> getFeaturedSeries() {
        return (List) this.featuredSeries;
    }

    @Override
    public List<Story> getFeaturedStories() {
        return this.getFeaturedStories();
    }

    @Override
    public String getPublicationImageUrl() {
        return this.publicationImageUrl;
    }
}
