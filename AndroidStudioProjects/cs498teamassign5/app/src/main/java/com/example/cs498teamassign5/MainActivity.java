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
    private ArrayList<GameEvent> gameLog;
    private int ownScore = 0;
    private int opponentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddEventButton = (Button) findViewById(R.id.add_event_button);

        AddEventButton.setOnClickListener(this);
        //This is the stat string passed in
        Intent i = getIntent();
        String statString = i.getStringExtra("ans");
        System.out.println("Stat string is: " + statString);
    }

    public void onClick(View v){
        if(v.getId() == R.id.add_event_button){
            Intent intent = new Intent(this, SelectTeamActivity.class);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        }
    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent) {
        System.out.println("hey there");
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
