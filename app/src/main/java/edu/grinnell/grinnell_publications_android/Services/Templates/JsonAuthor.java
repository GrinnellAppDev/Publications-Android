package edu.grinnell.grinnell_publications_android.Services.Templates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonAuthor {

    @SerializedName("mName")
    @Expose
    private String mName;
    @SerializedName("mEmail")
    @Expose
    private String mEmail;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}