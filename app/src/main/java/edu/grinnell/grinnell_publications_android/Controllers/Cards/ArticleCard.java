package edu.grinnell.grinnell_publications_android.Controllers.Cards;

import edu.grinnell.grinnell_publications_android.Controllers.RecyclerItem;
import edu.grinnell.grinnell_publications_android.R;

/**
 * Created by prabir on 4/29/16, AppDev Grinnell.
 */
public class ArticleCard extends RecyclerItem {

    String title;

    public ArticleCard() {
        super(R.layout.card_article_large);
        title = "This is a Large Article";
    }


}
