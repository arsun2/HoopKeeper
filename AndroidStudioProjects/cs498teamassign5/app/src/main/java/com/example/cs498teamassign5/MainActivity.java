package com.example.cs498teamassign5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int ADD_EVENT_ACTIVITY_REQUEST = 1;

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
            Intent intent = new Intent(this, AddEventActivity.class);
            startActivityForResult(intent, ADD_EVENT_ACTIVITY_REQUEST);
        }
    }
}
