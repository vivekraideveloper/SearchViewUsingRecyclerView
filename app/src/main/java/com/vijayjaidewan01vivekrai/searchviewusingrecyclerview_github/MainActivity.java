package com.vijayjaidewan01vivekrai.searchviewusingrecyclerview_github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editTextSearch;
    ArrayList<String> names;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = new ArrayList<>();

        names.add("Vivek");
        names.add("Rai");
        names.add("Ricky");
        names.add("Vijay");
        names.add("Radhe");
        names.add("Ram");

        editTextSearch = findViewById(R.id.edit_text);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter(names);
        recyclerView.setAdapter(adapter);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());

            }
        });

    }

    private void filter(String string) {
        ArrayList<String> filterdNames = new ArrayList<>();
        for (String s : names) {
            if (s.toLowerCase().contains(string.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        adapter.filterList(filterdNames);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_food);
        android.widget.SearchView searchView = (android.widget.SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
        
    }
}
