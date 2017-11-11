package com.startupclubs.scdd16;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SponsorsActivity extends AppCompatActivity {

    List<Event.Sponsor> sponsors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        int eventClicked = getIntent().getIntExtra(Data.EVENT_ITEM_CLICKED_KEY, 0);
        sponsors = Event.getEvent(eventClicked).getSponsors();
        GridView gridView = (GridView) findViewById(R.id.sponsors_gridview);
        ArrayAdapter<Event.Sponsor> adapter = new ArrayAdapter<Event.Sponsor>(this, R.layout.sponsors_list_item, sponsors){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View item = convertView;
                if(item == null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    item = inflater.inflate(R.layout.sponsors_list_item, parent, false);
                }

                TextView title = (TextView) item.findViewById(R.id.sponsor_item_title);
                ImageView photo = (ImageView) item.findViewById(R.id.sponsor_item_photo);
                title.setText(sponsors.get(position).getName());
                photo.setImageBitmap(sponsors.get(position).getLogo());

                return item;
            }
        };
        gridView.setAdapter(adapter);
    }
}
