package edu.grinnell.grinnell_publications_android.Services.POJO;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JStory {

    @SerializedName("datePublished")
    @Expose
    private String datePublished;
    @SerializedName("brief")
    @Expose
    private String brief;
    @SerializedName("headerImage")
    @Expose
    private String headerImage;
    @SerializedName("publication")
    @Expose
    private String publication;
    @SerializedName("dateEdited")
    @Expose
    private String dateEdited;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("authors")
    @Expose
    private List<JAuthor> authors = null;

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(String dateEdited) {
        this.dateEdited = dateEdited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<JAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<JAuthor> authors) {
        this.authors = authors;
    }

}