package edu.grinnell.grinnell_publications_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import edu.grinnell.grinnell_publications_android.R;

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
        ArrayList<Article> a;

    public NewsfeedAdapter (Context c) {
        mContext = c;
        a = new ArrayList<>();
        populateArrayTest();
    }

    public void populateArrayTest () {
        a.add(new Article(R.drawable.grinnell_gates, R.drawable.sandb,
                "This Is a Super Long Title. Lets See How it Shows Up", "Andrea Baumgartel & Some Long Nameeeee", "March 27"));
        a.add(new Article(R.drawable.grinnell_gates, R.drawable.sandb,
                "This Is a Even Longer Title. Lets See How it Shows Uppppppppppp", "Andrea Baumgartel & Long Name", "February 32"));
        for (int i = 0; i < 10; i++)
            a.add(new Article(R.drawable.grinnell_gates, R.drawable.sandb,
                "Ray King rayray k is rayking rk", "Andrea Baumgartel", "March 27"));
    }

    public long getItemId (int i) { return i; }
    public int getCount() { return a.size(); }
    public Object getItem (int i) { return a.get(i); }

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

        holder.mThumbnail.setImageResource(a.get(i).mThumbnail);
        holder.mPublicationIcon.setImageResource(a.get(i).mPubIcon);
        holder.mAuthor.setText(a.get(i).mAuhtor);
        holder.mDatePublished.setText(a.get(i).mDatePubllished);


        if (a.get(i).mTitle.length() < 50)  holder.mTitle.setText(a.get(i).mTitle);
        else  holder.mTitle.setText(a.get(i).mTitle.substring(0,50) + "...");

        return row;
    }

}
