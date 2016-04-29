package edu.grinnell.grinnell_publications_android.Controllers.Cards;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.grinnell.grinnell_publications_android.Controllers.RecyclerViewHolder;
import edu.grinnell.grinnell_publications_android.R;

/**
 *
 */
public class SmallArticleHolder extends RecyclerViewHolder<ArticleCard> {
    @Bind(R.id.tv_article_title)
    TextView title;

    public SmallArticleHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(ArticleCard articleCard, Activity activity) {
        ButterKnife.bind(activity);

        title.setText(articleCard.title);
    }
}