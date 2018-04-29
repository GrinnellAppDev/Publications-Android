package edu.grinnell.grinnell_publications_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.grinnell.grinnell_publications_android.R;

/**
 * @auhtor Dennis Chan on 4/16/2017.
 */

public class PublicationAdapter extends BaseAdapter {

    private class Publication {
        String mName;
        int mImageLink;
        boolean mIsFavorite;
        public Publication (String name, int link, boolean fav){
            mName = name; mImageLink = link; mIsFavorite = fav;
        }
    }

    Context mContext;
    ArrayList<Publication> mPublications;

    public PublicationAdapter(Context c) {
        mPublications = new ArrayList<>(); mContext = c;
        populateArrayTest();
    }

    private void populateArrayTest() {
        String[] pubNames = {"S&B", "The GUM", "B&S", "KDIC"};
        int[] pubImages = {R.drawable.sandb, R.drawable.thegum,
                R.drawable.bands, R.drawable.thegum};
        boolean[] isFavorite = {true, true, false, false};

        for (int i = 0; i < pubNames.length; i++) {
            mPublications.add(new Publication(pubNames[i], pubImages[i], isFavorite[i]));
        }
    }

    public long getItemId (int i) { return i; }
    public int getCount() { return mPublications.size(); }
    public Object getItem (int i) { return mPublications.get(i); }

    class ViewHolder {
        //needs refactoring
        ImageView mImageView;
        ImageView mStar;
        TextView mTextView;
        public ViewHolder (View v) {
            mImageView = (ImageView) v.findViewById(R.id.publication_img);
            mTextView = (TextView) v.findViewById(R.id.publication_name);
        }
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_publication_item, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else { holder = (ViewHolder) row.getTag(); }

        holder.mImageView.setImageResource(mPublications.get(position).mImageLink);
        holder.mTextView.setText(mPublications.get(position).mName);

        return row;

    }


}
