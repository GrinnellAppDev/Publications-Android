package edu.grinnell.grinnell_publications_android.Views;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.grinnell.grinnell_publications_android.R;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)Toolbar toolbar;
    @Bind(R.id.navigation_view)NavigationView navigationView;
    @Bind(R.id.drawer_layout)DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

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
                    case R.id.all_publications:

                }
            }
        });
    }

    private void initializeUI(){
        setSupportActionBar(toolbar);

    }

}
