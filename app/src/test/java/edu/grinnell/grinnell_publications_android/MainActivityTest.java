package edu.grinnell.grinnell_publications_android;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import edu.grinnell.grinnell_publications_android.Activities.MainActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class MainActivityTest {
  private MainActivity mMainActivity;

  @Before
  public void setUp() {
      mMainActivity = Robolectric.setupActivity(MainActivity.class);
  }

  @Test
  public void toolbarHasCorrectHeading(){
    Toolbar mToolbar = (Toolbar) mMainActivity.findViewById(R.id.main_toolbar);
    assertTrue("Check toolbar title",  mToolbar.getTitle().toString().compareTo("Newsfeed") == 0);
  }

  @Test
  public void drawerLayoutClosedOnStartup(){
    DrawerLayout mDrawerLayout = (DrawerLayout) mMainActivity.findViewById(R.id.drawer_layout);
      NavigationView mNavigationView = (NavigationView) mMainActivity.findViewById(R.id.navigation_view);
    assertFalse("Testing if drawer is closed", mDrawerLayout.isDrawerOpen(mNavigationView));
  }


}