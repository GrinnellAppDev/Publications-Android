package edu.grinnell.grinnell_publications_android;

import android.support.v7.widget.Toolbar;
import edu.grinnell.grinnell_publications_android.Activities.MainActivity;
import edu.grinnell.grinnell_publications_android.Fragments.NewsfeedFragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static junit.framework.Assert.assertTrue;

/**
 * Created by jrmunchy on 4/28/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class NewsfeedFragmentTest {
  private NewsfeedFragment mNewsfeedFragment;

  /*@Before
  public void setUp() {
    add newInstance() to Fragment
    mNewsfeedFragment = NewsfeedFragment.newInstance();
  }*/

  @Test public void navigationHasCorrectHeading() {

    NewsfeedFragment fragment = new NewsfeedFragment();
    SupportFragmentTestUtil.startFragment(fragment);
    //Toolbar mToolbar = (Toolbar) mNewsfeedFragment.findViewById(R.id.main_toolbar);
    //assertTrue("Check toolbar title",  mToolbar.getTitle().toString().compareTo("Newsfeed") == 0);
  }

  @Test public void testDoesHaveNavigationView() {

  }
}
