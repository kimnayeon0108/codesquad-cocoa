import java.util.Random;
import java.util.Scanner;

public class RPGgameProject {
    String[][] array1 = new String[11][11];
    String character = "character";
    String monster = "monster";
    String landMine = "landMine";
    Random rand = new Random();

    array1[5][5] = character;
    array1[rand.nextInt(11)][rand.nextInt(11)] = monster;
    array1[rand.nextInt(11)][rand.nextInt(11)] = landMine;

    System.out.println("W, A, S, D 중 하나를 입력하세요.")
    Scanner scanner = new Scanner(System.in);


    switch (scanner.next()) {
        case "W":

        case "A":

        case "S":

        case "D":


    }

    public static void main(String[] args){}


}
