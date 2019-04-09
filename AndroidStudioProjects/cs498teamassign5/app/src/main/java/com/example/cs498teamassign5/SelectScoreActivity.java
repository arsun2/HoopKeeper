package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectScoreActivity extends Activity implements View.OnClickListener {
    private Button OnePointButton;
    private Button TwoPointButton;
    private Button ThreePointButton;
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

        OnePointButton.setOnClickListener(this);
        TwoPointButton.setOnClickListener(this);
        ThreePointButton.setOnClickListener(this);

        Intent i = getIntent();
        info = i.getStringExtra("ans");
        System.out.println("Score string is: " + info);
    }

    public void onClick(View v){
        int clickId = v.getId();

        Intent intent = new Intent(this, MainActivity.class);

        if(clickId == R.id.one_point_button){
            info = info + "1Point:";
        } else if (clickId == R.id.two_point_button){
            info = info + "2Point:";
        } else if (clickId == R.id.three_point_button) {
            info = info + "3Point:";
        }
        intent.putExtra("ans", info);
        setResult(RESULT_OK, intent);
        finish();
    }
}
