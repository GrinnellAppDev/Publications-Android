package edu.grinnell.grinnell_publications_android.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.User;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Implements a local client for on device persistence using Realm.
 *
 * @author Albert Owusu-Asare, Yazan Kittaneh, Jemuel Santos
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

    }

    @Override
    public void savePublication(Publication publication) {

    }

    @Override
    public List<Publication> getSubscribedPublications(int id) {
        return null;
    }

    @Override
    public List<Publication> getAllPublications() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmPublication> pubResults = realm.where(RealmPublication.class).findAll();
        List<Publication> mResults = new ArrayList<>();
        mResults.addAll(pubResults.subList(0, pubResults.size()));
        return mResults;
    }

    @Override
    public List<Story> getAllStories(int page, int numStoriesPerPage) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmStory> mRealmResults= realm.where(RealmStory.class).findAll();
        List<Story> mResults = new ArrayList<>();
        mResults.addAll(mRealmResults.subList(0, mRealmResults.size())); //puts realmResults in a list
        return mResults;
    }

    @Override
    public List<Story> getRecentStories(List<Integer> subscribedPublicationIds, Date mostRecentStory) {
        Realm realm = Realm.getDefaultInstance();
        List<Story> allStories = new ArrayList<>();

        for (Integer currentPublicationId: subscribedPublicationIds) { //for each subscribed publication
            RealmResults<RealmStory> publicationStories = realm
                    .where(RealmStory.class)
                    .equalTo("publicationId", currentPublicationId)
                    .greaterThan("lastUpdated", mostRecentStory)
                    .findAll();
            allStories.addAll(publicationStories.subList(0, publicationStories.size())); //puts realmResults in a list
        }
        //TODO: sort all stories by date published
        return allStories;
    }

    @Override
    public Story getFullStoryById(int storyId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RealmStory.class).equalTo("storyId", storyId).findFirst();
    }

    @Override
    public List<Story> getAllStoriesInSeries(int publicationId, int seriesId, int page, int numStoriesPerPage) {
        return null;
    }

    @Override
    public List<Story> getRecentStories(int seriesId, Date mostRecentStoryInSeries) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmStory> mRealmResults= realm.where(RealmStory.class)
                .greaterThan("lastUpdated", mostRecentStoryInSeries)
                .findAll();
        List<Story> mResults = new ArrayList<>();
        mResults.addAll(mRealmResults.subList(0, mRealmResults.size())); //puts realmResults in a list
        return mResults;
    }

    @Override
    public List<Story> getBookmarkedStories() {
        return new ArrayList<>();
    }

    @Override
    public User getProfile() {
        return null;
    }

    @Override
    public Publication getPublicationById(String publicationId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RealmPublication.class)
                .equalTo("publicationId", publicationId)
                .findFirst();
    }
}
