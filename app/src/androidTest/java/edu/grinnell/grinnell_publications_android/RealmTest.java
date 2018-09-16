package edu.grinnell.grinnell_publications_android;

import android.app.Application;
import android.support.annotation.NonNull;
import android.test.ApplicationTestCase;
import android.util.Log;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.Services.Implementation.RealmLocalClient;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;


import io.realm.Realm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by jinannan on 3/4/18.
 */

public class RealmTest extends ApplicationTestCase<Application> {

    LocalClientAPI localClient;

    public RealmTest() { super(Application.class); }

    @Before
    public void setUp() {
        Realm.init(getContext());
        localClient = new RealmLocalClient();

    }

    @Test
    public void testAddingPublication(){

        //Test one Publication
        RealmPublication pub;
        pub = new RealmPublication();
        pub.setPublicationId("0");
        pub.setPublicationName("name0");
        localClient.savePublication(pub);

        List<Publication> lst = localClient.getAllPublications();
        assertEquals(lst.size(), 5);
        System.out.println(lst.get(0).getPublicationId());

        //Test multiple Publication
        List<Publication> lstPubs = new ArrayList<Publication>();
        for(int i = 1; i < 5; i ++){
            pub = new RealmPublication();
            pub.setPublicationId(Integer.toString(i));
            pub.setPublicationName("LstPubName"+i);
            lstPubs.add(pub);
       }

       localClient.savePublications(lstPubs);
        assertEquals(localClient.getAllPublications().size(), 5);

        //Test getPublicationById
        Log.i("getPublicationById", localClient.getPublicationById("1").getPublicationName());
        Log.i("getPublicationById", localClient.getPublicationById("2").getPublicationName());
        Log.i("getPublicationById", localClient.getPublicationById("3").getPublicationName());


        //Test Story
        RealmStory story;
        List<Story> stories = new ArrayList<>();


        for (int i = 0; i < 9; i++){
            story = new RealmStory();
            story.setArticleId(Integer.toString(i));
            story.setDateEdited(Long.toString(1524427388*1000));
            story.setPublication("LstPubName1");
            stories.add(story);
        }

        localClient.saveStories(stories);

        assertEquals(localClient.getPublicationById("1").getStories().size(), 0);

        //Test FullStory
        story = new RealmStory();
        story.setArticleId("12");
        story.setDateEdited(Long.toString(1524427388*1000));
        story.setPublication("LstPubName1");
        story.setFullStory(false);
        story.setTitle("FullStory");

        //Test saveFullStory
       localClient.saveFullStory(story);

       //Also test for getFullSotryById
       Log.i("saveFullStory",localClient.getFullStoryById("12").getTitle());
       assertEquals(localClient.getFullStoryById("12").getTitle(),"FullStory" );

       //Test for getRecentStories
        assertEquals(localClient.getRecentStories(0,new Date(316396800*1000)).size(), 10);
       Log.i("getRecentStories", String.valueOf(localClient.getRecentStories(0,new Date(1524427388*1000)).size()));
    }
}
