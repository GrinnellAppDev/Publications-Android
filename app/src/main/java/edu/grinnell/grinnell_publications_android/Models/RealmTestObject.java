package edu.grinnell.grinnell_publications_android.Models;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class RealmTestObject extends RealmObject {
    public String name;
    public String url;

    // You can instruct Realm to ignore a field and not persist it.
    @Ignore
    private int tempReference;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTempReference() {
        return tempReference;
    }

    public void setTempReference(int tempReference) {
        this.tempReference = tempReference;
    }
}
