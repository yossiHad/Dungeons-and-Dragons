package Backend.Tiles;

import Backend.objectFields.Fields.Position;

public abstract class Tile {
    public char represent;
    protected Position position;

    public Tile(char represent, Position position){
        this.represent = represent;
        this.position = position;
    }
    public Tile(char represent){
        this.represent = represent;
    }
    public String toCharString(){
        return represent + "";
    }
    public Position getPosition(){
        return position;
    }
    public abstract void accept(Unit u);
}
