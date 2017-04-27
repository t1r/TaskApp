package com.sml.t1r.taskapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sml.t1r.taskapp.Adapter.SettingsRecyclerAdapter;
import com.sml.t1r.taskapp.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    private int LAST_CHANGED_SIZE;

    //основной массив
    private float[] myListFloat;
    //изменённые
    private List<Integer> lastChanged;

    private RecyclerView recyclerView;
    private SettingsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LAST_CHANGED_SIZE = getResources().getInteger(R.integer.list_count);

        //back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //
        Bundle extras = getIntent().getExtras();
        myListFloat = extras.getFloatArray(MainListActivity.EXTRA_FLOATARR);
        lastChanged = extras.getIntegerArrayList(MainListActivity.EXTRA_LASTCHANGE);

        //adapter
        recyclerView = (RecyclerView) findViewById(R.id.settings_recycler_view);

        adapter = new SettingsRecyclerAdapter(myListFloat,(ArrayList<Integer>)lastChanged);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void onClickSettingsOk(View view) {
        EditText row = (EditText) findViewById(R.id.settings_row_text);
        EditText percent = (EditText) findViewById(R.id.settings_percent_text);
        if (row.getText().length() == 0) {
            row.setHint(getResources().getString(R.string.settings_row_hint));
            return;
        }
        if (percent.getText().length() == 0) {
            percent.setHint(getResources().getString(R.string.settings_percent_hint));
            return;
        }

        int i = Integer.valueOf(row.getText().toString());
        float value = Float.valueOf(percent.getText().toString());
        int max = getResources().getInteger(R.integer.list_count);
        if (i < 0 || i >= max) {
            row.setHint(getResources().getString(R.string.settings_row_hint));
            return;
        }
        if (value < 0 || value > 1) {
            percent.setHint(getResources().getString(R.string.settings_percent_hint));
            return;
        }
        myListFloat[i] = value;
        addLastChanged(i);

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //this.finish();
                Intent intent = new Intent(this, MainListActivity.class);
                intent.putExtra(MainListActivity.EXTRA_FLOATARR, myListFloat);
                intent.putIntegerArrayListExtra(MainListActivity.EXTRA_LASTCHANGE,(ArrayList<Integer>)lastChanged);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addLastChanged(int num) {
        //если массив не полный
        if (lastChanged.size() < LAST_CHANGED_SIZE) {
            lastChanged.add(0);
        }
        //проверяем есть ли элемент уже в списке
        int exist = lastChanged.indexOf(num);
        if (exist != -1) {
            lastChanged.remove(exist);
        }
        //добавляем в массив со сдвигом
        int tmp;
        for (int i = 0; i < LAST_CHANGED_SIZE && i < lastChanged.size(); i++) {

            tmp = lastChanged.get(i);
            lastChanged.set(i, num);
            num = tmp;
        }
    }

    private void lastChangedInit() {
        lastChanged = new ArrayList<>();
    }
}
