package Backend.objectFields.Fields;

import Backend.Tiles.Unit;
import Backend.Units.Enemy;
import Backend.Units.Player;
import Backend.Units.Warrior;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Position {
    private int x;
    private int y;

    public static double range(Position p1, Position p2){
        return Math.sqrt(Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2));
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Position(Position p){
        x = p.x;
        y = p.y;
    }

     public int getXPosition(){
        return x;
     }

    public int getYPosition(){
        return y;
    }

    public void up(){
        y--;
    }
    public void down(){
        y++;
    }
    public void left(){
        x--;
    }
    public void right(){
        x++;
    }

    public void set(int xPosition, int yPosition) {
        x = xPosition;
        y = yPosition;
    }
    public boolean equals(Object other){
        Position otherP = (Position) other;
        return otherP.y == y & otherP.x == x;
    }
}
