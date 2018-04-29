package edu.grinnell.grinnell_publications_android;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by prabirmsp on 4/29/18.
 */

public class PublicationsApplication extends Application{
    @Override
    public void onCreate() {

        // Initialize realm when app is created
        Realm.init(this);

        super.onCreate();


    }
}
