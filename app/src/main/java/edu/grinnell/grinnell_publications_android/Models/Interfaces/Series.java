package edu.grinnell.grinnell_publications_android.Models.Interfaces;

import java.util.AbstractList;
import java.util.List;

/**
 * A model for a series by a particular @code{publication}.
 *
 * <p>Implementors of this class have control of what local caching mechanism to apply to the model
 *  </p>
 *
 *  @author Albert Owusu-Asare
 *  @since 1.1 Thu May  5 23:11:08 CDT 2016
 *  @see Publication
 */
public interface Series {
    /**
     * @return the name of the series.
     */
    String getSeriesName();

    /**
     * @return the series id.
     */
    int getSeriesId();

    /**
     * Indicates whether the series is subscribable or not.
     *
     * @return @code{true} if the series is subscribable
     */
    boolean getIsSubscribable();

    /**
     * Indicates if the series has a sub-series.
     *
     * @return @code{true} if the series has a sub-series.
     */
    boolean getHasSubSeries();

    /**
     * Returns all the stories featured on the series.
     * @return the stories featured on the series.
     */
    AbstractList<? extends Story> getStories();


}
