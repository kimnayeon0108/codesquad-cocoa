package blackjack;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Table {
    private Scanner s = new Scanner(System.in);
    private int buyingChips;
    private String getName;
    private int numOfHands;
    private Player p;
    private House h;
    private Card c;

    public Table(){

        initPlayer();
        initHouse();
        buyChips();
        startGame();
    }

    private void initHouse() {
        h = new House();
    }

    private void initPlayer() {
        System.out.println("사용자의 이름을 입력해주세요.");
        getName = s.next();
        p = new Player(getName);
        System.out.println("사용자가 생성되었습니다. \n\t이름 :" + getName
                + "\t현금 : " + p.money + "\t칩스 잔액 : " + p.chips);
    }

    private void buyChips() {

            System.out.println("구매를 원하는 칩스의 금액을 입력해주세요.");
            buyingChips = s.nextInt();
            p.money = p.money - buyingChips;
            p.chips = p.chips + buyingChips;
            h.balance = h.balance - buyingChips;

            System.out.println("칩스 " + buyingChips + "이 생성되었습니다.");
            System.out.println("칩스 잔액 : " + p.chips);
    }

    private void startGame() {
        // 핸드 수 지정, 베팅 금액 설정
        chooseNumOfHands();
        divideCards();

    }

    private void divideCards() {
        c = new Card();


    }

    private void chooseNumOfHands() {
        System.out.println("플레이하고 싶은 Hand 수를 입력하세요. (1-3)");
        numOfHands = s.nextInt();
    }
}
