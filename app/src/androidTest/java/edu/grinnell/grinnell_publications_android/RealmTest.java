package edu.grinnell.grinnell_publications_android;

import android.app.Application;
import android.test.ApplicationTestCase;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Services.Implementation.RealmLocalClient;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;


import io.realm.Realm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

//        Realm realm = Realm.getDefaultInstance();
//        try {
//            realm.close();
//            Realm.deleteRealm(realm.getConfiguration());
//            //Realm file has been deleted.
//        } catch (Exception ex){
//            ex.printStackTrace();
//            //No Realm file to remove.
//        }

        //Test one
        RealmPublication pub;
        pub = new RealmPublication();
        pub.setPublicationId("0");
        pub.setPublicationName("name0");
        localClient.savePublication(pub);

        List<Publication> lst = localClient.getAllPublications();
        assertEquals(lst.size(), 5);
        System.out.println(lst.get(0).getPublicationId());

        //Test multiple
        List<Publication> lstPubs = new ArrayList<Publication>();
        for(int i = 0; i < 5; i ++){
            pub = new RealmPublication();
            pub.setPublicationId(Integer.toString(i));
            pub.setPublicationName("LstPubName"+i);
            lstPubs.add(pub);
       }

       localClient.savePublications(lstPubs);
        assertEquals(localClient.getAllPublications().size(), 5);


    }


}
