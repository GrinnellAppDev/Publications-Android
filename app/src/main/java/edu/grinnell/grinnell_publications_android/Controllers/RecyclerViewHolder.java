package edu.grinnell.grinnell_publications_android.Controllers;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 *
 */
public abstract class RecyclerViewHolder<RecyclerItem> extends RecyclerView.ViewHolder{


    public RecyclerViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Getter for the itemView from the super class
     * @return the itemView for the recycler item
     */
    public View getItemView() {
        return super.itemView;
    }

    /**
     *
     * @param recyclerItem
     */
    public abstract void bindView(RecyclerItem recyclerItem);



}
