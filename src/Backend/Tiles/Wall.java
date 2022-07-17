package Backend.Tiles;

import Backend.objectFields.Fields.Position;

public class Wall extends Tile{
    public static final char CHAR = '#';

    public Wall(Position position){
        super(CHAR,position);
    }
    @Override
    public void accept(Unit u) {
        u.visit(this);
    }
    public String toString(){
        return CHAR + "";
    }
}
