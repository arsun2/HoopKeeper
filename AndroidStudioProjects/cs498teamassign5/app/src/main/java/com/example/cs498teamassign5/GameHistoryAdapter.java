package com.example.cs498teamassign5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.MyViewHolder> {
    private ArrayList<GameInfo> gameInfos;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public Context context;
        public MyViewHolder(TextView v) {
            super(v);
            this.context = v.getContext();
            textView = v;
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public GameHistoryAdapter(ArrayList<GameInfo> gameInfos, Context context) {
        this.gameInfos = gameInfos;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GameHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stat_text_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        System.out.printf("adapter postion: %d\n", position);
        GameInfo gameInfo = gameInfos.get(position);
        Date date = gameInfo.calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String newString = dateFormat.format(date);
        String dateLabel = "Game Date: " + newString;
        holder.textView.setText(dateLabel);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(context, position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, GameHistoryActivity.class);
                intent.putExtra("gameInfo", gameInfos.get(position));
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gameInfos.size();
    }

    void startActivityHelper(int position){
        Intent intent = new Intent(context, GameHistoryActivity.class);
        intent.putExtra("gameInfos", gameInfos);
    }
}
