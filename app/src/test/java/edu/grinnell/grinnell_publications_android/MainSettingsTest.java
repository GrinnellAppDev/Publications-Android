package edu.grinnell.grinnell_publications_android;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
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
  public void testDoesToolbarExist() {
    Toolbar toolbar = (Toolbar) mSettingsActivity.findViewById(R.id.settings_toolbar);
    assertTrue("The Settings toolbar is named correctly", toolbar.getTitle().toString().compareTo("0") == 0);
  }


}