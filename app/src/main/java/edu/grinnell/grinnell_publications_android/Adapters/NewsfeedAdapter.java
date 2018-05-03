package edu.grinnell.grinnell_publications_android.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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

    public void updateStories(List<Story> stories) {
        this.mStories = stories;
        notifyDataSetChanged();
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

        // Get story id to pass into activity
        final String storyId = mStories.get(i).getArticleId();

        // Respond to Action
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ensures onclicklistener works
                Toast.makeText(mContext, "clicked", Toast.LENGTH_LONG).show();
                // launches article activity
                // TODO: implement ArticleActivity.class
                // Intent intent = new Intent(this, ArticleActivity.class);
                // intent.putExtra("storyId", storyId);
                // mContext.startActivity(intent);

            }
        });

        return row;
    }

    public ViewHolder populateSingleView (ViewHolder holder, Story story) {

        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        holder.mDatePublished.setText(dateFormat.format(story.getDatePublished()));

        if (story.getHeaderImage() != null)
            Glide.with(mContext).load(story.getHeaderImage()).into(holder.mThumbnail);


        /*  Loads publication icon from URL */
        //if (story.getPublication().getPublicationImageUrl() != null)
            Glide.with(mContext).load("https://i.imgur.com/P361xoU.jpg").into(holder.mPublicationIcon);

        /*
        final ImageView imageView = (ImageView) findViewById(R.id.mPublicationIcon);
        imageView.setImageResource(sand.png);
        */
        if (story.getAuthors() != null && story.getAuthors().size() > 0)
            holder.mAuthor.setText(story.getAuthors().get(0).getFullName());
        else
            holder.mAuthor.setText("Unknown Author");

        // Handling long titles
        String title = story.getTitle();
        if (title.length() < 50)  holder.mTitle.setText(title);
        else  holder.mTitle.setText(title.substring(0,50) + "...");

        return holder;
    }

}
