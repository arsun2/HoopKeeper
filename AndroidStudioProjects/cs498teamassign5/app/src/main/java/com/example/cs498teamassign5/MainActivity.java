package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int SELECT_TEAM_ACTIVITY_REQUEST = 1;
    private Button PointButton;
    private Button MissButton;
    private Button ReboundButton;
    private Button IllegalButton;
    private Button OtherButton;
    private Button PlayerStatButton;
    private ImageButton PrevGameButton;
    private ImageButton NextGameButton;
    private QuarterlyGameLog quarterlyGameLog;
    private int myTeamScore = 0;
    private int opposingTeamScore = 0;
    private int myPlayerScore = 0;
    private int myPlayerRebound = 0;
    private int myPlayerFoul = 0;
    private int myPlayerTurnover = 0;
    private String info = null;
    private RecyclerView myTeamRecyclerView;
    private RecyclerView opposingTeamRecyclerView;
    private RecyclerView.Adapter myTeamAdapter;
    private RecyclerView.Adapter opposingTeamAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager2;
    private int quarterNumber = 0;
    private GameInfo gameInfo;
    private String [] quarterString = new String [] {"Q1", "Q2", "Q3", "Q4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        gameInfo = (GameInfo) intent.getSerializableExtra("gameInfo");
        quarterlyGameLog = gameInfo.get(quarterNumber);

        //Adapters Initialization
        initializeQuarter();
        initializeRecyclerView();

        PointButton = (Button) findViewById(R.id.ScoreButton);
        MissButton = (Button) findViewById(R.id.MissButton);
        ReboundButton = (Button) findViewById(R.id.ReboundButton);
        IllegalButton = (Button) findViewById(R.id.IllegalButton);
        OtherButton = (Button) findViewById(R.id.TurnoverButton);
        PrevGameButton = (ImageButton) findViewById(R.id.PrevGameButton);
        NextGameButton = (ImageButton) findViewById(R.id.NextGameButton);
        PlayerStatButton = (Button) findViewById(R.id.PlayerButton);

        PointButton.setOnClickListener(this);
        MissButton.setOnClickListener(this);
        ReboundButton.setOnClickListener(this);
        IllegalButton.setOnClickListener(this);
        OtherButton.setOnClickListener(this);
        PrevGameButton.setOnClickListener(this);
        NextGameButton.setOnClickListener(this);
        PlayerStatButton.setOnClickListener(this);

    }

    @Override
    protected void onResume(){
        super.onResume();
        updateScoreAndStat();
    }

    public void onClick(View v) {
        int ClickId = v.getId();
        if (ClickId == R.id.ScoreButton) {
            info = "Score:";
        } else if (ClickId == R.id.MissButton) {
            info = "Miss:";
        } else if (ClickId == R.id.ReboundButton) {
            info = "Rebound:";
        } else if (ClickId == R.id.IllegalButton) {
            info = "Illegal:";
        } else if (ClickId == R.id.TurnoverButton) {
            info = "Turnover:";
        } else if (ClickId == R.id.NextGameButton){
            switchQuarterHelper(true);
            return;
        } else if (ClickId == R.id.PlayerButton){
            Intent intent = new Intent(this, PlayerStat.class);
            intent.putExtra("ans", info);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
            return;
        } else {
            switchQuarterHelper(false);
            return;
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
                if(info != null){
                    quarterlyGameLog.addGameEvent(info);
                }
            }
        }
    }

    public void updateScoreAndStat(){
        myTeamScore = quarterlyGameLog.myTeamScore();
        TextView myTeamScoreView = (TextView) findViewById(R.id.myTeamScore);
        myTeamScoreView.setText(Integer.toString(myTeamScore));

        opposingTeamScore = quarterlyGameLog.opposingTeamScore();
        TextView opposingTeamScoreView = (TextView) findViewById(R.id.opposingTeamScore);
        opposingTeamScoreView.setText(Integer.toString(opposingTeamScore));

        //to update
        /*
        myPlayerScore = 0;
        TextView playerPoints = (TextView) findViewById(R.id.PlayerPoints);
        playerPoints.setText(Integer.toString(myPlayerScore));

        myPlayerRebound = 0;
        TextView playerRebs = (TextView) findViewById(R.id.PlayerRebounds);
        playerRebs.setText(Integer.toString(myPlayerRebound));

        myPlayerFoul = 0;
        TextView playerFouls = (TextView) findViewById(R.id.PlayerFouls);
        playerRebs.setText(Integer.toString(myPlayerFoul));

        myPlayerTurnover = 0;
        TextView playerTurnovers = (TextView) findViewById(R.id.PlayerTurnovers);
        playerRebs.setText(Integer.toString(myPlayerTurnover));*/



        myTeamAdapter.notifyDataSetChanged();
        opposingTeamAdapter.notifyDataSetChanged();

        myPlayerScore = gameInfo.myPlayerScore();
        System.out.printf("myPlayerScore %d\n", myPlayerScore);
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

        ArrayList<GameEvent> myTeamGameLog = quarterlyGameLog.getMyTeamGameLog();
        ArrayList<GameEvent> opposingTeamGameLog = quarterlyGameLog.getOpposingTeamGameLog();

        // specify an adapter (see also next example)
        myTeamAdapter = new MyAdapter(myTeamGameLog);
        myTeamRecyclerView.setAdapter(myTeamAdapter);
        opposingTeamAdapter = new MyAdapter(opposingTeamGameLog);
        opposingTeamRecyclerView.setAdapter(opposingTeamAdapter);
    }

    public void initializeQuarter(){
        String quarter = quarterString[quarterNumber];
        TextView quarterName = (TextView) findViewById(R.id.quarterNumber);
        quarterName.setText(quarter);
    }

    public void switchQuarterHelper(boolean nextGame){
        //quarterNumber is zero-indexed!!
        if((quarterNumber == 3 && nextGame) || (quarterNumber == 0 && !nextGame)){
            Context context = getApplicationContext();
            CharSequence text;
            if(nextGame){
                text = "Last Quarter!";
            } else {
                text = "First Quarter!";
            }
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if(nextGame){
                quarterNumber++;
            } else {
                quarterNumber--;
            }
            quarterlyGameLog = gameInfo.get(quarterNumber);
            initializeQuarter();
            updateScoreAndStat();
            initializeRecyclerView();
        }
    }
}
