package edu.grinnell.grinnell_publications_android.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Activities.ExpandedArticleActivity;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;

/**
 * @author Dennis Chan on 4/16/2017.
 */

//public class NewsfeedAdapter extends BaseAdapter{
public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.ViewHolder> {

    Context mContext;
    Activity activity;
    public List<RealmStory> mArticles;
    public BookmarksAdapter(Activity activity, List<RealmStory> articles) {
        this.activity = activity;
        mContext = activity.getApplicationContext();
        mArticles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bookmark_item, parent,
                false);
        return new ViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RealmStory story = mArticles.get(position);
        holder.mPublication.setText(getPublication(story.getPublication()));
        if (story.getHeaderImage() != null) {
            Picasso.with(mContext).load(story.getHeaderImage()).into(holder.mArticleImage);
        }
        if (story.getAuthors().size() != 0) {
            holder.mAuthor.setText(story.getAuthorsAsString());
        } else {
            holder.mAuthor.setText("Anonymous");
        }
        holder.mTitle.setText(story.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ExpandedArticleActivity.class);
                intent.putExtra("articleID", story.getArticleId());
                mContext.startActivity(intent);
            }
        });

    }

    public long getItemId (int i) { return i; }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mArticleImage;
        TextView mTitle;
        TextView mAuthor;
        TextView mPublication;
        ViewHolder(final View itemView) {
            super(itemView);
            mArticleImage = itemView.findViewById(R.id.bookmarkarticleiv);
            mTitle = itemView.findViewById(R.id.bookmark_articletitle);
            mAuthor = itemView.findViewById(R.id.bookmark_author);
            mPublication = itemView.findViewById(R.id.bookmark_publication);
        }
    }

    private String getPublication(String publication) {
        if (publication.equals("s-and-b")) {
            return "Scarlet and Black";
        }
        return "The GUM";
    }

}