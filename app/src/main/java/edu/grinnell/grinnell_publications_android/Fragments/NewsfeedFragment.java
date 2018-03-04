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

        // Fill stories with dummy stories
        List<Story> stories = sGenerator();

        if (stories.size() != 0) {
            ListView gridview = (ListView) newsfeedFragment.findViewById(R.id.newsfeed_listview);
            gridview.setAdapter(new NewsfeedAdapter(getActivity().getApplicationContext(), stories));
        }



        return newsfeedFragment;
    }

    private List<Story> sGenerator() {
        List<Story> stories = new ArrayList<Story>();

        String[] months = {"January", "February", "March", "April",	"May", "June", "July", "August", "September", "October", "November", "December"};
        String[] pseudowords = {"Eum", "feugiat", "accusam", "deseruisse", "cu", "soluta", "libris", "te", "quo", "Iuvaret", "discere", "probatus", "no", "eos", "id", "qui", "malis", "dissentias", "temporibus", "eam", "audire", "civibus", "voluptaria", "ad", "Eum", "no", "molestie", "percipitur", "ei", "quo", "recusabo", "omittantur", "eos", "ancillae", "conclusionemque", "in", "Ut", "ius", "eirmod", "aperiri", "fierent", "paulo", "essent", "est", "at", "ut", "ius", "quem", "meliore", "intellegam", "In", "etiam", "quando", "sed", "facilisi", "suavitate", "cu", "mei", "ex", "viris", "graecis", "duo", "Iisque", "iudicabit", "patrioque", "mel", "ad", "has", "zril", "adipisci", "periculis", "ea", "Decore", "timeam", "vis", "id"};
        int pseudowords_count = 0;
        for (int i = 0; i < 10; i++) {
            String title = "";
            for (int w = 0; w < 8; w++) {
                int size = pseudowords.length;
                title = title + " " + pseudowords[pseudowords_count++ % size];
            }
            String date = "" + months[i] + " " + (i+10);
            stories.add(new RealmStory(date, "Brief", "tnurl", "S&B", date, "AId",
                    "Opeyemi Awe '15 Wins Watson", title,
                    "", new RealmList(new RealmAuthor("Mineta Suzuki", null, null, null))));
        }
        return stories;
    }

    private void refreshContent() {
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
