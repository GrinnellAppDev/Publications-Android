package edu.grinnell.grinnell_publications_android.Models.Realm;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Article;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Author;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAuthor;
import io.realm.RealmList;
import io.realm.RealmObject;

public class RealmArticle extends RealmObject implements Article {

    private String articleID;
    private String publication;
    private String articleTitle;
    private Long datePublished;
    private RealmList<RealmAuthor> authors;
    private Integer readTimeMinutes;
    private String headerImage;

    public RealmArticle() {

    }

    public RealmArticle(String articleID,
                        String publication,
                        String articleTitle,
                        Long datePublished,
                        RealmList<RealmAuthor> authors,
                        Integer readTimeMinutes,
                        String headerImage) {
        this.articleID = articleID;
        this.publication = publication;
        this.articleTitle = articleTitle;
        this.datePublished = datePublished;
        this.authors = authors;
        this.readTimeMinutes = readTimeMinutes;
        this.headerImage = headerImage;
    }

    @Override
    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    @Override
    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    @Override
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Override
    public Long getDatePublished() {
        return datePublished;
    }

    @Override
    public List<Author> getAuthors() {
        return null;
    }

    public void setDatePublished(Long datePublished) {
        this.datePublished = datePublished;
    }

    public RealmList<RealmAuthor> getRealmAuthors() {
        return authors;
    }

    public void setAuthors(RealmList<RealmAuthor> authors) {
        this.authors = authors;
    }

    @Override
    public Integer getReadTimeMinutes() {
        return readTimeMinutes;
    }

    public void setReadTimeMinutes(Integer readTimeMinutes) {
        this.readTimeMinutes = readTimeMinutes;
    }

    @Override
    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }
}
