package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

/**
 * {@link Fragment} to display all news in user's Newsfeed.
 * @author Larry Boateng Asante
 */
public class NewsfeedFragment extends Fragment implements UserInterface {
    Button testArticle;


    public NewsfeedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View  newsfeedFragment = inflater.inflate(R.layout.fragment_newfeed, container, false);

        testArticle = (Button) newsfeedFragment.findViewById(R.id.test_article);
        testArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment expandedArticleFragment = ExpandedArticleFragment.newInstance();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .add(R.id.newsfeed_frame, expandedArticleFragment).addToBackStack(null).commit();
            }
        });
        //initializeUI();
        return newsfeedFragment;
    }

    @Override
    public void initializeUI() {


    }


    private void setOnclickListener(){


    }
}
