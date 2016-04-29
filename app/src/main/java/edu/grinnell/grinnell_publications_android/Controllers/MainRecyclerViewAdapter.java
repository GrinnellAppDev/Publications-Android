package edu.grinnell.grinnell_publications_android.Controllers;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Controllers.Cards.LargeArticleCard;
import edu.grinnell.grinnell_publications_android.Controllers.Cards.LargeArticleHolder;
import edu.grinnell.grinnell_publications_android.Controllers.Cards.SmallArticleCard;
import edu.grinnell.grinnell_publications_android.Controllers.Cards.SmallArticleHolder;

/**
 *
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<RecyclerItem> data;
    private Activity activity;

    public MainRecyclerViewAdapter(Activity activity) {
        this.data = new ArrayList<>();
        this.activity = activity;
    }

    /**
     * Update the data for the recycler view
     *
     * @param data a list of recycler items that will populate the recycler view
     */
    public void setData(List<RecyclerItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = activity.getLayoutInflater();
        RecyclerViewHolder holder;
        switch (viewType) {
            case SmallArticleCard.SMALL_ARTICLE:
                holder = new SmallArticleHolder(li.inflate(SmallArticleCard.SMALL_ARTICLE, parent));
                break;
            case LargeArticleCard.LARGE_ARTICLE:
                holder = new LargeArticleHolder(li.inflate(LargeArticleCard.LARGE_ARTICLE, parent));
                break;
            default:
                holder = null;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bindView(data.get(position), activity);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getViewType();
    }
}
