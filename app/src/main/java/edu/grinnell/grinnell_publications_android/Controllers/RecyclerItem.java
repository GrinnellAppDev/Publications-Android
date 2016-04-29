package edu.grinnell.grinnell_publications_android.Controllers;

/**
 *
 */
public abstract class RecyclerItem {

    private int viewType;

    /**
     * Constructor
     *
     * @param layoutId the integer identifier for the view type.
     *                 Use the resource id of the layout as the view type.
     */
    public RecyclerItem(int layoutId) {
        this.viewType = layoutId;

    }

    public int getViewType() {
        return viewType;
    }

}
