package com.startupclubs.scdd16;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AgendaActivity extends AppCompatActivity {

    List<Event.Agenda> agendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        int itemClicked = getIntent().getIntExtra(Data.EVENT_ITEM_CLICKED_KEY, 0);
        agendas = Event.getEvent(itemClicked).getAgendas();
        TextView title = (TextView) findViewById(R.id.agenda_title);
        title.setText(Event.getEvent(itemClicked).getTitle());

        ArrayAdapter<Event.Agenda> adapter = new ArrayAdapter<Event.Agenda>(this, R.layout.agenda_list_item, agendas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View item = convertView;
                if(item == null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    item = inflater.inflate(R.layout.agenda_list_item, parent, false);
                }
                if(position == 0) {
                    item.findViewById(R.id.agenda_connector_top).setVisibility(View.INVISIBLE);
                    item.findViewById(R.id.agenda_connector_bottom).setVisibility(View.VISIBLE);
                } else if(position == agendas.size() - 1) {
                    item.findViewById(R.id.agenda_connector_top).setVisibility(View.VISIBLE);
                    item.findViewById(R.id.agenda_connector_bottom).setVisibility(View.INVISIBLE);
                } else {
                    item.findViewById(R.id.agenda_connector_top).setVisibility(View.VISIBLE);
                    item.findViewById(R.id.agenda_connector_bottom).setVisibility(View.VISIBLE);
                }

                TextView timing = (TextView) item.findViewById(R.id.agenda_item_timing);
                TextView title = (TextView) item.findViewById(R.id.agenda_item_title);
                timing.setText(agendas.get(position).getTiming());
                title.setText(agendas.get(position).getTitle());

                return item;
            }
        };
        ListView listView = (ListView) findViewById(R.id.agenda_list_of_agendas);
        listView.setAdapter(adapter);
    }
}
