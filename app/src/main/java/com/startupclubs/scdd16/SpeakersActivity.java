package com.startupclubs.scdd16;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SpeakersActivity extends AppCompatActivity {

    List<Event.Speaker> speakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);
        int eventSelected = getIntent().getIntExtra(Data.EVENT_ITEM_CLICKED_KEY, 0);
        speakers = Event.getEvent(eventSelected).getSpeakers();
        TextView title = (TextView) findViewById(R.id.speakers_title);
        title.setText(Event.getEvent(eventSelected).getTitle());

        ArrayAdapter<Event.Speaker> adapter = new ArrayAdapter<Event.Speaker>(this, R.layout.speakers_list_item, speakers) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View item = convertView;
                if(item == null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    item = inflater.inflate(R.layout.speakers_list_item, parent, false);
                }

                TextView title = (TextView) item.findViewById(R.id.speaker_item_title);
                TextView about = (TextView) item.findViewById(R.id.speaker_item_description);
                ImageView photo = (ImageView) item.findViewById(R.id.speaker_item_photo);
                title.setText(speakers.get(position).getName());
                about.setText(speakers.get(position).getAbout());
                photo.setImageBitmap(speakers.get(position).getPhoto());

                return item;
            }
        };
        ListView listView = (ListView) findViewById(R.id.speakers_listview);
        listView.setAdapter(adapter);
    }
}
