package com.sml.t1r.taskapp.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sml.t1r.taskapp.Adapter.MainRecyclerAdapter;
import com.sml.t1r.taskapp.Models.ArrayInit;
import com.sml.t1r.taskapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainListActivity extends AppCompatActivity {
    //основной массив константа
    public static final String EXTRA_FLOATARR = "floatarr";
    //изменённые массив константа
    public static final String EXTRA_LASTCHANGE = "lastchange";

    private RecyclerView recyclerView;
    private MainRecyclerAdapter adapter;

    //основной массив
    private float[] myListFloat;
    //изменённые
    private List<Integer> lastChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        getExtras();
        adapter = new MainRecyclerAdapter(myListFloat,(ArrayList<Integer>)lastChanged);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void getExtras(){
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            myListFloat = extras.getFloatArray(EXTRA_FLOATARR);
            lastChanged = extras.getIntegerArrayList(EXTRA_LASTCHANGE);
        }
        else
        {
            int cnt = getResources().getInteger(R.integer.list_count);
            myListFloat = ArrayInit.getListPrimFloats(cnt);
            lastChanged = new ArrayList<>();
        }
    }

    public void onClickSettings(View v) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra(MainListActivity.EXTRA_FLOATARR, myListFloat);
        intent.putIntegerArrayListExtra(MainListActivity.EXTRA_LASTCHANGE,(ArrayList<Integer>)lastChanged);
        this.startActivity(intent);
    }
}
