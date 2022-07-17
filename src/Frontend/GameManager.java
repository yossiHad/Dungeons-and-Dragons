package Frontend;

import Backend.Units.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private String path;
    private int level;
    private int numberOfLevels;
    private Player player;

    public GameManager(String path){
        this.path = path;
        level = 1;
        File directory = new File(path);
        numberOfLevels = directory.list().length;
    }

    public void play(){
        InputProvider inputProvider = new InputProvider();
        inputProvider.chooseInput();
        choosePlayer();
        while (level <= numberOfLevels & player.alive()){
            List<String> lines = loadLevel();
            LevelManager levelManager = new LevelManager(lines, player, inputProvider);
            levelManager.play();
            if(player.alive() & level<=numberOfLevels)
                System.out.println("you have leveled up to level: " + level);
        }
        if(player.alive())
            System.out.println("YOU WON!");
        else
            System.out.println("YOU LOST!");
        System.out.println("GAME OVER");
    }

    public List<String> loadLevel(){
        String levelPath = String.format(path+"\\level%d.txt",level++);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(levelPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    private void choosePlayer(){
        TileController tileController = new TileController();
        player = tileController.choosePlayer();
        System.out.println(String.format("your choice: %s\nENJOY!",player.getName()));
    }
}
