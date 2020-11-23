package OddEven;

import java.io.IOException;
import java.util.Scanner;

public class OddEven {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Scanner s = new Scanner(System.in);

        System.out.println("홀짝 게임에 오신것을 환영합니다. \n이름을 입력하시오");
        System.out.println("-----------------");

        String inputName = s.next();
        Player p1 = new Player(inputName);
        p1.createPlayer();
        game.play(p1);

        s.close();
    }
}
