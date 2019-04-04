package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddScoreActivity extends Activity implements View.OnClickListener {
    private Button OnePointButton;
    private Button TwoPointButton;
    private Button ThreePointButton;
    private Button SubmitButton;
    private String info = "";
    private static final int SELECT_TEAM_ACTIVITY_REQUEST = 1;
    public static String ADD_SCORE_RETURN_STRING = "ADD_SCORE_RETURN_STRING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);

        OnePointButton = (Button) findViewById(R.id.one_point_button);
        TwoPointButton = (Button) findViewById(R.id.two_point_button);
        ThreePointButton = (Button) findViewById(R.id.three_point_button);
        SubmitButton = (Button) findViewById(R.id.submitButton);

        OnePointButton.setOnClickListener(this);
        TwoPointButton.setOnClickListener(this);
        ThreePointButton.setOnClickListener(this);
        SubmitButton.setOnClickListener(this);
    }

    public void onClick(View v){
        int clickId = v.getId();

        Intent AddEventIntent = getIntent();
        info = AddEventIntent.getStringExtra(AddEventActivity.Add_Event_String);

        if(clickId == R.id.one_point_button){
            info = info + "1Point:";
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("ans", info);
            setResult(RESULT_OK, intent);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        } else if (clickId == R.id.two_point_button){
            info = info + "2Point:";
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("ans", info);
            setResult(RESULT_OK, intent);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        } else if (clickId == R.id.three_point_button) {
            info = info + "3Point:";
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("ans", info);
            setResult(RESULT_OK, intent);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        } else if (clickId == R.id.submitButton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, SELECT_TEAM_ACTIVITY_REQUEST);
        }

        System.out.println("yolo");
        Intent returnIntent = new Intent();
        returnIntent.putExtra(ADD_SCORE_RETURN_STRING, info);
        setResult(RESULT_OK, returnIntent);
    }
}
