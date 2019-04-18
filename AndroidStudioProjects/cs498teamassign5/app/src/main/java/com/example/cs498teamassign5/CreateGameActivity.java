package com.example.cs498teamassign5;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateGameActivity extends Activity implements View.OnClickListener {
    private static final int MAIN_ACTIVITY_REQUEST = 1;
    private Button StartButton;
    private EditText locationText;
    private EditText timeText;
    private EditText myPlayerText;
    private EditText myTeamText;
    private EditText opposingTeamText;

    private String location = "Type Location";
    private String time = "";
    private String myPlayer = "";
    private String myTeam = "";
    private String opposingTeam = "";
    private GameInfo gameInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION} ,1);
        }

        GPSTracker gps = new GPSTracker(this);
        double latitude = 0;
        double longitude = 0;
        if(gps.canGetLocation()){
            latitude = gps.getLatitude(); // returns latitude
            longitude = gps.getLongitude();
        }
        System.out.printf("location %f %f\n", latitude, longitude);

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 5);
            if(addresses.size() > 0){
                String addressLine = addresses.get(0).getAddressLine(0);
                if(addresses != null){
                    location = addressLine;
                    System.out.printf("knownName %s\n", location);
                }
            }
        } catch (IOException e) {
            System.out.printf("IOException\n");
            e.printStackTrace();
        }

        StartButton = (Button) findViewById(R.id.startButton);
        locationText = (EditText) findViewById(R.id.editText);
        timeText = (EditText) findViewById(R.id.TimeText);
        myPlayerText = (EditText) findViewById(R.id.playerText);
        myTeamText = (EditText) findViewById(R.id.MyTeamText);
        opposingTeamText = (EditText) findViewById(R.id.OpposingText);

        gameInfo = new GameInfo();
        Date date = gameInfo.calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String newString = dateFormat.format(date);
        timeText.setText(newString);
        locationText.setText(location);

        StartButton.setOnClickListener(this);
        /*
        Intent intent = getIntent();
        gameInfo = (GameInfo) intent.getSerializableExtra("gameInfo");
        updatePlayerStats();*/
    }

    public void onClick(View v) {
        int ClickId = v.getId();
        if(ClickId == R.id.startButton){
            Intent intent = new Intent(this, MainActivity.class);
            location = locationText.getText().toString();
            time = timeText.getText().toString();
            myPlayer = myPlayerText.getText().toString();
            myTeam = myTeamText.getText().toString();
            opposingTeam = opposingTeamText.getText().toString();


            Bundle toPass = new Bundle();
            toPass.putSerializable("gameInfo", gameInfo);
            toPass.putString("playerName", myPlayer);
            toPass.putString("myTeamName", myTeam);
            toPass.putString("opposingTeam", opposingTeam);
            toPass.putString("location", location);
            toPass.putString("time", time);
            intent.putExtras(toPass);

            startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
        }
    }

    public void updatePlayerStats(){

    }

    public void onActivityResult(int activityCode, int resultCode, Intent intent) {
        //System.out.println("hey there");
        if (activityCode == MAIN_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                System.out.print("create game result\n");
                GameInfo gameinfo = (GameInfo)intent.getSerializableExtra("gameInfo");
                Intent resultIntent = new Intent(this, HomePageActivity.class);
                resultIntent.putExtra("gameInfo", gameinfo);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }
    }
}