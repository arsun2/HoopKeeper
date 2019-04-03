package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectTeamActivity extends Activity implements View.OnClickListener {
    private Button TeamOneButton;
    private Button TeamTwoButton;
    private static final int ADD_EVENT_ACTIVITY_REQUEST = 1;
    public static String Select_Team_Event = "Select_Team_Event";
    public static String SELECT_TEAM_RETURN_STRING = "SELECT_TEAM_RETURN_STRING";
    private String info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);

        TeamOneButton = (Button) findViewById(R.id.team_1_button);
        TeamTwoButton = (Button) findViewById(R.id.team_2_button);

        TeamOneButton.setOnClickListener(this);
        TeamTwoButton.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId() == R.id.team_1_button) {
            info = "Team1:";
        } else {
            info = "Team2:";
        }
        Intent intent = new Intent(this, AddEventActivity.class);
        intent.putExtra(Select_Team_Event, info);
        startActivityForResult(intent, ADD_EVENT_ACTIVITY_REQUEST);
    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent){
        if(activityCode == ADD_EVENT_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                String returnStr = intent.getStringExtra(AddEventActivity.ADD_EVENT_RETURN_STRING);
                info = returnStr;
                System.out.printf("Add Event return str %s\n", info);
            }
            Intent returnIntent  = new Intent();
            returnIntent.putExtra(SELECT_TEAM_RETURN_STRING, info);
            setResult(RESULT_OK, returnIntent);
        }
    }
}
