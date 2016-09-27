package edu.grinnell.grinnell_publications_android.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
import edu.grinnell.grinnell_publications_android.R;

/**
 * {@link AppCompatActivity} to display relevant settings and configuration options
 * @author Larry Boateng Asante
 */
public class MainSettingsActivity extends AppCompatActivity implements UserInterface {

    @Bind(R.id.settings_toolbar)Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initializeUI();
    }

    @Override
    public void initializeUI() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
