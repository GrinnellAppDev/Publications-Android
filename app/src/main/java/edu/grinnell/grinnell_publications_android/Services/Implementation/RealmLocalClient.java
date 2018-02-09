package edu.grinnell.grinnell_publications_android.Services.Implementation;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.User;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static edu.grinnell.grinnell_publications_android.Constants.LAST_UPDATED;
import static edu.grinnell.grinnell_publications_android.Constants.LIST_START;
import static edu.grinnell.grinnell_publications_android.Constants.PUBLICATION_ID;
import static edu.grinnell.grinnell_publications_android.Constants.STORY_ID;

/**
 * Implements a local client for on device persistence using Realm.
 *
 * @author Albert Owusu-Asare, Yazan Kittaneh, Jemuel Santos
 * @version 1.1 Fri May  6 12:34:47 CDT 2016
 * @see LocalClientAPI
 * @see io.realm.Realm
 */
public class RealmLocalClient implements LocalClientAPI {
  @Override public boolean isCacheEmpty() {
    return false;
  }

  @Override public boolean isCacheFull() {
    return false;
  }

  @Override public void addToSubscribedPublications(int publicationId) {

  }

  @Override public void savePublications(List<Publication> publications) {
    Realm realm = Realm.getDefaultInstance();
    realm.beginTransaction();

    for (Publication pub : publications) {
      realm.copyToRealmOrUpdate((RealmPublication) pub);
    }

    realm.commitTransaction();
  }

  @Override public void savePublication(Publication publication) {
    Realm realm = Realm.getDefaultInstance();
    realm.beginTransaction();

    /*
    Creates a list of realm publications that match with the user input.
     */
    RealmResults<RealmPublication> pubList = realm.where(RealmPublication.class)
        .equalTo(PUBLICATION_ID, publication.getPublicationId())
        .findAll();

    if (pubList.size() == 0) {
      realm.copyToRealm((RealmPublication) publication);
    }
    realm.commitTransaction();
  }

  @Override public List<Publication> getSubscribedPublications(int id) {
    return null;
  }

  @Override public List<Publication> getAllPublications() {
    Realm realm = Realm.getDefaultInstance();
    RealmResults<RealmPublication> pubResults = realm.where(RealmPublication.class).findAll();
    List<Publication> mResults = new ArrayList<>();
    mResults.addAll(pubResults.subList(0, pubResults.size()));
    return mResults;
  }

  @Override public List<Story> getAllStories(int page, int numStoriesPerPage) {
    Realm realm = Realm.getDefaultInstance();
    RealmResults<RealmStory> mRealmResults = realm.where(RealmStory.class).findAll();
    List<Story> mResults = new ArrayList<>();
    mResults.addAll(mRealmResults.subList(0, mRealmResults.size())); //puts realmResults in a list
    return mResults;
  }

  @Override public List<Story> getRecentStories(List<Integer> subscribedPublicationIds,
      Date mostRecentStory) {
    Realm realm = Realm.getDefaultInstance();
    List<Story> allStories = new ArrayList<>();

    for (Integer currentPublicationId : subscribedPublicationIds) { //for each subscribed publication
      RealmResults<RealmStory> publicationStories = realm.where(RealmStory.class)
          .equalTo(PUBLICATION_ID, currentPublicationId)
          .greaterThan(LAST_UPDATED, mostRecentStory)
          .findAll();
      allStories.addAll(
          publicationStories.subList(0, publicationStories.size())); //puts realmResults in a list
    }
    //TODO: sort all stories by date published
    return allStories;
  }

  @Override public Story getFullStoryById(int storyId) {
    Realm realm = Realm.getDefaultInstance();
    return realm.where(RealmStory.class).equalTo(STORY_ID, storyId).findFirst();
  }

  @Override public List<Story> getAllStoriesInSeries(int publicationId, int seriesId, int page,
      int numStoriesPerPage) {
    return null;
  }

  @Override public List<Story> getRecentStories(int seriesId, Date mostRecentStoryInSeries) {
    if (mostRecentStoryInSeries == null) {
      throw new IllegalArgumentException();
    }
    Realm realm = Realm.getDefaultInstance();
    RealmResults<RealmStory> mRealmResults =
        realm.where(RealmStory.class).greaterThan(LAST_UPDATED, mostRecentStoryInSeries).findAll();
    List<Story> mResults = new ArrayList<>();
    mResults.addAll(
        mRealmResults.subList(LIST_START, mRealmResults.size())); //puts realmResults in a list
    return mResults;
  }

  @Override public List<Story> getBookmarkedStories() {
    return new ArrayList<>();
  }

  @Override public User getProfile() {
    return null;
  }

  @Override public Publication getPublicationById(String publicationId) {
    Realm realm = Realm.getDefaultInstance();
    return realm.where(RealmPublication.class).equalTo(PUBLICATION_ID, publicationId).findFirst();
  }
}
