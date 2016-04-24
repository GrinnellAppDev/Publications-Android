package edu.grinnell.grinnell_publications_android.Views.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.grinnell.grinnell_publications_android.R;

/*
    An activity to display relevant settings and configuration options
 */
public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
