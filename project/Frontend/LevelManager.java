package Frontend;

import Backend.Controllers.*;
import Backend.Messages.MessageCallBack;
import Backend.Tiles.*;
import Backend.Units.Enemy;
import Backend.Units.Player;
import Backend.objectFields.Fields.Movement;
import Backend.objectFields.Fields.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


public class LevelManager {

    private InputProvider inputProvider;
    private TileController tileController;
    private static int level = 1;
    private BoardController board;
    private EnemyController enemyController;
    private Player player;

    public LevelManager(List<String> lines,Player player, InputProvider inputProvider){
        tileController = new TileController();
        enemyController = new EnemyController();
        this.player = player;
        loadLevel(lines);
        this.inputProvider = inputProvider;
    }

    public void play(){
        while (!enemyController.levelEnd() & player.alive()) {
            System.out.println(board);
            System.out.println(player);
            performTick();
        }
    }

    private void loadLevel(List<String> lines){
        List<Tile> Board = new ArrayList<>();
        int height = lines.size();
        int width = lines.get(0).length();
        int x = 0;
        int y = 0;
        Iterator<String> iter = lines.iterator();
        while (iter.hasNext()){
            String line = iter.next();
            for(int i = 0; i < line.length(); i++){
                MessageCallBack messageCallBack = (msg) -> send(msg);
                Position position = new Position(x,y);
                char key = line.charAt(i);
                if(key == Player.CHAR){
                    player.setPosition(position);
                    player.setMessageCallBack(messageCallBack);
                    player.setDeathCallBack((enemy) -> playerDead(enemy));
                    Board.add(player);
                }
                else if(key == Empty.CHAR | key == Wall.CHAR)
                    Board.add(tileController.getTile(key).apply(position));
                else{
                    Function<Position,Enemy> function = tileController.getEnemy(key);
                    Enemy e = function.apply(position);
                    e.setMessageCallBack(messageCallBack);
                    e.setDeathCallBack((u) -> enemyDead(u));
                    Board.add(e);
                    enemyController.addEnemy(e);
                }
                y = x < width - 1 ? y : y + 1;
                x = x < width - 1 ? x + 1 : 0;
            }
        }
        board = new BoardController(Board,height,width);
        enemyController.setBoard(board);
    }

    private void performTick(){
        performPlayerTick();
        performEnemyTick();
    }

    private void performPlayerTick(){
        System.out.println("your turn:\n");
        char move = inputProvider.chooseMovement();
        if(Movement.movements.containsKey(move)){
            Function<Position,Position> function = Movement.movements.get(move);
            Position p = function.apply((player.getPosition()));
            player.interact(board.getTile(p));
        }
        else {
            player.cast(enemyController.getEnemies(player.getPosition(),player.getRange()));
        }
    }
    private void performEnemyTick(){
        System.out.println("\nenemies turn: \n");
        enemyController.performTick(player.getPosition());
    }

    public void send(String message){
        System.out.println(message);
    }

    public void playerDead(Unit u){
        player.deadChar();
        System.out.println(board);
        System.out.println(String.format("%s was killed by %s\n",player.getName(),u.getName()));
    }
    public void enemyDead(Unit u){
        System.out.println(u.getName() + " was killed by you\n");
        board.resetTile(u);
        enemyController.removeEnemy(u);
    }
}





