package com.example.cs498teamassign5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This is the structure for an entire game
 */
public class GameInfo implements Serializable {
    ArrayList<QuarterlyGameLog> quarterlyGameLogs;
    Calendar calendar;
    public GameInfo(){
        this.calendar = Calendar.getInstance();
        this.quarterlyGameLogs = new ArrayList<>();
    }

    public void set(int index, QuarterlyGameLog quarterlyGameLog){
        if(index == quarterlyGameLogs.size()){
            quarterlyGameLogs.add(quarterlyGameLog);
        } else {
            quarterlyGameLogs.set(index, quarterlyGameLog);
        }
    }

    public QuarterlyGameLog get(int index){
        System.out.printf("QuarterlyGameLog get %d, size(): %d\n", index, quarterlyGameLogs.size());
        if(quarterlyGameLogs.size() <= index){
            System.out.printf("test test\n");
            quarterlyGameLogs.add(new QuarterlyGameLog());
        }
        return quarterlyGameLogs.get(index);
    }

    public int myPlayerScore(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.myPlayerScore();
        }
        return score;
    }

    public int myPlayerRebound(){
        int rebound = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            rebound+= gamelog.myPlayerRebound();
        }
        return rebound;
    }

    public int myPlayerFoul(){
        int foul = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            foul += gamelog.myPlayerFoul();
        }
        return foul;
    }

    public int myPlayerTurnover(){
        int turnover = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            turnover += gamelog.myPlayerTurnover();
        }
        return turnover;
    }
}
