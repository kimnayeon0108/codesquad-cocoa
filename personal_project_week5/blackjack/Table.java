package blackjack;

import javax.sound.midi.Soundbank;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Table {
    private Scanner s = new Scanner(System.in);
    private int buyingChips;
    private String getName;
    private int numOfHands;
    private final int MAX_HANDS = 3;
    private Player p;
    private House h;
    private Card c;
    private Dealer d;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public Table() {

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
                + "\t현금 : " + formatter.format(p.money) + "\t칩스 잔액 : " + p.chips);
    }

    private void buyChips() {

        System.out.println("구매를 원하는 칩스의 금액을 입력해주세요.");
        buyingChips = s.nextInt();
        if (buyingChips % 10000 != 0){
            System.out.println("만원 단위로만 구매가 가능합니다. 다시 입력해주세요.");
            buyChips();
        }
        p.money = p.money - buyingChips;
        p.chips = p.chips + buyingChips;
        h.balance = h.balance - buyingChips;

        System.out.println("칩스 " + formatter.format(buyingChips) + "이 생성되었습니다.");
        System.out.println("칩스 잔액 : " + formatter.format(p.chips));
    }

    private void startGame() {
        // 핸드 수 지정, 베팅 금액 설정
        chooseNumOfHands();
        divideCards(this.numOfHands);

    }

    private void divideCards(int numOfHands) {
        c = new Card();     // Card 객체 생성과 동시에, 카드 셔플 진행 완료
        d = new Dealer();
        switch (numOfHands) {
            case 1:
                p.firstCard = c.sixDeckCard[1];
                d.firstCard = c.sixDeckCard[2];
                p.secondCard = c.sixDeckCard[3];
        }


    }

    private int chooseNumOfHands() {
        System.out.println("플레이하고 싶은 Hand 수를 입력하세요. (1-3)");
        this.numOfHands = s.nextInt();
        if (numOfHands > MAX_HANDS || numOfHands < 1) {
            System.out.println("Three Hands 까지만 베팅이 가능합니다.\n다시 입력해 주세요.");
            chooseNumOfHands();
        }
        return numOfHands;
    }

    public static void main(String[] args) {
        Table t = new Table();

    }
}
