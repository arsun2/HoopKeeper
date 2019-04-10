package com.example.cs498teamassign5;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<GameEvent> gameLog;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<GameEvent> gameLog) {
        this.gameLog = gameLog;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stat_text_view, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        System.out.printf("adapter postion in main_activity adapter: %d\n", position);
        String newString;
        GameEvent gameEvent = gameLog.get(position);
        if(gameEvent.score){
            if(gameEvent.point.toString().toLowerCase().equals("threepoint")){
                newString = "Score: " + "3PT";
            } else if(gameEvent.point.toString().toLowerCase().equals("twopoint")) {
                newString = "Score: " + "2PT";
            } else {
                newString = "Score: " + "1PT";
            }
            //newString = "Score: " + gameEvent.point.toString();
        } else if (gameEvent.miss){
            if(gameEvent.point.toString().toLowerCase().equals("threepoint")){
                newString = "Miss: " + "3PT";
            } else if(gameEvent.point.toString().toLowerCase().equals("twopoint")) {
                newString = "Miss: " + "2PT";
            } else {
                newString = "Miss: " + "1PT";
            }
        } else {
            newString = gameEvent.stat.toString();
        }
        holder.textView.setText(newString);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gameLog.size();
    }
}
