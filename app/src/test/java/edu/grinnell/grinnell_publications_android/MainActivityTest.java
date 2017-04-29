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
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class MainActivityTest {
  private MainActivity mMainActivity;

  @Before public void setUp() {
    mMainActivity = Robolectric.setupActivity(MainActivity.class);
  }

  @Test public void testDoesToolbarHaveTitle() {
    Toolbar toolbar = (Toolbar) mMainActivity.findViewById(R.id.main_toolbar);
    assertTrue("The Toolbar has a title", toolbar.getTitle() != null);
  }


  @Test public void drawerLayoutClosedOnStartup() {
    DrawerLayout mDrawerLayout = (DrawerLayout) mMainActivity.findViewById(R.id.drawer_layout);
    NavigationView mNavigationView = (NavigationView) mMainActivity.findViewById(R.id.navigation_view);
    assertFalse("Testing if drawer is closed", mDrawerLayout.isDrawerOpen(mNavigationView));
  }
}
