package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddEventActivity extends Activity implements View.OnClickListener {
    private static final int ADD_SCORE_ACTIVITY_REQUEST = 1;
    public static String Add_Event_String = "Add_Event_Event";
    public static String ADD_EVENT_RETURN_STRING = "ADD_EVENT_RETURN_STRING";
    private Button AddScoreButton;
    private Button FoulButton;
    private Button AssistButton;
    private Button ReboundButton;
    private Button BlockButton;
    private String info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        AddScoreButton = (Button) findViewById(R.id.add_score_button);
        FoulButton = (Button) findViewById(R.id.foul_button);
        AssistButton = (Button) findViewById(R.id.assist_button);
        ReboundButton = (Button) findViewById(R.id.rebound_button);
        BlockButton = (Button) findViewById(R.id.block_button);

        AddScoreButton.setOnClickListener(this);
        FoulButton.setOnClickListener(this);
        AssistButton.setOnClickListener(this);
        ReboundButton.setOnClickListener(this);
        BlockButton.setOnClickListener(this);

    }

    public void onClick(View v){
        int clickID = v.getId();

        Intent SelectTeamIntent = getIntent();
        info = SelectTeamIntent.getStringExtra(SelectTeamActivity.Select_Team_Event);

        if(clickID == R.id.add_score_button){
            Intent intent = new Intent(this, AddScoreActivity.class);
            intent.putExtra(Add_Event_String, info);
            startActivityForResult(intent, ADD_SCORE_ACTIVITY_REQUEST);
        } else {
            if (clickID == R.id.foul_button){
                info = info + "foul:";
            } else if (clickID == R.id.assist_button){
                info = info + "assist:";
            } else if (clickID == R.id.rebound_button){
                info = info + "rebound:";
            } else if (clickID == R.id.block_button){
                info = info + "rebound:";
            }
            Intent returnIntent  = new Intent();
            returnIntent.putExtra(ADD_EVENT_RETURN_STRING, info);
            setResult(RESULT_OK, returnIntent);
        }

    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent){
        if(activityCode == ADD_SCORE_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                String s = intent.getStringExtra(AddScoreActivity.ADD_SCORE_RETURN_STRING);
                info = info + s;
                System.out.printf("Add Score return str: %s\n", s);
            }
            Intent returnIntent  = new Intent();
            returnIntent.putExtra(ADD_EVENT_RETURN_STRING, info);
            setResult(RESULT_OK, returnIntent);
        }
    }
}
