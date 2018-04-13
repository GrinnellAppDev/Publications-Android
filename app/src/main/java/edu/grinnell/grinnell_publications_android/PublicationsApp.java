package edu.grinnell.grinnell_publications_android;

import android.app.Application;
import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * This class initializes Realm once for the whole application.
 *
 * @author Albert Ford
 */

public class PublicationsApp extends Application {

    @SuppressWarnings("FieldCanBeLocal")
    private static long SCHEMA_VERSION = 1;

    /**
     * Initialize realm and set default configuration.
     * @param context Context used to initialize Realm
     */
    public static void initRealm(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .deleteRealmIfMigrationNeeded()
            //.migration(new LocalMigration())
            .build();
        Realm.setDefaultConfiguration(config);
    }

    @Override public void onCreate() {
        super.onCreate();
        initRealm(this);
    }
}
