package edu.grinnell.grinnell_publications_android;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import edu.grinnell.grinnell_publications_android.Services.Implementation.PublicationsNetworkClient;
import edu.grinnell.grinnell_publications_android.Services.Implementation.RealmLocalClient;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.NetworkClientAPI;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.OnNetworkCallCompleteListener;
import io.realm.Realm;

/**
 * Created by prabirmsp on 4/29/18.
 */

public class RetrofitTest  extends ApplicationTestCase<Application> {

    private static final String TAG = "test";
    NetworkClientAPI networkClient;
    LocalClientAPI localClient;

    public RetrofitTest() {
        super(Application.class);
    }

    @Before
    public void setUp() {
        Realm.init(getContext());
        localClient = new RealmLocalClient();
        networkClient = new PublicationsNetworkClient(localClient, new OnNetworkCallCompleteListener() {
            @Override
            public void onNetworkCallSucceeded() {
                Log.d(TAG, "onNetworkCallSucceeded: SUCCESS");
            }

            @Override
            public void onNetworkCallFailed(String message) {
                Log.d(TAG, "onNetworkCallSucceeded: FAIL");

            }
        });
    }

    @Test
    public void testPub() {
        networkClient.getAllPublications();
    }
}