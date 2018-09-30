package edu.grinnell.grinnell_publications_android.Services.Templates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anpandey on 2/25/18.
 */

public class JsonAllPublications {


//    @SerializedName("nextPageToken")
//    @Expose
//    private String mNextPageToken;

    @SerializedName("items")
    @Expose
    private List<JsonPublication> mPublications;

//    public String getNextPageToken() { return mNextPageToken; }

//    public void setNextPageToken(String mNextPageToken) { this.mNextPageToken = mNextPageToken; }

    public List<JsonPublication> getPublications() { return mPublications; }

    public void setPublications(List<JsonPublication> mPublications) {
        this.mPublications = mPublications;
    }
}
