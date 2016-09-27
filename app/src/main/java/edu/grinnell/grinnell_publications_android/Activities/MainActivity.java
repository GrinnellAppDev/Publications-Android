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
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.grinnell.grinnell_publications_android.Fragments.PublicationsFragment;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;
import edu.grinnell.grinnell_publications_android.Fragments.NewsfeedFragment;
import edu.grinnell.grinnell_publications_android.Fragments.BookmarksFragment;
import edu.grinnell.grinnell_publications_android.Fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity implements UserInterface {
    @Bind(R.id.main_toolbar)Toolbar toolbar;
    @Bind(R.id.navigation_view)NavigationView navigationView;
    @Bind(R.id.drawer_layout)DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeUI();
    }

    /*
        Set up User interface
     */
    @Override
    public void initializeUI() {
        setSupportActionBar(toolbar);

        //Set default fragment to NewsfeedFragment
        NewsfeedFragment newsfeed = new NewsfeedFragment();
        replaceFrameWithFragment(newsfeed);

        //Build navigation drawer
        buildNavDrawer();
    }

    /*
        Build the navigation drawer
     */
    private void buildNavDrawer(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Check if menu item is checked/activated
                if (menuItem.isChecked()){
                    menuItem.setChecked(true);
                }else{
                    menuItem.setChecked(false);
                }

                //Close drawer on menu item click
                drawerLayout.closeDrawers();

                //Toggle to fragment associated with clicked menu item
                switch (menuItem.getItemId()){

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
                        Intent toSettingsActivity = new Intent(getApplicationContext(), MainSettingsActivity.class);
                        startActivity(toSettingsActivity);
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), R.string.transaction_error, Toast.LENGTH_LONG).show();
                        break;
                }

                //Set toolbar title
                setTitle(menuItem.getTitle());
                return true;
            }
        });

        //Initialize drawer toggling
        setUpToolbarToggle();
    }

    /*
        Implement drawer toggling
     */
    private void setUpToolbarToggle() {
        //Initialize drawerToggle
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        //Add the listener to the DrawerLayout
        drawerLayout.addDrawerListener(drawerToggle);

        //Sync state of the toggle indicator with the drawer
        drawerToggle.syncState();
    }


    /*
        Helper method to replace FrameLayout in the activity_main.xml with given Fragment
     */
    private void replaceFrameWithFragment(Fragment fragment){
        //Initialize fragment transaction and replace fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }


}
