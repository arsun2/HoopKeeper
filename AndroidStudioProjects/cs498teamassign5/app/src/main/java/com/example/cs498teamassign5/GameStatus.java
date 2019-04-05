package com.example.cs498teamassign5;

import java.util.ArrayList;

public class GameStatus {
    private ArrayList<GameEvent> gameLog;
    private String myTeamName = "";
    private String opposingTeamName = "";
    private int time;

    //Todo: change constructor with team name
    public GameStatus(){
        this.gameLog = new ArrayList<GameEvent>();
        this.time = 0;
    }

    public void addGameEvent(String info){
        this.time++;
        GameEvent gameEvent = new GameEvent(info, this.time);
        gameLog.add(gameEvent);
    }

    public int myTeamScore(){
        int sum = 0;
        for(GameEvent gameEvent : gameLog){
            if(gameEvent.score && gameEvent.player != Player.OpposingTeam){
                int point = 0;
                if(gameEvent.point == Point.onePoint){
                    point = 1;
                } else if (gameEvent.point == Point.twoPoint){
                    point = 2;
                } else {
                    point = 3;
                }
                sum += point;
            }
        }
        return sum;
    }

    public int myPlayerScore(){
        int sum = 0;
        for(GameEvent gameEvent : gameLog){
            if(gameEvent.score && gameEvent.player == Player.MyPlayer){
                int point = 0;
                if(gameEvent.point == Point.onePoint){
                    point = 1;
                } else if (gameEvent.point == Point.twoPoint){
                    point = 2;
                } else {
                    point = 3;
                }
                sum += point;
            }
        }
        return sum;
    }

    public int opposingTeamScore(){
        int sum = 0;
        for(GameEvent gameEvent : gameLog){
            if(gameEvent.score && gameEvent.player == Player.OpposingTeam){
                int point = 0;
                if(gameEvent.point == Point.onePoint){
                    point = 1;
                } else if (gameEvent.point == Point.twoPoint){
                    point = 2;
                } else {
                    point = 3;
                }
                sum += point;
            }
        }
        return sum;
    }
}
