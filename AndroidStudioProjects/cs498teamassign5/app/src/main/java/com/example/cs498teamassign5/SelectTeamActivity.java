package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectTeamActivity extends Activity implements View.OnClickListener {
    private Button TeamOneButton;
    private Button TeamTwoButton;
    private Button OtherPlayerButton;
    public static final int SELECT_SCORE_ACTIVITY_REQUEST = 1;
    public static String SELECT_TEAM_RETURN_STRING = "SELECT_TEAM_RETURN_STRING";
    private String info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);

        TeamOneButton = (Button) findViewById(R.id.team_1_button);
        TeamTwoButton = (Button) findViewById(R.id.team_2_button);
        OtherPlayerButton = (Button) findViewById(R.id.button6);

        TeamOneButton.setOnClickListener(this);
        TeamTwoButton.setOnClickListener(this);
        OtherPlayerButton.setOnClickListener(this);

        Intent i = getIntent();
        info = i.getStringExtra("ans");
        System.out.println("Select string is: " + info);
    }

    public void onClick(View v){
        /*Codes are
        * "MyPlayer:" for My Player
        * "OpposingTeam:" for Opposing Team
        * "MyTeam:" For my team, but not my player
        */
        boolean isScore = info.equals("Score:");
        if (v.getId() == R.id.team_1_button) {
            info += "MyPlayer:";
        } else if (v.getId() == R.id.team_2_button) {
            info += "OpposingTeam:";
        } else if (v.getId() == R.id.button6) {
            info += "MyTeam:";
        }

        Intent intent;
        if(isScore) {
            intent = new Intent(this, SelectScoreActivity.class);
            intent.putExtra("ans", info);
            startActivityForResult(intent, SELECT_SCORE_ACTIVITY_REQUEST);
        } else {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("ans", info);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent) {
        //System.out.println("hey there");
        if (activityCode == SELECT_SCORE_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                String info = intent.getStringExtra("ans");
//                System.out.printf("Select Score return str: %s\n", info);
                intent.putExtra("ans", info);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
