package edu.grinnell.grinnell_publications_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;
import io.realm.Realm;

/**
 * @author Dennis Chan on 4/16/2017.
 */

public class NewsfeedAdapter extends BaseAdapter{

    private class Article {
        public int mThumbnail;
        public int mPubIcon;
        public String mTitle;
        //support multiple author?
        public String mAuhtor;
        public String mDatePubllished;

        public Article(int tn, int ic, String t, String a, String d) {
            mThumbnail = tn;
            mPubIcon = ic;
            mTitle = t;
            mAuhtor = a;
            mDatePubllished = d;
        }
    }


    Context mContext;
    List<Story> mStories;

    public NewsfeedAdapter (Context c, List<Story> stories) {
        mContext = c;
        mStories = stories;
    }

    public long getItemId (int i) { return i; }
    public int getCount() { return mStories.size(); }
    public Object getItem (int i) { return mStories.get(i); }

    class ViewHolder {
        ImageView mThumbnail;
        ImageView mPublicationIcon;
        TextView mTitle;
        TextView mAuthor;
        TextView mDatePublished;
        public ViewHolder (View v) {
            mThumbnail = (ImageView) v.findViewById(R.id.nf_articlethumbnail);
            mPublicationIcon = (ImageView) v.findViewById(R.id.nf_articlepub);
            mTitle = (TextView) v.findViewById(R.id.nf_articletitle);
            mAuthor = (TextView) v.findViewById(R.id.nf_author);
            mDatePublished = (TextView) v.findViewById(R.id.nf_date);
        }
    }

    public View getView (int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_newfeed_item, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else holder = (ViewHolder) row.getTag();

        holder = populateSingleView(holder, mStories.get(i));

        return row;
    }

    public ViewHolder populateSingleView (ViewHolder holder, Story story) {

        holder.mDatePublished.setText(story.getPublicationDate());

        if (story.getThumbnailUrl() != null)
            Glide.with(mContext).load(story.getThumbnailUrl()).into(holder.mThumbnail);
        if (story.getPublication().getPublicationImageUrl() != null)
            Glide.with(mContext).load(story.getPublication()
                    .getPublicationImageUrl()).into(holder.mPublicationIcon);

        holder.mAuthor.setText(story.getAuthor().get(0).getFullName());

        // Handling long titles
        String title = story.getTitle();
        if (title.length() < 50)  holder.mTitle.setText(title);
        else  holder.mTitle.setText(title.substring(0,50) + "...");

        return holder;
    }

}
