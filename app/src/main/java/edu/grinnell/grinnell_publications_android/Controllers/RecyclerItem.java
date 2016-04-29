package edu.grinnell.grinnell_publications_android.Controllers;

/**
 *
 */
public abstract class RecyclerItem {

    private int viewType;
    private int layoutId;

    /**
     * Constructor
     *
     * @param layoutId the integer identifier for the view type.
     *                 Use the resource id of the layout as the view type.
     */
    public RecyclerItem(int layoutId) {
        this.viewType = this.layoutId = layoutId;

    }

    public int getViewType() {
        return viewType;
    }

    public int getLayoutId() {
        return layoutId;
    }



}
