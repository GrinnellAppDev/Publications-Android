package edu.grinnell.grinnell_publications_android.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Adapters.NewsfeedAdapter;
import edu.grinnell.grinnell_publications_android.Adapters.PublicationAdapter;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthor;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Services.Implementation.PublicationsRemoteClient;
import edu.grinnell.grinnell_publications_android.Services.Implementation.RealmLocalClient;
import io.realm.RealmList;

/**
 * {@link Fragment} to display all news in user's Newsfeed.
 * @author Larry Boateng Asante
 */
public class NewsfeedFragment extends Fragment implements UserInterface {

    SwipeRefreshLayout mSwipeRefreshLayout;
    RealmLocalClient mRealmClient = new RealmLocalClient();

    public NewsfeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View  newsfeedFragment = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        initializeUI(newsfeedFragment);

        /*
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
*/
        /*

         */
        // create dummy stories

        List<Story> stories = getStoriesFromRealm();

        ListView gridview = (ListView) newsfeedFragment.findViewById(R.id.newsfeed_listview);
        gridview.setAdapter(new NewsfeedAdapter(getActivity().getApplicationContext(), stories));

        return newsfeedFragment;
    }

    private List<Story> sGenerator() {
        List<Story> stories = new ArrayList<Story>();
        stories.add(new RealmStory("March 7", "Brief", "tnurl", "S&B", "March 7", "AId",
                "Opeyemi Awe '15 Wins Watson", "Content",
                "", new RealmList(new RealmAuthor("Mineta Suzuki", null, null, null))));
        return stories;
    }

    private void refreshContent() {
        PublicationsRemoteClient prc = new PublicationsRemoteClient(mRealmClient);
        // TODO: Get Stories through PRC

    }

    public List<Story> getStoriesFromRealm () {
        // Getting stories from Realm; mRealmClient.getRecentStories(new Date());
        return null;
    }
    @Override
    public void initializeUI(View view) {
    }


}
