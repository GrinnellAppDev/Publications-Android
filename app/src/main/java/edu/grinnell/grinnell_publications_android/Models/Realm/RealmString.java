package edu.grinnell.grinnell_publications_android.Models.Realm;

import io.realm.RealmObject;

/**
 * Wrapper class for Java.String in Realm.
 * @author Albert Owusu-Asare
 * @since Fri May  6 10:23:36 CDT 2016
 */
public class RealmString extends RealmObject {
    private String val;

    public String getValue() {
        return val;
    }

    public void setValue(String value) {
        this.val = value;
    }
}
