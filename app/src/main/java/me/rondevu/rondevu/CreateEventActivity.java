package me.rondevu.rondevu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class CreateEventActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private Spinner spinner;

    private String categorySelection;

    private EditText eventName, hostName, eventInfo, location, personLimit;


    private Button button, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        Intent intent = getIntent();

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        eventName = (EditText) findViewById(R.id.eventNameEditText);
        hostName = (EditText) findViewById(R.id.hostNameEditText);
        eventInfo = (EditText) findViewById(R.id.eventInfoEditText);
        location = (EditText) findViewById(R.id.locationEditText);
        personLimit = (EditText) findViewById(R.id.personLimitEditText);


        eventName.addTextChangedListener(new TextValidator(eventName) {
            @Override
            public void validate(TextView textView, String text) {
                if (eventName.equals("") || eventName == null) {
                    eventName.setError("Please enter a name for this event");
                }
            }
        });

        hostName.addTextChangedListener(new TextValidator(hostName) {
            @Override
            public void validate(TextView textView, String text) {
                if (hostName.equals("") || hostName == null) {
                    eventName.setError("Please enter a name for the organization or person hosting!");
                }
            }
        });



        personLimit.addTextChangedListener(new TextValidator(personLimit) {
            @Override
            public void validate(TextView textView, String text) {
                if (personLimit.getText().toString().length() < 5) {
                    if (personLimit.getText().toString().equals("") ||
                            personLimit.getText().toString().startsWith("0") || Integer.parseInt(personLimit.getText().toString()) == 0) {

                        personLimit.setError("Please enter a value that is greater than 0");
                    }
                } else {
                    personLimit.setError("Please enter a valid number");
                }
            }
        });

        spinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        button = (Button) findViewById(R.id.finalizeEventButton);
        cancelButton = (Button) findViewById(R.id.cancelCreateEventButton);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        categorySelection = (String) parent.getItemAtPosition(pos);

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
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

    /**
     * Communicates with server to create event and store online
     */
    public void createEvent(View view) {
        Intent intent = new Intent(this, CreateEventServer.class);


        if (eventName.getText().toString().equals("") || eventName.getText().toString() == null) {
            eventName.setText("Unknown Event");
        }

        if (hostName.getText().toString().equals("") || hostName.getText().toString() == null) {
            hostName.setText("a mystery person");
        }

        if (eventInfo.getText().toString().equals("") || eventInfo.getText().toString() == null) {
            eventInfo.setText("No description available");
        }

        if (location.getText().toString().equals("") || location.getText().toString() == null) {
            location.setText("a mystery location!");
        }

        if (personLimit.getText().toString().equals("") || personLimit.getText().toString() == null) {
            personLimit.setText("1");
        }

        intent.putExtra("eventName", eventName.getText().toString());
        intent.putExtra("hostName", hostName.getText().toString());
        intent.putExtra("eventInfo", eventInfo.getText().toString());
        intent.putExtra("location", location.getText().toString());
        intent.putExtra("categorySelectionFromActivity", categorySelection);
        intent.putExtra("personLimit", personLimit.getText().toString());


        startActivity(intent);
    }

    public void cancelEvent(View view) {
        finish();
    }
}
