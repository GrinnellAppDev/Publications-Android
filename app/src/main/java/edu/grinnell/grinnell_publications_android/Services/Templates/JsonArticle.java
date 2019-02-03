package edu.grinnell.grinnell_publications_android.Services.Templates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Realm.RealmArticle;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthor;
import io.realm.RealmList;

public class JsonArticle {

    public JsonArticle(String articleID,
                       String publication,
                       String articleTitle,
                       Long datePublished,
                       List<JsonAuthor> authors,
                       Integer readTimeMinutes,
                       String headerImage) {
        this.id = articleID;
        this.publication = publication;
        this.title = articleTitle;
        this.datePublished = datePublished;
        this.authors = authors;
        this.readTimeMinutes = readTimeMinutes;
        this.headerImage = headerImage;
    }

    public JsonArticle(RealmArticle realmArticle) {
        this.id = realmArticle.getArticleID();
        this.publication = realmArticle.getPublication();
        this.title = realmArticle.getArticleTitle();
        this.datePublished = realmArticle.getDatePublished();
        this.authors = getJsonAuthors(realmArticle.getRealmAuthors());
        this.readTimeMinutes = realmArticle.getReadTimeMinutes();
        this.headerImage = realmArticle.getHeaderImage();
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("publication")
    @Expose
    private String publication;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("datePublished")
    @Expose
    private Long datePublished;
    @SerializedName("authors")
    @Expose
    private List<JsonAuthor> authors;
    @SerializedName("readTimeMinutes")
    @Expose
    private Integer readTimeMinutes;
    @SerializedName("headerImage")
    @Expose
    private String headerImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Long datePublished) {
        this.datePublished = datePublished;
    }

    public List<JsonAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<JsonAuthor> authors) {
        this.authors = authors;
    }

    public Integer getReadTimeMinutes() {
        return readTimeMinutes;
    }

    public void setReadTimeMinutes(Integer readTimeMinutes) {
        this.readTimeMinutes = readTimeMinutes;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    private List<JsonAuthor> getJsonAuthors(RealmList<RealmAuthor> authors) {
        List<JsonAuthor> jsonAuthors = new ArrayList<>();
        for (RealmAuthor realmAuthor : authors) {
            JsonAuthor jsonAuthor = new JsonAuthor(realmAuthor.getFullName(), realmAuthor.getAuthorContactInfo().getEmail());
            jsonAuthors.add(jsonAuthor);
        }
        return jsonAuthors;
    }


}