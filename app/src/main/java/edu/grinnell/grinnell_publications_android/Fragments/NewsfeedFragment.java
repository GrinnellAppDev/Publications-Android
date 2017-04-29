package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Adapters.NewsfeedAdapter;
import edu.grinnell.grinnell_publications_android.Adapters.PublicationAdapter;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Implementation.RealmLocalClient;

/**
 * {@link Fragment} to display all news in user's Newsfeed.
 * @author Larry Boateng Asante
 */
public class NewsfeedFragment extends Fragment implements UserInterface {

    public NewsfeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View  newsfeedFragment = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        initializeUI(newsfeedFragment);

        // Getting stories from Realm
        int seriesId = 0;
        Date date = new Date();
        RealmLocalClient rlc = new RealmLocalClient();
        List<Story> stories = rlc.getRecentStories(seriesId, date);

        ListView gridview = (ListView) newsfeedFragment.findViewById(R.id.newsfeed_listview);
        gridview.setAdapter(new NewsfeedAdapter(getActivity().getApplicationContext(), stories));

        return newsfeedFragment;
    }

    @Override
    public void initializeUI(View view) {
    }


}
