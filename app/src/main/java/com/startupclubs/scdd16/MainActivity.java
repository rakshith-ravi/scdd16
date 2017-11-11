package com.startupclubs.scdd16;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, R.layout.event_list_item, Event.getEvents()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView != null) {
                    return convertView;
                } else {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View item =  inflater.inflate(R.layout.event_list_item, parent, false);

                    TextView year = (TextView) item.findViewById(R.id.events_item_year);
                    TextView date = (TextView) item.findViewById(R.id.events_item_date);
                    TextView day = (TextView) item.findViewById(R.id.events_item_day);
                    TextView title = (TextView) item.findViewById(R.id.events_item_title);
                    TextView city = (TextView) item.findViewById(R.id.events_item_city);

                    year.setText(Event.getEvent(position).getYear());
                    date.setText(Event.getEvent(position).getDate());
                    day.setText(Event.getEvent(position).getDay());
                    title.setText(Event.getEvent(position).getTitle());
                    city.setText(Event.getEvent(position).getCity());
                    return item;
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.list_of_events);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EventDescriptionActivity.class);
                intent.putExtra(Data.EVENT_ITEM_CLICKED_KEY, position);
                startActivity(intent);
            }
        });
    }
}
