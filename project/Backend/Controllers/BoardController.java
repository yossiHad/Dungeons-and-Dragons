package Backend.Controllers;

import Backend.Tiles.Empty;
import Backend.Tiles.Tile;
import Backend.Tiles.Unit;
import Backend.objectFields.Fields.Position;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BoardController {
    private List<Tile> board;
    private int height;
    private int width;

    public BoardController(List<Tile> board, int height, int width){
        this.board = board;
        this.height = height;
        this.width = width;
    }

    public String toString(){
        sortBoard();
        int count = 0;
        String Board = "";

        for(Tile tile : board){
            Board+=tile.toCharString();
            if(count < width - 1) {
                count++;
            }
            else {
                Board+="\n";
                count = 0;
            }
        }
        return Board;
    }

    public Tile getTile(Position p){
        for (Tile tile: board) {
            if(tile.getPosition().equals(p))
                return tile;
        }
        return null;
    }

    public void resetTile(Unit u){
        board.remove(u);
        board.add(new Empty(u.getPosition()));
        sortBoard();
    }

    private void sortBoard(){
        Comparator<Tile> comp = new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                int o1Y = o1.getPosition().getYPosition();
                int o2Y = o2.getPosition().getYPosition();
                if(o1Y < o2Y)
                    return -1;
                else if (o1Y > o2Y)
                    return 1;
                else{
                    int o1X = o1.getPosition().getXPosition();
                    int o2X = o2.getPosition().getXPosition();
                    if(o1X < o2X)
                        return -1;
                    else if(o1X > o2X)
                        return 1;
                    else
                        return 0;
                }
            }
        };
        board = board.stream().sorted(comp).collect(Collectors.toList());
    }


}
