package com.example.cs498teamassign5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This is the structure for an entire game
 */
public class GameInfo implements Serializable {
    ArrayList<GameStatus> gameStatuses;
    Calendar calendar;
    public GameInfo(){
        this.calendar = Calendar.getInstance();
        this.gameStatuses = new ArrayList<>(0);
    }

    public void set(int index, GameStatus gameStatus){
        if(index == gameStatuses.size()){
            gameStatuses.add(gameStatus);
        } else {
            gameStatuses.set(index, gameStatus);
        }
    }

    public GameStatus get(int index){
        System.out.printf("GameStatus get %d, size(): %d\n", index, gameStatuses.size());
        if(gameStatuses.size() >= index) return null;
        else return gameStatuses.get(index);
    }
}
