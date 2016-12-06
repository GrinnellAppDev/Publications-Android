package edu.grinnell.grinnell_publications_android.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;

/**
 * Created by Mattori on 12/3/16.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<RealmStory> mStories;
    private Context mContext;

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;
        public ImageView mThumbnail;
        public TextView mTitle;
        public TextView mAuthor;
        public TextView mPublication;

        public ArticleViewHolder(View view) {
            super(view);
            mImage = (ImageView) view.findViewById(R.id.image);
            mThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            mTitle = (TextView) view.findViewById(R.id.title);
            mAuthor = (TextView) view.findViewById(R.id.author);
            mPublication = (TextView) view.findViewById(R.id.publication);
        }
    }

    public ArticleAdapter(List<RealmStory> stories, Context ctxt) {
        mStories = stories;
        mContext = ctxt;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.viewholder_article, parent, false);
        ArticleViewHolder vh = new ArticleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ArticleViewHolder holder, int position) {
        RealmStory story = mStories.get(position);
        // TODO: placeholder and error images
        Picasso.with(mContext).load(story.getImageUrl()).into(holder.mImage);
        Picasso.with(mContext).load(story.getThumbnailUrl()).into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mStories.size();
    }
}
