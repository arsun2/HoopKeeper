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

    public int myTeamScore(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.myTeamScore();
        }
        return score;
    }

    public int myTeamRebound(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.myTeamRebound();
        }
        return score;
    }

    public int myTeamMiss(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.myTeamMiss();
        }
        return score;
    }

    public int myTeamFoul(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.myTeamFoul();
        }
        return score;
    }

    public int myTeamTurnover(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.myTeamTurnover();
        }
        return score;
    }

    public int opposingTeamTurnover(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.opposingTeamTurnover();
        }
        return score;
    }

    public int opposingTeamFoul(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.opposingTeamFoul();
        }
        return score;
    }

    public int opposingTeamScore(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.opposingTeamScore();
        }
        return score;
    }

    public int opposingTeamMiss(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.opposingTeamMiss();
        }
        return score;
    }

    public int opposingTeamRebound(){
        int score = 0;
        for(QuarterlyGameLog gamelog : quarterlyGameLogs){
            score+= gamelog.opposingTeamRebound();
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
