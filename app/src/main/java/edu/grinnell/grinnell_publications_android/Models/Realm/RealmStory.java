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
    private String datePublished;
    private String lastUpdated;
    private RealmList<RealmAuthor> author;
    private RealmReactions reactions;
    private String content;
    private String brief;
    private String title;
    private String thumbnailUrl;
    private String webUrl;
    private String series;
    private String tags;
    private String issue;
    private String articleId;






    /* Default constructor for Realm */
    public RealmStory(){}

    public RealmStory(RealmPublication publication, String publicationDate, String lastUpdated,
                      RealmList<RealmAuthor> author, RealmReactions reactions, String content,
                      String brief, String title, String thumbnailUrl, String webUrl, String series,
                      String tags, String issue, String articleId) {
        this.publication = publication;
        this.datePublished = publicationDate;
        this.lastUpdated = lastUpdated;
        this.author = author;
        this.reactions = reactions;
        this.content = content;
        this.brief = brief;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.webUrl = webUrl;
        this.series = series;
        this.tags = tags;
        this.issue = issue;
        this.articleId = articleId;
    }

    /** Setters */
    public void setPublication(RealmPublication publication){
        this.publication = publication;
    }
    public void setPublicationDate(String publicationDate){
        this.datePublished = publicationDate;
    }

    public void setLastUpdated(String lastUpdated){
        this.lastUpdated = lastUpdated;
    }
    public void setReactions(RealmReactions realmReactions){
        this.reactions = realmReactions;
    }
    public void setFullText(String fullText){this.content = fullText;}
    public void setBlurb(String blurb){this.brief = blurb;}
    public void setStoryTitle(String title){this.title = title;}
    public void setThumbnailUrl(String thumbnailUrl){this.thumbnailUrl = thumbnailUrl;}
    public void setAuthor(RealmList<RealmAuthor> author){this.author = author;}
    /** Getters */
    @Override
    public RealmPublication getPublication() {
        return  this.publication;
    }

    @Override
    public String getPublicationDate() {
        return this.datePublished;
    }

    @Override
    public String getLastUpdated(){ return this.lastUpdated;}

    @Override
    public RealmList<RealmAuthor> getAuthor() {
        return  this.author;
    }

    @Override
    public RealmReactions getReactions() {
        return this.reactions;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getBrief() {
        return this.brief;
    }

    @Override
    public String getTitle() { return this.title; }

    @Override
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    @Override
    public String getWebUrl() { return this.webUrl; }

    @Override
    public String getSeries() { return this.series; }

    @Override
    public String getArticleId() { return this.articleId; }
}
