package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int SELECT_TEAM_ACTIVITY_REQUEST = 1;
    private Button PointButton;
    private Button MissButton;
    private Button ReboundButton;
    private Button FoulButton;
    private Button TurnoverButton;
    private Button PlayerStatButton;
    private Button FinishButton;
    private TextView myTeamNameT;
    private TextView opposingTeamNameT;
    private ImageButton PrevGameButton;
    private ImageButton NextGameButton;
    private Button CompleteButton;
    private QuarterlyGameLog quarterlyGameLog;
    private TextView quarterNumberView;
    private GestureDetectorCompat gestureDetectorCompat = null;

    private String myTeamName = "";
    private String opposingTeamName = "";
    private String myPlayerName = "";

    private int myTeamScore = 0;
    private int opposingTeamScore = 0;
    private String info = null;
    private RecyclerView myTeamRecyclerView;
    private RecyclerView opposingTeamRecyclerView;
    private RecyclerView.Adapter myTeamAdapter;
    private RecyclerView.Adapter opposingTeamAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager2;
    private int quarterNumber = 0;
    private GameInfo gameInfo;
    private String [] quarterString = new String [] {"Q1", "Q2", "Q3", "Q4", "OT", "2OT", "3OT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        //gameInfo = (GameInfo) intent.getSerializableExtra("gameInfo");
        //quarterlyGameLog = gameInfo.get(quarterNumber);


        Bundle info = intent.getExtras();
        gameInfo = (GameInfo)info.getSerializable("gameInfo");
        quarterlyGameLog = gameInfo.get(quarterNumber);
        myTeamName = (String)info.getSerializable("myTeamName");
        opposingTeamName = (String) info.getSerializable("opposingTeam");
        myPlayerName = (String) info.getSerializable("playerName");


        //Adapters Initialization
        initializeQuarter();
        initializeRecyclerView();

        PointButton = (Button) findViewById(R.id.ScoreButton);
        MissButton = (Button) findViewById(R.id.MissButton);
        ReboundButton = (Button) findViewById(R.id.ReboundButton);
        FoulButton = (Button) findViewById(R.id.FoulButton);
        TurnoverButton = (Button) findViewById(R.id.TurnoverButton);
        PrevGameButton = (ImageButton) findViewById(R.id.PrevGameButton);
        PrevGameButton.setVisibility(View.INVISIBLE);
        NextGameButton = (ImageButton) findViewById(R.id.NextGameButton);
        PlayerStatButton = (Button) findViewById(R.id.PlayerButton);
        CompleteButton = (Button) findViewById(R.id.completeButton);
        FinishButton = (Button) findViewById(R.id.finishButton);

        myTeamNameT = (TextView) findViewById(R.id.myTeamName);
        opposingTeamNameT = (TextView) findViewById(R.id.opposingTeamName);
        myTeamNameT.setText(myTeamName);
        opposingTeamNameT.setText(opposingTeamName);

        PointButton.setOnClickListener(this);
        MissButton.setOnClickListener(this);
        ReboundButton.setOnClickListener(this);
        FoulButton.setOnClickListener(this);
        TurnoverButton.setOnClickListener(this);
//        PrevGameButton.setOnClickListener(this);
//        NextGameButton.setOnClickListener(this);
        PlayerStatButton.setOnClickListener(this);
        CompleteButton.setOnClickListener(this);
        FinishButton.setOnClickListener(this);

        quarterNumberView = findViewById(R.id.quarterNumber);

        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateScoreAndStat();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat.onTouchEvent(event);
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }

    public void displayMessage(String message)
    {
        if(quarterNumberView!=null)
        {
            // Display text in the text view.
            quarterNumberView.setText(message);
        }
    }

    public void onClick(View v) {
        int ClickId = v.getId();
        if (ClickId == R.id.ScoreButton) {
            info = "Score:";
        } else if (ClickId == R.id.MissButton) {
            info = "Miss:";
        } else if (ClickId == R.id.ReboundButton) {
            info = "Rebound:";
        } else if (ClickId == R.id.FoulButton) {
            info = "Foul:";
        } else if (ClickId == R.id.TurnoverButton) {
            info = "Turnover:";
        } else if (ClickId == R.id.NextGameButton){
            switchQuarterHelper(true);
            return;
        } else if (ClickId == R.id.PlayerButton){
            Intent intent = new Intent(this, PlayerStat.class);
            Bundle playerStatBundle = new Bundle();
            playerStatBundle.putSerializable("gameInfo", gameInfo);
            playerStatBundle.putString("playerName", myPlayerName);
            intent.putExtras(playerStatBundle);
            startActivity(intent);
            return;
        } else if (ClickId == R.id.completeButton){
            Intent intent = new Intent(this, GameHistoryActivity.class);
            Bundle gameSummaryBundle = new Bundle();
            gameSummaryBundle.putSerializable("gameInfo", gameInfo);
            gameSummaryBundle.putString("myTeamName", myTeamName);
            gameSummaryBundle.putString("opposingTeamName", opposingTeamName);
            gameSummaryBundle.putString("playerName", myPlayerName);
            //intent.putExtra("gameInfo", gameInfo);
            intent.putExtras(gameSummaryBundle);
            startActivity(intent);
            return;
        } else if (ClickId == R.id.finishButton){
            System.out.print("finishButton is clicked\n");
            Intent intent = new Intent(this, CreateGameActivity.class);
            intent.putExtra("gameInfo", gameInfo);
            setResult(RESULT_OK, intent);
            finish();
            return;
        } else {
            switchQuarterHelper(false);
            return;
        }
        Intent intent = new Intent(this, SelectTeamActivity.class);
        Bundle selectBundle = new Bundle();
        selectBundle.putSerializable("ans", info);
        selectBundle.putString("playerName", myPlayerName);
        intent.putExtras(selectBundle);
        startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        //startActivity(intent);
        return;
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

        if(nextGame){
            if(quarterNumber == 6) {
                // Out of index
                return;
            } else {
                vibrateHelper();
                quarterNumber++;
            }
        } else {
            if(quarterNumber == 0) {
                // Out of index
                return;
            } else {
                vibrateHelper();
                quarterNumber--;
            }
        }
        quarterlyGameLog = gameInfo.get(quarterNumber);
        initializeQuarter();
        updateScoreAndStat();
        initializeRecyclerView();

        if(quarterNumber == 6) {
            NextGameButton.setVisibility(View.INVISIBLE);
        } else if (quarterNumber == 0) {
            PrevGameButton.setVisibility(View.INVISIBLE);
        } else {
            NextGameButton.setVisibility(View.VISIBLE);
            PrevGameButton.setVisibility(View.VISIBLE);
        }

    }

    private void vibrateHelper(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        System.out.print("vibrated\n");
    // Vibrate for 100 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(100);
        }
    }
}
