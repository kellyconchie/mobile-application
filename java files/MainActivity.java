package com.example.testingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to layout
        listView = findViewById(R.id.list_names);

        // Populating data into the ListView
        ArrayList<Section> sections = new ArrayList<>();

        sections.add(new Section(R.drawable.groceries, "Groceries"));
        sections.add(new Section(R.drawable.transport, "Transport"));
        sections.add(new Section(R.drawable.shopping, "Shopping"));
        sections.add(new Section(R.drawable.services, "Services"));
        sections.add(new Section(R.drawable.entertainment, "Entertainment"));
        sections.add(new Section(R.drawable.restaurant, "Restaurants"));
        sections.add(new Section(R.drawable.currency, "Currency Converter"));
        sections.add(new Section(R.drawable.totalspending, "Total Spending"));

        // Creation and use of adapter
        SectionAdapter sectionAdapter = new SectionAdapter(this, R.layout.list_item, sections);
        listView.setAdapter(sectionAdapter);


        // On click listener to move to other activities

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 ) {
                    Intent groceries = new Intent(view.getContext(), Groceries.class);
                    startActivity(groceries);
                }
                  else if (position == 1) {
                    Intent transport = new Intent(view.getContext(), Transport.class);
                    startActivity(transport);
                 }
                  else if (position == 2) {
                      Intent shopping = new Intent(view.getContext(), Shopping.class);
                      startActivity(shopping);
                  }
                  else if (position == 3) {
                      Intent services = new Intent(view.getContext(), Services.class);
                      startActivity(services);

                  }
                  else if (position == 4) {
                      Intent entertainment = new Intent(view.getContext(), Entertainment.class);
                      startActivity(entertainment);

                  }
                  else if (position == 5) {
                      Intent restaurant = new Intent(view.getContext(), Restaurant.class);
                      startActivity(restaurant);
                  }
                  else if (position == 6) {
                      Intent currency = new Intent(view.getContext(), Currency.class);
                      startActivity(currency);
                 }
                  else {
                    Intent total = new Intent(view.getContext(), Total.class);
                    startActivity(total);

                }
            }
        });

    }
}
