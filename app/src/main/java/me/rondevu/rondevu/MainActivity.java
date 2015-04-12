package me.rondevu.rondevu;

import android.content.Intent;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private Button createEventButton;
    private RecyclerView recList;
    private EventAdapter ea;
    private LinearLayoutManager llm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        createEventButton = (Button) findViewById(R.id.host);

        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ea = new EventAdapter(createList(0));
        recList.setAdapter(ea);


        recList.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Event event = ea.getEvent(position);

                        Intent intent = new Intent(getApplication(), EventInformationActivity.class);
                        intent.putExtra("eventName", event.getEventName());
                        intent.putExtra("hostName", event.getHost());
                        intent.putExtra("info", event.getInfo());
                        intent.putExtra("location", event.getLocation());
                        intent.putExtra("category", event.getCategory());

                        startActivity(intent);
                    }
                }));
        testAdd();


        new RetrieveFeedTask().execute();

    }


    public void testAdd() {
        Event test = new Event("Bitcamp", "University of Maryland", "College Park", "HACK AWAY", "EVENT", 10);
        Event concert = new Event("VIDEO GAMES LIVE", "Marching Virginians", "Virginia Tech", "Listen!", "CONCERT", 9999);
        Event festival = new Event("Fireworks", "UMD SGA", "UMD Parking lot", "fireworks are awesome!", "FESTIVAL", 9999);
        Event party = new Event("PROJECT X", "Super awesome frat", "some address", "come hang out with us frat guys", "PARTY", 9999);
        Event sports = new Event("VT VS UMD", "VT", "LANE STADIUM", "Come watch the VT vs UMD game!", "SPORTS", 9999);
        Event other = new Event("Some bland event", "bland person", "bland venue", "bland description", "OTHER", 9999);
        ea.getList().add(test);
        ea.getList().add(concert);
        ea.getList().add(festival);
        ea.getList().add(party);
        ea.getList().add(sports);
        ea.getList().add(other);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Event other = new Event("Some bland event", "bland person", "bland venue", "bland description", "OTHER", 9999);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_refresh) {
            ea.getList().add(other);

            new RetrieveFeedTask().execute();

            ea.notifyDataSetChanged();
            llm.setSmoothScrollbarEnabled(true);
            llm.scrollToPosition(0);

        }

        return super.onOptionsItemSelected(item);
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, Event> {
        protected Event doInBackground(Void... arg0) {
            try {
                refreshFeed();
            } catch (JSONException e) {
                Log.d("me.rondevu.rondevu", "Exception caught in async");
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void result) {

        }
    }


    public void createEvent(View view) {
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }


    public void refreshFeed() throws JSONException {
        Log.d("me.rondevu.rondevu", "refreshing feed");

        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        //HttpPost httppost = new HttpPost("http://api.androidhive.info/contacts/");
        // URI myuri = new URI("http://default-environment-t33qajk2jx.elasticbeanstalk.com/api/events");
        Uri myuri = Uri.parse("http://default-environment-t33qajk2jx.elasticbeanstalk.com/api/events");
        HttpGet httppost = new HttpGet(String.valueOf(myuri));
        Log.d("me.rondevu.rondevu", "Website " + httppost.getURI().toString());
// Depends on your web service
        httppost.setHeader("Content-type", "application/json");

        InputStream inputStream = null;
        String result = null;
        try {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            inputStream = entity.getContent();
            // json is UTF-8 by default
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();

            JSONObject jObject = new JSONObject(result);

            Log.d("me.rondevu.rondevu", jObject.toString());

            JSONArray jArray = jObject.getJSONArray("events");


            for (int i = 0; i < jArray.length(); i++) {
                try {

                    

                    JSONObject oneObject = jArray.getJSONObject(i);
                    // Pulling items from the array
                    String oneObjectsItem = oneObject.getString("title");
                    String host = oneObject.getString("host");
                    String location = oneObject.getString("location");
                    String info = oneObject.getString("description");
                    int capacity = oneObject.getInt("capacity");
                    String category = oneObject.getString("category");

                    Event event = new Event(oneObjectsItem, host, location, info, category, capacity);

                    Log.d("me.rondevu.rondevu", event.toString());

                    ea.getList().add(event);
                    ea.notifyDataSetChanged();
                    llm.setSmoothScrollbarEnabled(true);
                    llm.scrollToPosition(0);

                    Log.d("me.rondevu.rondevu", oneObjectsItem);
                } catch (JSONException e) {
                    // Oops
                }
            }


            Log.d("me.rondevu.rondevu", result + "WE'VE MADE THE TRY!!!");
            // Log.d("me.rondevu.rondevu", aString);

        } catch (Exception e) {
            Log.d("me.rondevu.rondevu", "EXCEPTION CAUGHT HERE");
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception squish) {
            }
        }
    }

    private List<Event> createList(int size) {

        List<Event> result = new ArrayList<Event>();
        for (int i = 1; i <= size; i++) {

        }
        return result;
    }

}
