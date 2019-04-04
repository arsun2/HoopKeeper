package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    public static String Main_Activity_Event = "Main_Activity_Event";
    private static final int SELECT_TEAM_ACTIVITY_REQUEST = 1;
    private Button AddEventButton;
    private Button PointButton;
    private Button MissButton;
    private Button ReboundButton;
    private Button IllegalButton;
    private Button OtherButton;
    private ArrayList<GameEvent> gameLog;
    private int ownScore = 0;
    private int opponentScore = 0;
    private String info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AddEventButton = (Button) findViewById(R.id.add_event_button);
        PointButton = (Button) findViewById(R.id.button);
        MissButton = (Button) findViewById(R.id.button2);
        ReboundButton = (Button) findViewById(R.id.button3);
        IllegalButton = (Button) findViewById(R.id.button4);
        OtherButton = (Button) findViewById(R.id.button5);

        //AddEventButton.setOnClickListener(this);
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

    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            Intent intent = new Intent(this, SelectTeamActivity.class);
            info = "Score:";
            intent.putExtra("ans", info);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        } else if (v.getId() == R.id.button2) {
            info = "Miss:";
            Intent intent = new Intent(this, SelectTeamActivity.class);
            intent.putExtra("ans", info);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        } else if (v.getId() == R.id.button3) {
            info = "Rebound:";
            Intent intent = new Intent(this, SelectTeamActivity.class);
            intent.putExtra("ans", info);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        } else if (v.getId() == R.id.button4) {
            info = "Illegal:";
            Intent intent = new Intent(this, SelectTeamActivity.class);
            intent.putExtra("ans", info);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        } else if (v.getId() == R.id.button5) {
            info = "Other:";
            Intent intent = new Intent(this, SelectTeamActivity.class);
            intent.putExtra("ans", info);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        }
    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent) {
        //System.out.println("hey there");
        if (activityCode == SELECT_TEAM_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                String returnStr = intent.getStringExtra(AddScoreActivity.ADD_SCORE_RETURN_STRING);
                System.out.printf("Select Team return str: %s\n", returnStr);
            }
        }
        /*
        if(activityCode == ADD_SCORE_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                String s = intent.getStringExtra(AddScoreActivity.ADD_SCORE_RETURN_STRING);
                info = info + s;
                System.out.printf("Add Score return str: %s\n", s);
            }
            Intent returnIntent  = new Intent();
            returnIntent.putExtra(ADD_EVENT_RETURN_STRING, info);
            setResult(RESULT_OK, returnIntent);
        }*/
    }
}
