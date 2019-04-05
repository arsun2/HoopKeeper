package com.example.cs498teamassign5;

/**
 * this is the struct for game event
 * Point 1, 2, 3
 * and different attributes for other event
 * WARNING: need to update parseStrForInfo if new info is added
 */
enum Player
{
    MyPlayer, OpposingTeam, MyTeam
}

enum Stat
{
    rebound, foul, block, assist
}

enum Point
{
    onePoint, twoPoint, threePoint
}

public class GameEvent {
    int time;   //Placeholder for actual time
    Player player;
    Stat stat;
    Boolean score = false;
    Point point;
    public GameEvent(String str, int time){
        this.time = time;
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
                case "MyPlayer":
                    this.player = Player.MyPlayer;
                    break;
                case "MyTeam":
                    this.player = Player.MyTeam;
                    break;
                case "OpposingTeam":
                    this.player = Player.OpposingTeam;
                    break;
                case "1Point":
                    this.score = true;
                    this.point = Point.onePoint;
                    break;
                case "2Point":
                    this.score = true;
                    this.point = Point.twoPoint;
                    break;
                case "3Point":
                    this.score = true;
                    this.point = Point.threePoint;
                    break;
                case "foul":
                    this.stat = Stat.foul;
                    break;
                case "block":
                    this.stat = Stat.block;
                    break;
                case "rebound":
                    this.stat = Stat.rebound;
                    break;
                case "assist":
                    this.stat = Stat.assist;
                    break;
                default:
            }
        }
    }

    public Stat getStat(){
        return this.stat;
    }

    public Player getPlayer(){
        return this.player;
    }
}
