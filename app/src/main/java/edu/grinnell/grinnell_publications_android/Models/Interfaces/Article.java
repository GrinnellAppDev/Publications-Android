package edu.grinnell.grinnell_publications_android.Models.Interfaces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Services.Templates.JsonAuthor;

public interface Article {

    String getArticleID();

    String getPublication();

    String getArticleTitle();

    Long getDatePublished();

    List<Author> getAuthors();

    Integer getReadTimeMinutes();

    String getHeaderImage();

}
