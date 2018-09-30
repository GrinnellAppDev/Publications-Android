package edu.grinnell.grinnell_publications_android.Services.Implementation;

import android.app.Activity;

import edu.grinnell.grinnell_publications_android.Activities.MainActivity;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.User;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmPublication;
import edu.grinnell.grinnell_publications_android.Models.Realm.RealmStory;
import edu.grinnell.grinnell_publications_android.Services.Interfaces.LocalClientAPI;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.Sort;

import java.nio.file.FileSystemNotFoundException;
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
 * @author Albert Owusu-Asare, Yazan Kittaneh, Jemuel Santos, Nannan Ji
 * @version 1.1 Fri May  6 12:34:47 CDT 2016
 * @see LocalClientAPI
 * @see io.realm.Realm
 */
public class RealmLocalClient implements LocalClientAPI {

  Realm realm;

  public RealmLocalClient (Activity activity){
    Realm.init(activity.getApplicationContext());
    final RealmConfiguration configuration = new RealmConfiguration.Builder().name("sample.realm").schemaVersion(1).build();
    Realm.setDefaultConfiguration(configuration);
    this.realm = Realm.getDefaultInstance();
  }

  @Override public boolean isCacheEmpty() {
    return false;
  }

  @Override public boolean isCacheFull() {
    return false;
  }

  @Override public void addToSubscribedPublications(int publicationId) {
    
  }

  @Override public void savePublications(List<Publication> publications) {
    realm.beginTransaction();

    for (Publication pub : publications) {
      realm.copyToRealmOrUpdate((RealmPublication) pub);
    }

    realm.commitTransaction();
  }

  @Override public void savePublication(Publication publication) {
    realm.beginTransaction();

    RealmResults<RealmPublication> pubList = realm.where(RealmPublication.class)
        .equalTo(PUBLICATION_ID, publication.getPublicationId())
        .findAll();
    if (pubList.size() == 0) {
      realm.copyToRealm((RealmPublication) publication);
    }
    realm.commitTransaction();
  }

  @Override
  public void saveStories(List<Story> stories) {
    realm.beginTransaction();
    for(Story story : stories) {
      realm.copyToRealmOrUpdate((RealmStory) story);
    }
    realm.commitTransaction();
  }

  @Override
  public void saveFullStory(Story fullStory) {
    realm.beginTransaction();
    fullStory.setFullStory(true);
    realm.copyToRealmOrUpdate((RealmModel) fullStory);
    realm.commitTransaction();
  }

  @Override public List<Publication> getSubscribedPublications(int id) {
    return null;
  }

  @Override public List<Publication> getAllPublications() {
    RealmResults<RealmPublication> pubResults = realm.where(RealmPublication.class).findAll();
    List<Publication> mResults = new ArrayList<>();
    mResults.addAll(pubResults.subList(0, pubResults.size()));
    return mResults;
  }

  @Override public List<Story> getAllStories() {
    RealmResults<RealmStory> mRealmResults = realm.where(RealmStory.class).findAll();
    List<Story> mResults = new ArrayList<>();
    mResults.addAll(mRealmResults.subList(0, mRealmResults.size())); //puts realmResults in a list
    return mResults;
  }

  @Override public List<Story> getRecentStories(List<String> subscribedPublicationIds,
      Date mostRecentStory) {
    List<Story> allStories = new ArrayList<>();

    for (String currentPublication : subscribedPublicationIds) { //for each subscribed publication
      RealmResults<RealmStory> publicationStories = realm.where(RealmStory.class)
          .equalTo(PUBLICATION_ID, currentPublication)
          .greaterThan(LAST_UPDATED, mostRecentStory)
          .sort("mDatePublished", Sort.DESCENDING)
          .findAll();
      allStories.addAll(
          publicationStories.subList(0, publicationStories.size())); //puts realmResults in a list
    }
    return allStories;
  }

  @Override
  public Story getFullStoryById(String storyId) {
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
    RealmResults<RealmStory> mRealmResults =
        realm.where(RealmStory.class).greaterThan(LAST_UPDATED, mostRecentStoryInSeries).findAll();

    List<Story> mResults = new ArrayList<>();
    mResults.addAll(
        mRealmResults.subList(LIST_START, mRealmResults.size())); //puts realmResults in a list
    return mResults;
  }

  @Override public List<Story> getBookmarkedStories() {
    // TODO: 3/11/18 implement
    return null;
  }

  @Override public User getProfile() {
    // TODO: 3/11/18 implement
    return null;
  }

  @Override public Publication getPublicationById(String publicationId) {
    return realm.where(RealmPublication.class).equalTo(PUBLICATION_ID, publicationId).findFirst();
  }
}

