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
    private House house;
    private Card c;
    private Dealer d = new Dealer();
    private Hands[] handsArr;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public Table() {

        initPlayer();
        initHouse();
        buyChips();
        startGame();
    }

    private void initHouse() {
        house = new House();
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
        if (buyingChips % 10000 != 0) {
            System.out.println("만원 단위로만 구매가 가능합니다.");
            buyChips();
        }
        p.money = p.money - buyingChips;
        p.chips = p.chips + buyingChips;
        house.balance = house.balance - buyingChips;

        System.out.println("칩스 " + formatter.format(buyingChips) + "이 생성되었습니다.");
        System.out.println("칩스 잔액 : " + formatter.format(p.chips));
    }

    private void startGame() {
        // 핸드 수 지정, 베팅 금액 설정
        chooseNumOfHands();
        getHands(numOfHands);   // 받은 hands 수 만큼 array 생성
        divideCards(this.handsArr);

    }

    private void divideCards(Hands[] handsArr) {
        c = new Card();     // Card 객체 생성과 동시에, 카드 셔플 진행 완료

        switch (numOfHands) {
            case 1:
                handsArr[0].firstCard = c.sixDeckCard[0];
                d.firstCard = c.sixDeckCard[1];
                handsArr[0].secondCard = c.sixDeckCard[2];
                break;
            case 2:
                handsArr[0].firstCard = c.sixDeckCard[0];
                handsArr[1].firstCard = c.sixDeckCard[1];
                d.firstCard = c.sixDeckCard[2];
                handsArr[0].secondCard = c.sixDeckCard[3];
                handsArr[1].secondCard = c.sixDeckCard[4];
                break;
            case 3:
                handsArr[0].firstCard = c.sixDeckCard[0];
                handsArr[1].firstCard = c.sixDeckCard[1];
                handsArr[2].firstCard = c.sixDeckCard[2];
                d.firstCard = c.sixDeckCard[3];
                handsArr[0].secondCard = c.sixDeckCard[4];
                handsArr[1].secondCard = c.sixDeckCard[5];
                handsArr[2].secondCard = c.sixDeckCard[6];
                break;
        }

        printDividedCards();

    }

    private void printDividedCards() {
        for(int i = 0; i < handsArr.length; i++){
            System.out.printf("Hand %d: %s, %s\n", (i+1),
                    handsArr[i].firstCard, handsArr[i].secondCard);
        }
        System.out.printf("딜러 카드: %s", d.firstCard);

    }

    private Hands[] getHands(int numOfHands) {
        // player 가 지정한 hands 수 만큼 hands 객체 생성해서 배열에 값 담기
        handsArr = new Hands[numOfHands];
        for (int i = 0; i < numOfHands; i++) {
            handsArr[i] = new Hands();
            handsArr[i].handsNum = i + 1;
        }
        return handsArr;
    }

    private int chooseNumOfHands() {
        System.out.println("플레이하고 싶은 Hand 수를 입력하세요. (1-3)");
        this.numOfHands = s.nextInt();
        if (numOfHands > MAX_HANDS || numOfHands < 1) {
            System.out.println("Three Hands 까지만 베팅이 가능합니다.\n다시 입력해 주세요.");
            chooseNumOfHands();
        }
        return this.numOfHands;     // 여기 this 붙이면 뭔가 이상한거 같은데..? 오류는 안나지만 .. 뭔가 어색
    }

    public static void main(String[] args) {
        Table t = new Table();

    }
}
