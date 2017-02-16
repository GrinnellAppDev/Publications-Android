package edu.grinnell.grinnell_publications_android.Services.Implementation;

import java.util.Date;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.User;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Implements a local client for on device persistence using Realm.
 *
 * @author Albert Owusu-Asare.
 * @version 1.1 Fri May  6 12:34:47 CDT 2016
 * @see LocalClientAPI
 * @see io.realm.Realm
 */
public class RealmLocalClient implements LocalClientAPI {
    @Override
    public boolean isCacheEmpty() {
        return false;
    }

    @Override
    public boolean isCacheFull() {
        return false;
    }

    @Override
    public void addToSubscribedPublications(int publicationId) {

    }

    @Override
    public void savePublications(List<Publication> publications) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        for(int i = 0; i < publications.size(); i++) {
            realm.copyToRealmOrUpdate((RealmPublication)publications.get(i));
        }
        realm.commitTransaction();
    }

    @Override
    public void savePublication(Publication publication) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmResults<RealmPublication> pubList = realm.where(RealmPublication.class)
                .equalTo("PublicationId", publication.getPublicationId())
                .findAll();

        if(pubList.size() == 0) {
            realm.copyToRealm((RealmPublication) publication);
        }
        realm.commitTransaction();
    }

    @Override
    public List<Publication> getSubscribedPublications(int id) {
        return null;
    }

    @Override
    public List<Publication> getAllPublications() {
        return null;
    }

    @Override
    public List<Story> getAllStories(int page, int numStoriesPerPage) {
        return null;
    }

    @Override
    public List<Story> getRecentStories(List<Integer> subscribedPublicationIds, Date mostRecentStory) {
        return null;
    }

    @Override
    public Story getFullStoryById(int storyId) {
        return null;
    }

    @Override
    public List<Story> getAllStoriesInSeries(int publicationId, int seriesId, int page, int numStoriesPerPage) {
        return null;
    }

    @Override
    public List<Story> getRecentStories(int seriesId, Date mostRecentStoryInSeries) {
        return null;
    }

    @Override
    public List<Story> getBookmarkedStories() {
        return null;
    }

    @Override
    public User getProfile() {
        return null;
    }
}
