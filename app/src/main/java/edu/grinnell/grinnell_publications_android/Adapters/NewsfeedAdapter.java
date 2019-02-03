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
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonArticle;

/**
 * @author Dennis Chan on 4/16/2017.
 */

//public class NewsfeedAdapter extends BaseAdapter{
public class NewsfeedAdapter extends RecyclerView.Adapter<NewsfeedAdapter.ViewHolder> {

    Context mContext;
    Activity activity;
    public List<JsonArticle> mArticles;
    public NewsfeedAdapter (Activity activity, List<JsonArticle> articles) {
        this.activity = activity;
        mContext = activity.getApplicationContext();
        mArticles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newsfeed_item, parent,
                false);
        return new ViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JsonArticle article = mArticles.get(position);
        holder.mPublication.setText(getPublication(article.getPublication()));
        if (article.getHeaderImage() != null) {
            Picasso.with(mContext).load(article.getHeaderImage()).into(holder.mThumbnail);
        }
        if (article.getAuthors().size() != 0) {
            holder.mAuthor.setText(article.getAuthors().get(0).getName());
        } else {
            holder.mAuthor.setText("Anonymous");
        }
        holder.mTitle.setText(article.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ExpandedArticleActivity.class);
                intent.putExtra("articleID", article.getId());
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
        ImageView mThumbnail;
        TextView mTitle;
        TextView mAuthor;
        TextView mPublication;
        ViewHolder(final View itemView) {
            super(itemView);
            mThumbnail = itemView.findViewById(R.id.nf_articlethumbnail);
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



//    public View getView (int i, View view, ViewGroup viewGroup) {
//        View row = view;
//        ViewHolder holder;
//
//        if (view == null) {
//            LayoutInflater inflater = (LayoutInflater) mContext.
//                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            row = inflater.inflate(R.layout.layout_newsfeed_item, viewGroup, false);
//            holder = new ViewHolder(row);
//            row.setTag(holder);
//        } else holder = (ViewHolder) row.getTag();
//
//        holder = populateSingleView(holder, mArticles.get(i));
////        holder = populateSingleView(holder, mStories.get(i));
//
//        // Get story id to pass into activity
//        final String title = mArticles.get(i).getTitle();
//
//        final String id = mArticles.get(i).getId();
////        final String title = mStories.get(i).getTitle();
//
//        // Respond to Action
//        row.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // ensures onclicklistener works
////                Toast.makeText(mContext, title, Toast.LENGTH_SHORT).show();
//                // launches article activity
//                Intent intent = new Intent(view.getContext(), ExpandedArticleActivity.class);
////                intent.putExtra("storyTitle", title);
//                intent.putExtra("articleID", id);
//                mContext.startActivity(intent);
//            }
//        });
//
//        return row;
//    }
//
////    public ViewHolder populateSingleView (ViewHolder holder, Story story) {
////
////        holder.mDatePublished.setText(story.getDatePublished().toString());
////
////        /*  Loads publication icon from URL */
////        //if (story.getPublication().getPublicationImageUrl() != null)
////            //Glide.with(mContext).load("https://i.imgur.com/P361xoU.jpg").into(holder.mPublicationIcon);
////
////        /*
////        final ImageView imageView = (ImageView) findViewById(R.id.mPublicationIcon);
////        imageView.setImageResource(sand.png);
////        */
////
////        holder.mAuthor.setText(story.getAuthors().get(0).getFullName());
////
////        // Handling long titles
////        String title = story.getTitle();
////        if (title.length() < 50)  holder.mTitle.setText(title);
////        else  holder.mTitle.setText(title.substring(0,50) + "...");
////
////        return holder;
////    }
//
////    public void setThumbnailFromUrl(final String url, final ImageView thumbnailImageView) throws IOException {
////        final Bitmap[] x = new Bitmap[1];
////
////        new Thread() {
////            @Override
////            public void run() {
////                super.run();
////                HttpURLConnection connection = null;
////                try {
////                    connection = (HttpURLConnection) new URL(url).openConnection();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////                try {
////                    connection.connect();
////                    InputStream input = connection.getInputStream();
////                    x[0] = BitmapFactory.decodeStream(input);
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////                activity.runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        setThumbnail(new BitmapDrawable(mContext.getResources(), x[0]), thumbnailImageView);
////                    }
////                });
////            }
////        }.start();
////    }
//
//    private void setThumbnail(Drawable image, ImageView thumbnailImageView) {
//        thumbnailImageView.setImageDrawable(image);
//    }
//
//    public ViewHolder populateSingleView (ViewHolder holder, JsonArticle article) {
//
//        if (article.getHeaderImage() != null) {
//            Picasso.with(mContext).load(article.getHeaderImage()).into(holder.mArticleImage);
//        }
//
//        if (article.getAuthors().size() != 0) {
//            holder.mAuthor.setText(article.getAuthors().get(0).getName());
//        }
//
//        String publication = getPublication(article.getPublication());
//        holder.mPublication.setText(publication);
//        // Handling long titles
//        String title = article.getTitle();
//        holder.mTitle.setText(title);
//
//        return holder;
//    }
