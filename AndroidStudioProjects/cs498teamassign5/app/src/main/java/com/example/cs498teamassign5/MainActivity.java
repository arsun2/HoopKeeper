package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    public static String Main_Activity_Event = "Main_Activity_Event";
    public static String Add_Event_String = "Add_Event_Event";
    public static String ADD_EVENT_RETURN_STRING = "ADD_EVENT_RETURN_STRING";
    private static final int SELECT_TEAM_ACTIVITY_REQUEST = 1;
    private Button AddScoreButton;
    private Button MissButton;
    private Button ReboundButton;
    private Button StealButton;
    private Button FoulButton;
    private Button AddEventButton;
    private String info;

    private ArrayList<GameEvent> gameLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddEventButton = (Button) findViewById(R.id.add_event_button);
        AddScoreButton = (Button) findViewById(R.id.add_score_button);
        MissButton = (Button) findViewById(R.id.miss_button);
        ReboundButton = (Button) findViewById(R.id.rebound_button);
        StealButton = (Button) findViewById(R.id.steal_button);
        FoulButton = (Button) findViewById(R.id.foul_button);

        AddScoreButton.setOnClickListener(this);
        MissButton.setOnClickListener(this);
        ReboundButton.setOnClickListener(this);
        StealButton.setOnClickListener(this);
        FoulButton.setOnClickListener(this);

        AddEventButton.setOnClickListener(this);
    }

    public void onClick(View v){
        int clickId = v.getId();

        Intent SelectTeamIntent = getIntent();
        info = SelectTeamIntent.getStringExtra(SelectTeamActivity.Select_Team_Event);

        if (clickId == R.id.add_score_button) {
            Intent intent = new Intent(this, AddScoreActivity.class);
            intent.putExtra(Add_Event_String, info);
        } else {
            if (clickId == R.id.foul_button){
                info = info + "foul:";
            } else if (clickId == R.id.assist_button){
                info = info + "assist:";
            } else if (clickId == R.id.rebound_button){
                info = info + "rebound:";
            } else if (clickId == R.id.block_button){
                info = info + "rebound:";
            }
            Intent returnIntent  = new Intent();
            returnIntent.putExtra(ADD_EVENT_RETURN_STRING, info);
            setResult(RESULT_OK, returnIntent);
        }
    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent) {
        if (activityCode == SELECT_TEAM_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                String returnStr = intent.getStringExtra(SelectTeamActivity.SELECT_TEAM_RETURN_STRING);
                System.out.printf("Select Team return str: %s\n", returnStr);
            }
        }
    }
}
