package edu.grinnell.grinnell_publications_android.Models.Realm;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * This class implements @code{Story} using Realm Persistence
 * @author Albert Owusu-Asare
 * @since 1.1 Fri May  6 10:01:38 CDT 2016
 * @see Story
 * @see RealmObject
 */
public class RealmStory extends RealmObject implements Story{
    private RealmPublication publication;
    private String publicationDate;
    private String lastUpdated;
    private RealmList<RealmAuthor> authors;
    private RealmReactions reactions;
    private String fullText;
    private String blurb;
    private String title;
    private String thumbnailUrl;
    private String imageUrl;


    /* Default constructor for Realm */
    public RealmStory(){}

    public RealmStory(RealmPublication publication, String publicationDate, String lastUpdated,
                      RealmList<RealmAuthor> authors, RealmReactions reactions, String fullText,
                      String blurb, String title, String thumbnailUrl, String imageUrl) {
        this.publication = publication;
        this.publicationDate = publicationDate;
        this.lastUpdated = lastUpdated;
        this.authors = authors;
        this.reactions = reactions;
        this.fullText = fullText;
        this.blurb = blurb;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.imageUrl = imageUrl;
    }

    /** Setters */
    public void setPublication(RealmPublication publication){
        this.publication = publication;
    }
    public void setPublicationDate(String publicationDate){
        this.publicationDate = publicationDate;
    }

    public void setLastUpdated(String lastUpdated){
        this.lastUpdated = lastUpdated;
    }
    public void setReactions(RealmReactions realmReactions){
        this.reactions = realmReactions;
    }
    public void setFullText(String fullText){this.fullText = fullText;}
    public void setBlurb(String blurb){this.blurb = blurb;}
    public void setStoryTitle(String title){this.title = title;}
    public void setThumbnailUrl(String thumbnailUrl){this.thumbnailUrl = thumbnailUrl;}
    public void setAuthors(RealmList<RealmAuthor> author){this.authors = author;}
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    /** Getters */
    @Override
    public RealmPublication getPublication() {
        return  this.publication;
    }

    @Override
    public String getPublicationDate() {
        return this.publicationDate;
    }

    @Override
    public String getLastUpdated(){ return this.lastUpdated;}

    @Override
    public RealmList<RealmAuthor> getAuthors() {
        return  this.authors;
    }

    @Override
    public RealmReactions getReactions() {
        return this.reactions;
    }

    @Override
    public String getFullText() {
        return this.fullText;
    }

    @Override
    public String getBlurb() {
        return this.blurb;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    @Override
    public String getImageUrl() { return this.imageUrl; }
}
