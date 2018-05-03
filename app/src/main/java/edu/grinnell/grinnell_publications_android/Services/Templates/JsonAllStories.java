package edu.grinnell.grinnell_publications_android.Services.Templates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonAllStories {


    @SerializedName("nextPageToken")
    @Expose
    private String mNextPageToken;

    @SerializedName("items")
    @Expose
    private List<JsonStory> mStories;

    public String getNextPageToken() { return mNextPageToken; }

    public void setNextPageToken(String mNextPageToken) { this.mNextPageToken = mNextPageToken; }

    public List<JsonStory> getPublications() { return mStories; }

    public void setPublications(List<JsonStory> mStories) {
        this.mStories = mStories;
    }
}