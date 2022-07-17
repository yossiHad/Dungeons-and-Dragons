package Frontend;

import Backend.Units.Player;
import Backend.objectFields.Fields.Movement;

import java.util.Scanner;

public class InputProvider {

    private static Scanner scanner = new Scanner(System.in);
    private char[] keys = new char[6];

    public void chooseInput(){
        String[] keyWords = {"moving up","moving down","moving left","moving right","skip turn","cast special ability"};
        int i = 0;
        while (i < keyWords.length){
            System.out.printf("\nplease choose the key for %S :%n",keyWords[i]);
            char key = chooseKey();
            while (contains(keys,key)) {
                System.out.println("please choose key you haven't chose yet:");
                key = chooseKey();
            }
            if(i == 0)
                Movement.up = key;
            else if(i == 1)
                Movement.down = key;
            else if(i == 2)
                Movement.left = key;
            else if(i == 3)
                Movement.right = key;
            else if(i == 4)
                Movement.skipTurn = key;
            else
                Player.specialAbility = key;
            keys[i] = key;
            if(i == 5)
                Movement.build();
            i++;
        }
    }

    private boolean contains(char[] arr, char c){
        for (char h: arr) {
            if(h == c)
                return true;
        }
        return false;
    }

    public char chooseKey(){
        String input = scanner.next();
        while (input.length() != 1){
            System.out.println("please choose only one key");
            input = scanner.next();
        }
        return input.charAt(0);
    }

    public char chooseMovement(){
        String input = scanner.next();
        while (input.length() != 1 | !contains(keys,input.charAt(0))){
            System.out.println("please choose a valid key");
            input = scanner.next();
        }
        return input.charAt(0);
    }
}
