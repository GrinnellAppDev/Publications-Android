package edu.grinnell.grinnell_publications_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.grinnell.grinnell_publications_android.Models.RealmTestObject;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textView)
    TextView textView;

    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Test ButterKnife call
        textView.setText("Hey");

        //Test Realm
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = realm.getInstance(realmConfig);

        realm.beginTransaction();

        RealmTestObject obj = realm.createObject(RealmTestObject.class);
        obj.setName("Google");
        obj.setUrl("www.google.com");
        realm.commitTransaction();

        RealmTestObject obj = realm.createObject(RealmTestObject.class);
        obj.setName("Googleff");
        obj.setUrl("www.gdfm");

    }
}
