package Backend.Tiles;

import Backend.objectFields.Fields.Position;

public class Empty extends Tile {
    public static final char CHAR = '.';

    public Empty(Position position) {
        super(CHAR,position);
    }

    @Override
    public void accept(Unit u) {
        u.visit(this);
    }
    public String toString(){
        return CHAR +"";
    }
}
