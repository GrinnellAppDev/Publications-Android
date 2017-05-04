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

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class MainActivityTest {
  private MainActivity mMainActivity;

  @Before public void setUp() {
    mMainActivity = Robolectric.setupActivity(MainActivity.class);
  }

  @Test
  public void testDoesToolbarHaveTitle() {
    Toolbar toolbar = (Toolbar) mMainActivity.findViewById(R.id.main_toolbar);
    assertTrue("The Toolbar has a title", toolbar.getTitle() != null);
  }

  @Test public void navigationViewClosedOnStartup() {
    DrawerLayout mDrawerLayout = (DrawerLayout) mMainActivity.findViewById(R.id.drawer_layout);
    NavigationView mNavigationView = (NavigationView) mMainActivity.findViewById(R.id.navigation_view);
    assertFalse("Testing if drawer is closed", mDrawerLayout.isDrawerOpen(mNavigationView));
  }

  @Test
  public void doesNavigationViewExist() {
    NavigationView mNavigationView = (NavigationView) mMainActivity.findViewById(R.id.navigation_view);
    assertTrue("Testing if navigation-view exists", mNavigationView.isActivated());
  }

  @Test
  public void doesNavigationDrawerOpenUponClick() {
    DrawerLayout mDrawerLayout = (DrawerLayout) mMainActivity.findViewById(R.id.drawer_layout);
    mDrawerLayout.performClick();
    NavigationView mToggle = (NavigationView) mMainActivity.findViewById(R.id.navigation_view);
    assertTrue("Navigation-view opens when drawer_layout clicked", mDrawerLayout.isDrawerOpen(mToggle));
  }

  @Test
  public void doesNavigationDrawerCloseUponClick() {
    DrawerLayout mDrawerLayout = (DrawerLayout) mMainActivity.findViewById(R.id.drawer_layout);
    mDrawerLayout.performClick();
    mDrawerLayout.performClick();
    NavigationView mToggle = (NavigationView) mMainActivity.findViewById(R.id.navigation_view);
    assertFalse("Navigation-view closes when drawer_layout clicked after it's opened", mDrawerLayout.isDrawerOpen(mToggle));
  }

}
