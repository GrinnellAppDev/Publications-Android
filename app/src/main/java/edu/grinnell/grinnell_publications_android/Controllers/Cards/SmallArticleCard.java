package edu.grinnell.grinnell_publications_android.Controllers.Cards;

import edu.grinnell.grinnell_publications_android.Controllers.RecyclerItem;
import edu.grinnell.grinnell_publications_android.R;

/**
 *
 */
public class SmallArticleCard extends RecyclerItem {

    public static final int SMALL_ARTICLE = R.layout.card_article_small;
    String title;

    public SmallArticleCard() {
        super(SMALL_ARTICLE);
        title = "This is a Small Article";
    }

}
