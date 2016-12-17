package edu.grinnell.grinnell_publications_android.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;

/**
 * Created by Mattori on 12/3/16.
 */

public class ArticleAdapter extends RecyclerView.Adapter {
    private List<RealmStory> stories;

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public CardView card;

        public ArticleViewHolder(View view) {
            super(view);
        }
    }

    public ArticleAdapter(List<RealmStory> stories) {
        this.stories = stories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.viewholder_article, parent, false);
        ArticleViewHolder viewHolder = new ArticleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RealmStory story = stories.get(position);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
}
