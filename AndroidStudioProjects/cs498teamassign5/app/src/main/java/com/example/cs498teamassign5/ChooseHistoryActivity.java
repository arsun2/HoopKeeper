package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ChooseHistoryActivity extends Activity {
    private RecyclerView chooseTeamRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<GameInfo> allGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_history);

        initializeRecyclerView();
    }


    public void initializeRecyclerView() {
        chooseTeamRecyclerView = findViewById(R.id.choose_team_recycler_view);
        chooseTeamRecyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        allGames = (ArrayList<GameInfo>) intent.getSerializableExtra("allgames");

        layoutManager = new LinearLayoutManager(this);
        chooseTeamRecyclerView.setLayoutManager(layoutManager);

        Context context = this;
        mAdapter = new GameHistoryAdapter(allGames,context);
        chooseTeamRecyclerView.setAdapter(mAdapter);
    }
}
