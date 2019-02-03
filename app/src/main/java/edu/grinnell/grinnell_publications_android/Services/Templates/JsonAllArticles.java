package edu.grinnell.grinnell_publications_android.Services.Templates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonAllArticles {

    @SerializedName("items")
    @Expose
    private List<JsonArticle> articles;
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    public List<JsonArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<JsonArticle> articles) {
        this.articles = articles;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

}