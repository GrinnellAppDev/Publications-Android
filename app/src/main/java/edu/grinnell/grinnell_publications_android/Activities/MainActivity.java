package edu.grinnell.grinnell_publications_android.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import edu.grinnell.grinnell_publications_android.Fragments.PublicationsFragment;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Fragments.NewsfeedFragment;
import edu.grinnell.grinnell_publications_android.Fragments.BookmarksFragment;
import edu.grinnell.grinnell_publications_android.Fragments.ProfileFragment;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import static android.widget.Toast.makeText;
import static android.widget.Toast.LENGTH_LONG;

/**
 * Represents the {@link AppCompatActivity} that hosts all the various Fragments and the Navigation
 * drawer implemented via the {@link NavigationView}
 * @author Larry Boateng Asante
 */
public class MainActivity extends AppCompatActivity implements UserInterface {

    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        initializeUI();
    }

    @Override
    public void initializeUI() {
        setSupportActionBar(toolbar);

        // We are doing this so we always start the app in the News Feed
        NewsfeedFragment newsfeed = new NewsfeedFragment();
        replaceFrameWithFragment(newsfeed);

        buildNavDrawer();
    }

    private void buildNavDrawer() {
        navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) {
                    menuItem.setChecked(true);
                } else {
                    menuItem.setChecked(false);
                }

                drawerLayout.closeDrawers();

                switchFragments(menuItem);

                setTitle(menuItem.getTitle());
                return true;
            }

            private void switchFragments(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.newsfeed:
                        replaceFrameWithFragment(new NewsfeedFragment());
                        break;
                    case R.id.publications:
                        replaceFrameWithFragment(new PublicationsFragment());
                        break;
                    case R.id.profile:
                        replaceFrameWithFragment(new ProfileFragment());
                        break;
                    case R.id.bookmarks:
                        replaceFrameWithFragment(new BookmarksFragment());
                        break;
                    case R.id.settings:
                        Intent toSettingsActivity =
                                new Intent(getApplicationContext(), MainSettingsActivity.class);
                        startActivity(toSettingsActivity);
                        break;
                    default:
                        makeText(getApplicationContext(), R.string.transaction_error, LENGTH_LONG)
                                .show();
                        break;
                }
            }
        });

        setUpToolbarToggle();
    }

    private void setUpToolbarToggle() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void replaceFrameWithFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        toolbar = null;
        drawerLayout = null;
        navigationView = null;
        super.onDestroy();
    }
}
