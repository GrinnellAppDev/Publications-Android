package edu.grinnell.grinnell_publications_android.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.grinnell.grinnell_publications_android.Fragments.ExpandedArticleFragment;
import edu.grinnell.grinnell_publications_android.R;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String storyTitle = intent.getStringExtra("storyTitle");
    }

    public void initializeUI(View view) {
        // We are doing this so we always start the app in the News Feed
        setContentView(R.layout.activity_article);

        ExpandedArticleFragment newsfeed = new ExpandedArticleFragment();
        replaceFrameWithFragment(newsfeed);
    }

    private void replaceFrameWithFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

}
