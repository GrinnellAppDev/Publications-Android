package edu.grinnell.grinnell_publications_android.Controllers.Cards;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.grinnell.grinnell_publications_android.Controllers.RecyclerViewHolder;
import edu.grinnell.grinnell_publications_android.R;

/**
 */
public class LargeArticleHolder extends RecyclerViewHolder<LargeArticleCard> {

    @Bind(R.id.tv_article_title)
    TextView title;

    public LargeArticleHolder(View itemView) {
        super(itemView);
    }


    @Override
    public void bindView(LargeArticleCard largeArticleCard, Activity activity) {
        ButterKnife.bind(activity);

        title.setText(largeArticleCard.title);
    }
}
