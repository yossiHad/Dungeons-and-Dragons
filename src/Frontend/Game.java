package Frontend;

public class Game {
    public static void main(String[] args) {
        String path = args[0];
        GameManager g = new GameManager(path);
        g.play();
    }
}
