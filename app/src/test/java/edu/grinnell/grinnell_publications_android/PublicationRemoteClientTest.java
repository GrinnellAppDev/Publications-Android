package edu.grinnell.grinnell_publications_android;
import org.junit.Test;
import org.mockito.Mockito;

import edu.grinnell.grinnell_publications_android.Models.Realm.RealmAuthor;
import edu.grinnell.grinnell_publications_android.Services.Templates.JsonStory;
import io.realm.RealmList;

import static org.mockito.Mockito.when;

/**
 * Created by Cx831 on 4/7/2017.
 */

public class PublicationRemoteClientTest {


    @Test
    public void testConvertJsonToRealmStory () {
        // Create Mock


        // Create mock realm author list
        RealmList<RealmAuthor> realmAuthorsTest = Mockito.mock(RealmList.class);

        // Create mock json story for test conversion
        JsonStory jsonStoryTest = Mockito.mock(JsonStory.class);
        when(jsonStoryTest.getDatePublished()).thenReturn("");
        when(jsonStoryTest.getBrief()).thenReturn("");
        when(jsonStoryTest.getHeaderImage()).thenReturn("");
        when(jsonStoryTest.getPublication()).thenReturn("");
        when(jsonStoryTest.getDateEdited()).thenReturn("");
        when(jsonStoryTest.getId()).thenReturn("");
        when(jsonStoryTest.getTitle()).thenReturn("");
        when(jsonStoryTest.getContent()).thenReturn("");
        when(jsonStoryTest.getAuthors()).thenReturn(null);





    }


}
