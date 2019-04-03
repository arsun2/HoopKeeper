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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddEventButton = (Button) findViewById(R.id.add_event_button);

        AddEventButton.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId() == R.id.add_event_button){
            Intent intent = new Intent(this, SelectTeamActivity.class);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
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
