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
public class LargeArticleHolder extends RecyclerViewHolder<ArticleCard> {

    @Bind(R.id.tv_article_title)
    TextView title;

    public LargeArticleHolder(View itemView) {
        super(itemView);
    }

    public static LargeArticleHolder(){

    }

    @Override
    public void bindView(ArticleCard articleCard, Activity activity) {
        ButterKnife.bind(activity);

        title.setText(articleCard.title);
    }
}
