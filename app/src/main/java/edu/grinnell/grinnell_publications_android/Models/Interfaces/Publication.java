package edu.grinnell.grinnell_publications_android.Models.Interfaces;

import java.util.List;



/**
 * A model for a publication hosted within the application.
 *
 *<p>Implementors of this class have control of what local caching mechanism to apply to the model
 * </p>
 *
 * @author Albert Owusu-Asare
 * @since 1.1 Thu May  5 22:53:49 CDT 2016
 */
public interface Publication {
    /**
     * @return the name of the publication
     */
    String getPublicationName();

    /**
     * @return the id of the publication
     */
    int getPublicationId();

    /**
     * @return a list of all the series featured on this publication
     */
    List<Series> getFeaturedSeries();

    /**
     * @return a list of all the stories featured in the series.
     */
    List<Story> getFeaturedStories();

    /**
     * @return the image url for the publication.
     */
    String getPublicationImageUrl();
}
