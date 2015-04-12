package me.rondevu.rondevu;

import android.content.Intent;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.net.NetworkInfo.State.CONNECTED;
import static android.net.NetworkInfo.State.DISCONNECTED;


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

        createEventButton = (Button) findViewById(R.id.host);

        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ea = new EventAdapter(createList(0));
        recList.setAdapter(ea);

        testAdd();
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
            ea.notifyDataSetChanged();
            llm.setSmoothScrollbarEnabled(true);
            llm.scrollToPosition(0);

            new RequestTask().execute("http://stackoverflow.com");

        }

        return super.onOptionsItemSelected(item);
    }

    public void createEvent(View view) {
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }


    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..

        }
    }



    private List<Event> createList(int size) {

        List<Event> result = new ArrayList<Event>();
        for (int i=1; i <= size; i++) {

        }
        return result;
    }
}
