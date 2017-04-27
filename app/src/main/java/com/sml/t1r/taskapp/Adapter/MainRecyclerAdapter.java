package com.sml.t1r.taskapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sml.t1r.taskapp.Activity.MainListActivity;
import com.sml.t1r.taskapp.Activity.OneItemActivity;
import com.sml.t1r.taskapp.Helpers.ProgressButton;
import com.sml.t1r.taskapp.R;

import java.util.ArrayList;


public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {

    //основной массив
    private float[] myListFloat;
    //изменённые
    private ArrayList<Integer> lastChanged;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView numRowText;
        private Button btnRow;

        private final Context context;

        public MyViewHolder(View view) {
            super(view);
            context = itemView.getContext();

            view.setOnClickListener(this);

            numRowText = (TextView) view.findViewById(R.id.recycler_item_num);
            btnRow = (Button) view.findViewById(R.id.recycler_item_button);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,OneItemActivity.class);
            intent.putExtra(OneItemActivity.EXTRA_ITEMNO,getAdapterPosition());
            intent.putExtra(MainListActivity.EXTRA_FLOATARR, myListFloat);
            intent.putIntegerArrayListExtra(MainListActivity.EXTRA_LASTCHANGE,(ArrayList<Integer>)lastChanged);
            context.startActivity(intent);
        }
    }

    public MainRecyclerAdapter(float[] myListFloat, ArrayList<Integer> lastChanged) {
        this.myListFloat = myListFloat;
        this.lastChanged = lastChanged;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.numRowText.setText(String.valueOf(position));
        //holder.btnRow.setText(String.valueOf(myListFloat[position]));
        ProgressButton progressButton = (ProgressButton) holder.btnRow;
        progressButton.setPercent(myListFloat[position]);
    }

    @Override
    public int getItemCount() {
        return myListFloat.length;
    }
}
