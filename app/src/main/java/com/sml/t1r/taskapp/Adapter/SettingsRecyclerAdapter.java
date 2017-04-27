package com.sml.t1r.taskapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sml.t1r.taskapp.Helpers.ProgressButton;
import com.sml.t1r.taskapp.R;

import java.util.ArrayList;

/**
 * Created by t1r on 27.04.2017.
 */

public class SettingsRecyclerAdapter extends RecyclerView.Adapter<SettingsRecyclerAdapter.MyViewHolder> {
    //основной массив
    private float[] myListFloat;
    //изменённые
    private ArrayList<Integer> lastChanged;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView numRowText;
        private Button btnRow;

        private final Context context;

        public MyViewHolder(View view) {
            super(view);
            context = itemView.getContext();

            numRowText = (TextView) view.findViewById(R.id.recycler_item_num);
            btnRow = (Button) view.findViewById(R.id.recycler_item_button);
        }
    }

    public SettingsRecyclerAdapter(float[] myListFloat, ArrayList<Integer> lastChanged) {
        this.myListFloat = myListFloat;
        this.lastChanged = lastChanged;
    }

    public void makeLastChangedArray(){

    }

    @Override
    public SettingsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        SettingsRecyclerAdapter.MyViewHolder vh = new SettingsRecyclerAdapter.MyViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(SettingsRecyclerAdapter.MyViewHolder holder, int position) {
        if (lastChanged.size() <= 0)
            return;

        int index  = lastChanged.get(position);

        holder.numRowText.setText(String.valueOf(index));
        //holder.btnRow.setText(String.valueOf(myListFloat[index]));
        ProgressButton progressButton = (ProgressButton) holder.btnRow;
        progressButton.setPercent(myListFloat[index]);
    }

    @Override
    public int getItemCount() {
        return lastChanged.size();
    }
}
