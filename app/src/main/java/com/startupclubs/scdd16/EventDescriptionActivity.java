package com.startupclubs.scdd16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class EventDescriptionActivity extends AppCompatActivity {

    private int eventClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        eventClicked = getIntent().getIntExtra(Data.EVENT_ITEM_CLICKED_KEY, 0);
        TextView title = (TextView) findViewById(R.id.event_description_title);
        title.setText(Event.getEvent(eventClicked).getTitle());
    }

    public void agendaClicked(View view) {
        Intent intent = new Intent(this, AgendaActivity.class);
        intent.putExtra(Data.EVENT_ITEM_CLICKED_KEY, eventClicked);
        startActivity(intent);
    }

    public void venueClicked(View view) {

    }

    public void speakersClicked(View view) {
        Intent intent = new Intent(this, SpeakersActivity.class);
        intent.putExtra(Data.EVENT_ITEM_CLICKED_KEY, eventClicked);
        startActivity(intent);
    }

    public void stallsClicked(View view) {

    }

    public void sponsorsClicked(View view) {
        Intent intent = new Intent(this, SponsorsActivity.class);
        intent.putExtra(Data.EVENT_ITEM_CLICKED_KEY, eventClicked);
        startActivity(intent);
    }
}
