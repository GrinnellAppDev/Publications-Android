package edu.grinnell.grinnell_publications_android.Controllers.Cards;

import edu.grinnell.grinnell_publications_android.Controllers.RecyclerItem;
import edu.grinnell.grinnell_publications_android.R;

/**
 *
 */
public class LargeArticleCard extends RecyclerItem {

    public static final int LARGE_ARTICLE = R.layout.card_article_large;

    String title;

    public LargeArticleCard() {
        super(LARGE_ARTICLE);
        title = "This is a Large Article";
    }


}
