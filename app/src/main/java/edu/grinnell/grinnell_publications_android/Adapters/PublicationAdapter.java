package edu.grinnell.grinnell_publications_android.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.R;

/**
 * {@link RecyclerView} Adapter for all {@code RealmPublications} to be displayed in the
 * {@code PublicationsFragment}
 * @author : Matt Murphy & Larry Asante
 */

public class PublicationAdapter
        extends RecyclerView.Adapter<PublicationAdapter.PublicationsViewHolder> {

    private List<RealmPublication> mPublications;
    private Context mContext;

    public PublicationAdapter(Context context, List<RealmPublication> publications) {
        this.mPublications = publications;
        this.mContext = context;
    }

    @Override
    public PublicationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View inflatedView = LayoutInflater.from(context).
                inflate(R.layout.layout_publication_item, parent, false);
        PublicationsViewHolder publicationView = new PublicationsViewHolder(inflatedView);
        return publicationView;
    }

    @Override
    public int getItemCount() {
        return mPublications.size();
    }

    @Override
    public void onBindViewHolder(PublicationsViewHolder holder, int position) {
        final RealmPublication publication = mPublications.get(position);

        // TODO: Use actual publication image and name as retrieved from database
        holder.setPublicationLogo(ContextCompat.getDrawable(mContext, R.drawable.grinnell_gates));
        holder.setPublicationName("Scarlet & Black");

        final ImageButton favoriteButton = holder.getFavoriteButton();
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: set drawable for favorite button as opposite of previous state
                if (publication.getIsFavoriteButtonToggled()) {
                    favoriteButton.setImageResource(R.drawable.ic_star_transparent);
                } else {
                    favoriteButton.setImageResource(R.drawable.ic_star_gold);
                }
                publication.setIsFavoriteButtonToggled();
            }
        });
    }

    static class PublicationsViewHolder extends RecyclerView.ViewHolder {

        private ImageView mPublicationLogo;
        private TextView mPublicationName;
        private ImageButton mFavoriteButton;

        public PublicationsViewHolder(View itemView) {
            super(itemView);
            mPublicationLogo = (ImageView)itemView.findViewById(R.id.publication_img);
            mPublicationName = (TextView)itemView.findViewById(R.id.publication_name);
            mFavoriteButton = (ImageButton)itemView.findViewById(R.id.favorites_btn);
        }

        public ImageButton getFavoriteButton() {
            return mFavoriteButton;
        }

        public void setPublicationLogo(Drawable publicationLogo) {
            this.mPublicationLogo.setImageDrawable(publicationLogo);
        }

        public void setPublicationName(String publicationName) {
            this.mPublicationName.setText(publicationName);
        }
    }

}
