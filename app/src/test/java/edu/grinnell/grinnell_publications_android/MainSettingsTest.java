package edu.grinnell.grinnell_publications_android;

import android.support.v7.widget.Toolbar;
import edu.grinnell.grinnell_publications_android.Activities.MainSettingsActivity;
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

public class MainSettingsTest {

  private MainSettingsActivity mSettingsActivity;

  @Before
  public void setUp() {
      mSettingsActivity = Robolectric.setupActivity(MainSettingsActivity.class);
  }

  @Test
  public void toolbarHasCorrectHeading(){
      Toolbar mToolbar = (Toolbar) mSettingsActivity.findViewById(R.id.settings_toolbar);
      assertTrue("Check toolbar title",  mToolbar.getTitle().toString().compareTo("Settings") == 0);
  }

}