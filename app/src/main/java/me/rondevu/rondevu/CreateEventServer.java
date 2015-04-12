package me.rondevu.rondevu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class CreateEventServer extends ActionBarActivity {

    private Toolbar toolbar;

    private String eventName, hostName, eventInfo, location, personLimitString, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_server);

        Intent intent = getIntent();

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        category = intent.getStringExtra("categorySelectionFromActivity");
        eventName = intent.getStringExtra("eventName");
        hostName = intent.getStringExtra("hostName");
        eventInfo = intent.getStringExtra("eventInfo");
        location = intent.getStringExtra("location");
        personLimitString = intent.getStringExtra("personLimit");


        int personLimit = Integer.parseInt(personLimitString);

        Event event = new Event(eventName, hostName, location, eventInfo, category, personLimit);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event_server, menu);
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
