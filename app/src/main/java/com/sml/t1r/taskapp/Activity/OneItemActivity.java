package com.sml.t1r.taskapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.sml.t1r.taskapp.Helpers.ProgressButton;
import com.sml.t1r.taskapp.R;

import java.util.ArrayList;
import java.util.List;

public class OneItemActivity extends AppCompatActivity {
    public static final String EXTRA_ITEMNO = "itemNo";

    private int itemNo;
    //основной массив
    private float[] myListFloat;
    //изменённые
    private List<Integer> lastChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_item);
        //back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //
        Bundle extras = getIntent().getExtras();
        itemNo = (Integer) extras.get(EXTRA_ITEMNO);
        myListFloat = extras.getFloatArray(MainListActivity.EXTRA_FLOATARR);
        lastChanged = extras.getIntegerArrayList(MainListActivity.EXTRA_LASTCHANGE);

        TextView num = (TextView) findViewById(R.id.recycler_item_num);
        num.setText(String.valueOf(itemNo));


        Button button = (Button) findViewById(R.id.recycler_item_button);
        //button.setText(String.valueOf(myListFloat[itemNo]));
        ProgressButton progressButton = (ProgressButton) button;
        progressButton.setPercent(myListFloat[itemNo]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //this.finish();
                Intent intent = new Intent(this, MainListActivity.class);
                intent.putExtra(MainListActivity.EXTRA_FLOATARR, myListFloat);
                intent.putIntegerArrayListExtra(MainListActivity.EXTRA_LASTCHANGE, (ArrayList<Integer>) lastChanged);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
