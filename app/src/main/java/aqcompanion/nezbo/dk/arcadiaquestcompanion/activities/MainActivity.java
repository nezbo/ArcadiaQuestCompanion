package aqcompanion.nezbo.dk.arcadiaquestcompanion.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import aqcompanion.nezbo.dk.arcadiaquestcompanion.R;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.adapters.CampaignAdapter;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.database.Database;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.model.Campaign;

public class MainActivity extends AppCompatActivity {

    private Database database;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.saveCampaign(new Campaign(0, -1, "Arcadia Quest", null, null, false));
                list.setAdapter(new CampaignAdapter(database.getCampaigns(), MainActivity.this));
//                Snackbar.make(view, title, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        init();
    }

    private void init() {
        this.database = new Database(this);
        this.database.open();

        list = (ListView) findViewById(R.id.lvMainCampaigns);
        list.setAdapter(new CampaignAdapter(database.getCampaigns(), this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
