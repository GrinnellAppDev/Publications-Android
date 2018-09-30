package edu.grinnell.grinnell_publications_android.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Activities.ExpandedArticleActivity;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonArticle;
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
//    List<Story> mStories;
//    List<RealmStory> mStories;
    public List<JsonArticle> mArticles;
    public NewsfeedAdapter (Context c, List<JsonArticle> articles) {
        mContext = c;
        mArticles = articles;
//        mStories = articles;
    }

    public long getItemId (int i) { return i; }
    public int getCount() { return mArticles.size(); }
//    public int getCount() { return mStories.size(); }
    public Object getItem (int i) { return mArticles.get(i); }
//    public Object getItem (int i) { return mStories.get(i); }

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

        holder = populateSingleView(holder, mArticles.get(i));
//        holder = populateSingleView(holder, mStories.get(i));

        // Get story id to pass into activity
        final String title = mArticles.get(i).getTitle();

        final String id = mArticles.get(i).getId();
//        final String title = mStories.get(i).getTitle();

        // Respond to Action
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ensures onclicklistener works
//                Toast.makeText(mContext, title, Toast.LENGTH_SHORT).show();
                // launches article activity
                Intent intent = new Intent(view.getContext(), ExpandedArticleActivity.class);
//                intent.putExtra("storyTitle", title);
                intent.putExtra("articleID", id);
                mContext.startActivity(intent);
            }
        });

        return row;
    }

    public ViewHolder populateSingleView (ViewHolder holder, Story story) {

        holder.mDatePublished.setText(story.getDatePublished().toString());

        if (story.getHeaderImage() != null) {}
            // Glide.with(mContext).load(story.getHeaderImage()).into(holder.mThumbnail);


        /*  Loads publication icon from URL */
        //if (story.getPublication().getPublicationImageUrl() != null)
            //Glide.with(mContext).load("https://i.imgur.com/P361xoU.jpg").into(holder.mPublicationIcon);

        /*
        final ImageView imageView = (ImageView) findViewById(R.id.mPublicationIcon);
        imageView.setImageResource(sand.png);
        */

        holder.mAuthor.setText(story.getAuthors().get(0).getFullName());

        // Handling long titles
        String title = story.getTitle();
        if (title.length() < 50)  holder.mTitle.setText(title);
        else  holder.mTitle.setText(title.substring(0,50) + "...");

        return holder;
    }

    public ViewHolder populateSingleView (ViewHolder holder, JsonArticle article) {

        Date date = new Date(article.getDatePublished());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
        holder.mDatePublished.setText(simpleDateFormat.format(date));

        if (article.getHeaderImage() != null) {}
//         Glide.with(mContext).load(story.getHeaderImage()).into(holder.mThumbnail);


        /*  Loads publication icon from URL */
        //if (story.getPublication().getPublicationImageUrl() != null)
        //Glide.with(mContext).load("https://i.imgur.com/P361xoU.jpg").into(holder.mPublicationIcon);

        /*
        final ImageView imageView = (ImageView) findViewById(R.id.mPublicationIcon);
        imageView.setImageResource(sand.png);
        */

        if (article.getAuthors().size() != 0) {
            holder.mAuthor.setText(article.getAuthors().get(0).getName());
        }

        // Handling long titles
        String title = article.getTitle();
        if (title.length() < 50)  holder.mTitle.setText(title);
        else  holder.mTitle.setText(title.substring(0,50) + "...");

        return holder;
    }

}
