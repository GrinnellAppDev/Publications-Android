package edu.grinnell.grinnell_publications_android.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import edu.grinnell.grinnell_publications_android.Fragments.ExpandedArticleFragment;
import edu.grinnell.grinnell_publications_android.R;

/**
 * Created by jrmunchy on 3/1/18.
 */

public class ExpandedArticleActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_expanded_article);
    FrameLayout frame = findViewById(R.id.expanded_article_frame);

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    ExpandedArticleFragment fragment = new ExpandedArticleFragment();
    fragmentTransaction.add(R.id.expanded_article_frame, fragment);
    fragmentTransaction.commit();

  }
}