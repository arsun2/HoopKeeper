package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int SELECT_TEAM_ACTIVITY_REQUEST = 1;
    private Button PointButton;
    private Button MissButton;
    private Button ReboundButton;
    private Button IllegalButton;
    private Button OtherButton;
    private GameStatus gameStatus;
    private int myTeamScore = 0;
    private int opposingTeamScore = 0;
    private int myPlayerScore = 0;
    private String info = null;
    private RecyclerView myTeamRecyclerView;
    private RecyclerView opposingTeamRecyclerView;
    private RecyclerView.Adapter myTeamAdapter;
    private RecyclerView.Adapter opposingTeamAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Todo: change constructor with team name
        gameStatus = new GameStatus();

        //Adapters Initialization
        initializeRecyclerView();

        PointButton = (Button) findViewById(R.id.ScoreButton);
        MissButton = (Button) findViewById(R.id.MissButton);
        ReboundButton = (Button) findViewById(R.id.ReboundButton);
        IllegalButton = (Button) findViewById(R.id.IllegalButton);
        OtherButton = (Button) findViewById(R.id.TurnoverButton);

        PointButton.setOnClickListener(this);
        MissButton.setOnClickListener(this);
        ReboundButton.setOnClickListener(this);
        IllegalButton.setOnClickListener(this);
        OtherButton.setOnClickListener(this);

        //This is the stat string passed in
        Intent i = getIntent();
        info = i.getStringExtra("ans");
        System.out.println("Main string is: " + info);
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateScoreAndStat();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.ScoreButton) {
            info = "Score:";
        } else if (v.getId() == R.id.MissButton) {
            //Todo Add suppor for miss
            info = "Miss:";
        } else if (v.getId() == R.id.ReboundButton) {
            info = "Rebound:";
        } else if (v.getId() == R.id.IllegalButton) {
            info = "Illegal:";
        } else if (v.getId() == R.id.TurnoverButton) {
            info = "Turnover:";
        }
        Intent intent = new Intent(this, SelectTeamActivity.class);
        intent.putExtra("ans", info);
        startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent) {
        //System.out.println("hey there");
        if (activityCode == SELECT_TEAM_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                String info = intent.getStringExtra("ans");
                System.out.printf("Select Team return str: %s\n", info);
                gameStatus.addGameEvent(info);
            }
        }
    }

    public void updateScoreAndStat(){
        myTeamScore = gameStatus.myTeamScore();
        TextView myTeamScoreView = (TextView) findViewById(R.id.myTeamScore);
        myTeamScoreView.setText(Integer.toString(myTeamScore));

        opposingTeamScore = gameStatus.opposingTeamScore();
        TextView opposingTeamScoreView = (TextView) findViewById(R.id.opposingTeamScore);
        opposingTeamScoreView.setText(Integer.toString(opposingTeamScore));

        myPlayerScore = gameStatus.myPlayerScore();

        myTeamAdapter.notifyDataSetChanged();
        opposingTeamAdapter.notifyDataSetChanged();
    }

    public void initializeRecyclerView(){
        myTeamRecyclerView = (RecyclerView) findViewById(R.id.myTeam_recycler_view);
        opposingTeamRecyclerView = (RecyclerView) findViewById(R.id.opposingTeam_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        myTeamRecyclerView.setHasFixedSize(true);
        opposingTeamRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        myTeamRecyclerView.setLayoutManager(layoutManager);
        layoutManager2 = new LinearLayoutManager(this);
        opposingTeamRecyclerView.setLayoutManager(layoutManager2);

        ArrayList<GameEvent> myTeamGameLog = gameStatus.getMyTeamGameLog();
        ArrayList<GameEvent> opposingTeamGameLog = gameStatus.getOpposingTeamGameLog();

        // specify an adapter (see also next example)
        myTeamAdapter = new MyAdapter(myTeamGameLog);
        myTeamRecyclerView.setAdapter(myTeamAdapter);
        opposingTeamAdapter = new MyAdapter(opposingTeamGameLog);
        opposingTeamRecyclerView.setAdapter(opposingTeamAdapter);
    }
}
