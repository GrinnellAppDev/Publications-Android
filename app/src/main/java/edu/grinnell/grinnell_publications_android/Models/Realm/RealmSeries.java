package edu.grinnell.grinnell_publications_android.Models.Realm;

import java.util.AbstractList;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Series;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * This class implements @code{Series} using Realm Persistence
 *
 * @author Albert Owusu-Asare
 * @since Fri May  6 03:03:01 CDT 2016
 * @see Series
 * @see RealmObject
 */
public class RealmSeries extends RealmObject implements Series {
    private String seriesName;
    private int seriesId;
    private boolean isSubscribable;
    private boolean hasSubSeries;
    private RealmList<RealmStory> stories;


    /* Default constructor required by Realm */
    public RealmSeries(){}
    /** Setters */
    public void setSeriesName(String seriesName){this.seriesName = seriesName;}
    public void setSeriesId(int seriesId){this.seriesId = seriesId;}
    public void setIsSubscribable(boolean isSubscribable){this.isSubscribable = isSubscribable;}
    public void setHasSubSeries(boolean hasSubSeries){this.hasSubSeries = hasSubSeries;}
    public void setStories(RealmList<RealmStory> stories){
        this.stories = stories;
    }
    @Override
    public String getSeriesName() {
        return this.seriesName;
    }

    @Override
    public int getSeriesId() {
        return this.seriesId;
    }

    @Override
    public boolean getIsSubscribable() {
        return this.isSubscribable;
    }

    @Override
    public boolean getHasSubSeries() {
        return this.hasSubSeries;
    }

    @Override
    public RealmList<RealmStory> getStories() {
        return  this.stories;
    }
}
