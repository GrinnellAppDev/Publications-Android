package edu.grinnell.grinnell_publications_android.Models.Realm;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Reactions;
import io.realm.RealmObject;

/**
 * This class implements @code{Reactions} using Realm persistence.
 *
 *@author Albert Owusu-Asare
 *@sinece Fri May  6 03:01:25 CDT 2016
 *@see Reactions
 *@see RealmObject
 */
public class RealmReactions extends RealmObject implements Reactions {
    private int numFavorites;
    private int numViews;

    /* Default constructor required by Realm */
    public RealmReactions(){}

    /** Setters */
    public void setNumFavorites(int numFavorites){this.numFavorites = numFavorites;}
    public void setNumViews(int numViews){this.numViews = numViews;}

    /** Getters */
    @Override
    public int getNumFavorites() {
        return this.numFavorites;
    }

    @Override
    public int getNumViews() {
        return this.numViews;
    }
}
