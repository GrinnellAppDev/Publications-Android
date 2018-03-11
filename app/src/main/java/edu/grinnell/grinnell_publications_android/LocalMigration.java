package edu.grinnell.grinnell_publications_android;

import android.content.Context;
import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * This class handles realm migrations for when the schema changes.
 *
 * @see PublicationsActivity#initRealm(Context)
 *
 * @author Albert Ford
 */

public class LocalMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        // Put migrations here
    }
}
