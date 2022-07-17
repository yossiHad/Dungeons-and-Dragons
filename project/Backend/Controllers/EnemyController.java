package Backend.Controllers;

import Backend.Tiles.Unit;
import Backend.Units.Enemy;
import Backend.objectFields.Fields.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnemyController {
    private List<Enemy> enemies;
    private BoardController boardController;

    public EnemyController(){
        enemies = new ArrayList<>();
    }

    public void setBoard(BoardController board){
        boardController = board;
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies(Position p, int range){
        return enemies.stream().filter(e -> Position.range(e.getPosition(),p) < range).collect(Collectors.toList());
    }
    public void performTick(Position position){
        for (Enemy e: enemies) {
            Position p = e.tick(position);
            e.interact(boardController.getTile(p));
        }
    }
    public void removeEnemy(Unit u){
        enemies.remove(u);
    }
    public boolean levelEnd(){
        return enemies.isEmpty();
    }
}
