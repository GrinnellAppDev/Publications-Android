package edu.grinnell.grinnell_publications_android.Models.Realm;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Author;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Reactions;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import io.realm.Realm;
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
    private RealmList<RealmPublication> publications;
    private String publicationDate;
    private RealmAuthor author;
    private RealmReactions reactions;
    private String fullText;
    private String summaryText;
    private String storyTitle;
    private String thumbnailUrl;
    private RealmList<RealmString> textBodyImageUrls;


    /* Default constructor for Realm */
    public RealmStory(){}
    /** Setters */
    public void setPublications(RealmList<RealmPublication> publications){
        this.publications = publications;
    }
    public void setPublicationDate(String publicationDate){
        this.publicationDate = publicationDate;
    }
    public void setReactions(RealmReactions realmReactions){
        this.reactions = realmReactions;
    }
    public void setFullText(String fullText){this.fullText = fullText;}
    public void setSummaryText(String summaryText){this.summaryText = summaryText;}
    public void setStoryTitle(String storyTitle){this.storyTitle = storyTitle;}
    public void setThumbnailUrl(String thumbnailUrl){this.thumbnailUrl = thumbnailUrl;}
    public void setTextBodyImageUrls(RealmList<RealmString> textBodyImageUrls){this.textBodyImageUrls =
            textBodyImageUrls;}
    public void setAuthor(RealmAuthor author){this.author = author;}
    /** Getters */
    @Override
    public List<Publication> getPublications() {
        return (List) this.publications;
    }

    @Override
    public String getPublicationDate() {
        return this.publicationDate;
    }

    @Override
    public List<Author> getAuthor() {
        return (List) this.author;
    }

    @Override
    public Reactions getReactions() {
        return this.reactions;
    }

    @Override
    public String getFullText() {
        return this.fullText;
    }

    @Override
    public String getSummaryText() {
        return this.summaryText;
    }

    @Override
    public String getStoryTitle() {
        return this.storyTitle;
    }

    @Override
    public List<String> getTextBodyImageUrls() {
        return (List) this.textBodyImageUrls;
    }

    @Override
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }
}
