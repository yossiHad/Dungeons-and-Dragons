package Backend.objectFields.Fields;

import Backend.objectFields.Fields.Generators.ValueGenerator;

import java.util.HashMap;
import java.util.function.Function;



public class Movement {
    public static HashMap<Character,Function<Position,Position>> movements = new HashMap<>();
    public static char up;
    public static char down;
    public static char left;
    public static char right;
    public static char skipTurn;

    public static void build(){
        movements.put(Movement.up,(position) -> Movement.up(position));
        movements.put(Movement.down,(position) -> Movement.down(position));
        movements.put(Movement.left,(position) -> Movement.left(position));
        movements.put(Movement.right,(position) -> Movement.right(position));
        movements.put(Movement.skipTurn,(position) -> Movement.skipTurn(position));
    }

    public static Position up(Position p){
        Position move = new Position(p);
        move.up();
        return move;
    }

    public static Position down(Position p){
        Position move = new Position(p);
        move.down();
        return move;
    }

    public static Position left(Position p){
        Position move = new Position(p);
        move.left();
        return move;
    }

    public static Position right(Position p){
        Position move = new Position(p);
        move.right();
        return move;
    }

    public static Position skipTurn(Position p){return p;}

    public static Position chooseRandomMove(Position p){
        ValueGenerator valueGenerator = ValueGenerator.getInstance();
        char[] keys = {Movement.up,Movement.down,Movement.left,Movement.right,Movement.skipTurn};
        char random = keys[valueGenerator.getValue(keys.length)%keys.length];
        Function<Position,Position> function = movements.get(random);
        return function.apply(p);
    }


}
