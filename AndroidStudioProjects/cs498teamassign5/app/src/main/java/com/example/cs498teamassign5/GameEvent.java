package com.example.cs498teamassign5;

/**
 * this is the struct for game event
 * Point 1, 2, 3
 * Team 1 is Away Team, Team 0 is home team
 * and different attributes for other event
 */
public class GameEvent {
    int point = 0;
    int team = -1;
    int playerNumber = -1;
    boolean rebound = false;
    boolean foul = false;
    boolean block = false;
    boolean assist = false;
    public GameEvent(int team, int playerNumber){
        this.team = team;
        this.playerNumber = playerNumber;
    }
}
