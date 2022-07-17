package Frontend;

import Backend.Tiles.Empty;
import Backend.Tiles.Tile;
import Backend.Tiles.Wall;
import Backend.Units.*;
import Backend.objectFields.Fields.Position;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

public class TileController {
    private static HashMap<Character, Function<Position,Enemy>> enemies = new HashMap<Character, Function<Position,Enemy>>(){
        {
            put('s',(position) -> new Monster('s',position,"Lannister Solider",80,8,3,25,3));
            put('k', (position) -> new Monster('k',position,"Lannister Knight",200,14,8,50,4));
            put('q', (position) -> new Monster('q',position,"Queen’s Guard",400,20,15,100,3));
            put('z', (position) -> new Monster('z',position,"Wright",600,30,15,100,3));
            put('b', (position) -> new Monster('b',position,"Bear-Wright",1000,75,30,250,4));
            put('g', (position) -> new Monster('g',position,"Giant-Wright",1500,100,40,500,5));
            put('w', (position) -> new Monster('w',position,"White Walker",2000,150,50,1000,6));
            put('M', (position) -> new Monster('M',position,"The Mountain",1000,60,25,500,6));
            put('C', (position) -> new Monster('C',position,"Queen Cersei",100,10,10,1000,1));
            put('K', (position) -> new Monster('K',position,"Night’s King",5000,300,150,5000,8));
            put('B', (position) -> new Trap('B',position,"Bonus Trap",1,1,1,250,1,5));
            put('Q', (position) -> new Trap('Q',position,"Queen’s Trap",250,50,10,100,3,7));
            put('D', (position) -> new Trap('D',position,"Death Trap",500,100,20,250,1,10));
        }
    };

    private static HashMap<Character, Function<Position,Tile>> tiles = new HashMap<>(){
        {
            put(Empty.CHAR,(position) -> new Empty(position));
            put(Wall.CHAR, (position) -> new Wall(position));
        }
    };

    private static HashMap<Integer,Player> players = new HashMap<>(){
        {
            put(1, new Warrior("Jon Snow",300,30,4,3));
            put(2, new Warrior("The Hound",400,20,6,5));
            put(3, new Mage("Melisandre",100,5,1,300,30,15,5,6));
            put(4, new Mage("Thoros of Myr",250,25,4,150,20,20,3,4));
            put(5, new Rogue("Arya stark",150,40,2,20));
            put(6, new Rogue("Bronn",250,35,3,50));
        }
    };

    public Function<Position,Tile> getTile(char key){
        return tiles.get(key);
    }

    public Function<Position,Enemy> getEnemy(char key){
        return enemies.get(key);
    }

    public Player choosePlayer(){
        System.out.println("\nplease choose your player:\n");
        showPlayers();
        Scanner scanner = new Scanner(System.in);
        int player = scanner.nextInt();
        while(player > 6 | player < 1) {
            System.out.println("please enter a valid key:");
            player = scanner.nextInt();
        }
        return players.get(player);
    }

    private void showPlayers(){
        for (int key:players.keySet()) {
            System.out.println(key + ". "+ players.get(key).toString() + "\n");
        }
    }
}
