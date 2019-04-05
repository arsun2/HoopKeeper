package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Todo: change constructor with team name
        gameStatus = new GameStatus();
        //set the initial score
        updateScore();

        PointButton = (Button) findViewById(R.id.ScoreButton);
        MissButton = (Button) findViewById(R.id.MissButton);
        ReboundButton = (Button) findViewById(R.id.ReboundButton);
        IllegalButton = (Button) findViewById(R.id.IllegalButton);
        OtherButton = (Button) findViewById(R.id.OtherButton);

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
        updateScore();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.ScoreButton) {
            info = "Score:";
        } else if (v.getId() == R.id.MissButton) {
            info = "Miss:";
        } else if (v.getId() == R.id.ReboundButton) {
            info = "Rebound:";
        } else if (v.getId() == R.id.IllegalButton) {
            info = "Illegal:";
        } else if (v.getId() == R.id.OtherButton) {
            info = "Other:";
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

    public void updateScore(){
        myTeamScore = gameStatus.myTeamScore();
        TextView myTeamScoreView = (TextView) findViewById(R.id.myTeamScore);
        myTeamScoreView.setText(Integer.toString(myTeamScore));

        opposingTeamScore = gameStatus.opposingTeamScore();
        TextView opposingTeamScoreView = (TextView) findViewById(R.id.opposingTeamScore);
        opposingTeamScoreView.setText(Integer.toString(opposingTeamScore));

        myPlayerScore = gameStatus.myPlayerScore();

//        String[] textArray = {"One", "Two", "Three", "Four"};
//        RelativeLayout linearLayout = (RelativeLayout)findViewById(R.id.activity_main);
//        for( int i = 0; i < textArray.length; i++ )
//        {
//            Button textView = new Button(this);
//            textView.setText(textArray[i]);
//            linearLayout.addView(textView);
//        }
    }
}
