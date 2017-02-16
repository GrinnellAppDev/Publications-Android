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
    private String datePublished;
    private String brief;
    private String headerImage;
    private String publication;
    private String dateEdited;
    private int articleId;
    private String title;
    private String content;
    private RealmList authors;


    /* Default constructor for Realm */
    public RealmStory(){}

    public RealmStory(String datePublished, String brief, String headerImage, String publication,
                      String dateEdited, int articleId, String title, String content,
                      RealmList authors) {
        this.datePublished = datePublished;
        this.brief = brief;
        this.headerImage = headerImage;
        this.publication = publication;
        this.dateEdited = dateEdited;
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.authors = authors;
    }

    /** Setters */
    public void setPublication(String publication) {
        if (publication.isEmpty() || publication == null) {
            return;
        }
        this.publication = publication;
    }

    public void setDatePublished(String datePublished) {
        if (datePublished.isEmpty() || datePublished == null) {
            return;
        }
        this.datePublished = datePublished;
    }

    public void setDateEdited(String dateEdited) {
        if (dateEdited.isEmpty() || dateEdited == null) {
            return;
        }
        this.dateEdited = dateEdited;
    }

    public void setAuthors(RealmList<RealmAuthor> authors) {
        if (authors.isEmpty() || authors == null) {
            return;
        }
        this.authors = authors;
    }

    public void setContent(String content) {
        if (content.isEmpty() || content == null) {
            return;
        }
        this.content = content;
    }

    public void setBrief(String brief) {
        if (brief.isEmpty() || brief == null) {
            return;
        }
        this.brief = brief;
    }

    public void setTitle(String title) {
        if(title.isEmpty() || title == null) {
            return;
        }
        this.title = title;
    }

    public void setArticleId(int articleId) {
        if (articleId == null) {
            return;
        }
        this.articleId = articleId;
    }

    public void setHeaderImage(String headerImage) {
        if (headerImage.isEmpty() || headerImage == null) {
            return;
        }
        this.headerImage = headerImage;
    }

    /** Getters */
    @Override
    public String getPublication() {
        return this.publication;
    }

    @Override
    public String getDatePublished() {
        return this.datePublished;
    }

    @Override
    public String getDateEdited() { return this.dateEdited; }

    @Override
    public RealmList<RealmAuthor> getAuthors() {
        return  this.authors;
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
    public int getArticleId() { return this.articleId; }

    @Override
    public String getHeaderImage() { return this.headerImage;}
}
