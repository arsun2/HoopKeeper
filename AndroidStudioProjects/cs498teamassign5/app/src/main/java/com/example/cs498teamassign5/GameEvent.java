package com.example.cs498teamassign5;

/**
 * this is the struct for game event
 * Point 1, 2, 3
 * team: team 1 or 2
 * and different attributes for other event
 * WARNING: need to update parseStrForInfo if new info is added
 */
public class GameEvent {
    int point = 0;
    int team = 0;
    int playerNumber = -1;
    boolean rebound = false;
    boolean foul = false;
    boolean block = false;
    boolean assist = false;
    public GameEvent(String str){
        parseStrForInfo(str);
    }

    /**
     *
     * @param str info generate during user interaction
     *            format should be XXX:XXX:XXX:XXX:
     *            e.g. "Team1:Player1:foul:"
     */
    private void parseStrForInfo(String str){
        String [] arr = str.split(":");
        for(String info : arr){
            switch(info){
                case "Team1":
                    this.team = 1;
                    break;
                case "Team2":
                    this.team = 2;
                    break;
                case "foul":
                    this.foul = true;
                    break;
                case "block":
                    this.block = true;
                    break;
                case "1Point":
                    this.point = 1;
                    break;
                case "2Point":
                    this.point = 2;
                    break;
                case "3Point":
                    this.point = 3;
                    break;
                case "rebound":
                    this.rebound = true;
                    break;
                case "assist":
                    this.assist = true;
                    break;
                default:
                    parsePlayer(info);
            }
        }
    }

    /**
     *
     * @param info, format "playerXX", XX is the digit of player
     */
    private void parsePlayer(String info){
        String playerNumber = info.substring(6);
        this.playerNumber = Integer.parseInt(playerNumber);
    }
}
