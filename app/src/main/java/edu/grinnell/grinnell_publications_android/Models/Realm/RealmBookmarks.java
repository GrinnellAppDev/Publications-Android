package edu.grinnell.grinnell_publications_android.Models.Realm;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Bookmarks;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by yazankittaneh on 4/28/17.
 */

public class RealmBookmarks extends RealmObject implements edu.grinnell.grinnell_publications_android.Models.Interfaces.Bookmarks {
    private List<String> storyIds;
    private String publicationId;

    public RealmBookmarks(String publicationId){
        this.publicationId = publicationId;
    }

    @Override
    public void addBookmark(String publicationId){
        this.storyIds.add(publicationId);
    }

    @Override
    public List<String> getBookmarks(){
        return this.storyIds;
    }
}
