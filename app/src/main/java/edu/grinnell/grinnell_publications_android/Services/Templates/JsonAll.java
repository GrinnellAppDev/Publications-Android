package edu.grinnell.grinnell_publications_android.Services.Templates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anpandey on 2/25/18.
 */

public class JsonAll<T> {


    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    @SerializedName("items")
    @Expose
    private List<T> items;

    public String getNextPageToken() { return nextPageToken; }

    public void setNextPageToken(String nextPageToken) { this.nextPageToken = nextPageToken; }

    public List<T> getItems() { return items; }

}
