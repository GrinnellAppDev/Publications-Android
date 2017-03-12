package edu.grinnell.grinnell_publications_android.Services.Templates;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonStory {

    @SerializedName("datePublished")
    @Expose
    private String mDatePublished;
    @SerializedName("brief")
    @Expose
    private String mBrief;
    @SerializedName("headerImage")
    @Expose
    private String mHeaderImage;
    @SerializedName("publication")
    @Expose
    private String mPublication;
    @SerializedName("dateEdited")
    @Expose
    private String mDateEdited;
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("content")
    @Expose
    private String mContent;
    @SerializedName("authors")
    @Expose
    private List<JsonAuthor> mAuthors = null;

    public String getDatePublished() {
        return mDatePublished;
    }

    public void setDatePublished(String datePublished) {
        this.mDatePublished = datePublished;
    }

    public String getBrief() {
        return mBrief;
    }

    public void setBrief(String brief) {
        this.mBrief = brief;
    }

    public String getHeaderImage() {
        return mHeaderImage;
    }

    public void setHeaderImage(String headerImage) {
        this.mHeaderImage = headerImage;
    }

    public String getPublication() {
        return mPublication;
    }

    public void setPublication(String publication) {
        this.mPublication = publication;
    }

    public String getDateEdited() {
        return mDateEdited;
    }

    public void setDateEdited(String dateEdited) {
        this.mDateEdited = dateEdited;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public List<JsonAuthor> getAuthors() {
        return mAuthors;
    }

    public void setAuthors(List<JsonAuthor> authors) {
        this.mAuthors = authors;
    }

}