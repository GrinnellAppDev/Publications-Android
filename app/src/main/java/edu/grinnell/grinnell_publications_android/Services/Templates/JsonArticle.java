package edu.grinnell.grinnell_publications_android.Services.Templates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

}