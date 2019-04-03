package com.example.cs498teamassign5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddScoreActivity extends Activity implements View.OnClickListener {
    private Button OnePointButton;
    private Button TwoPointButton;
    private Button ThreePointButton;

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
    }

    public void onClick(View V){

    }
}
