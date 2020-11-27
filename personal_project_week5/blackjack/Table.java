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
    private int[] valueArr;
    private int value;
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
        divideCards(c);
        hitStay();
    }

    private void hitStay() {
    }

    private int getCardValue(String card) {
        //Todo: 이부분 너무 하드코딩 인듯 ㅠㅠ 나중에 다시 생각해보기
        switch (card) {
            case "\u001B[31ma♥\u001B[0m": case "a♠": case "\u001B[31ma♦\u001B[0m": case "a♣":
                value = 1;
                break;
            case "\u001B[31m2♥\u001B[0m": case "2♠": case "\u001B[31m2♦\u001B[0m": case "2♣":
                value = 2;
                break;
            case "\u001B[31m3♥\u001B[0m": case "3♠": case "\u001B[31m3♦\u001B[0m": case "3♣":
                value = 3;
                break;
            case "\u001B[31m4♥\u001B[0m": case "4♠": case "\u001B[31m4♦\u001B[0m": case "4♣":
                value = 4;
                break;
            case "\u001B[31m5♥\u001B[0m": case "5♠": case "\u001B[31m5♦\u001B[0m": case "5♣":
                value = 5;
                break;
            case "\u001B[31m6♥\u001B[0m": case "6♠": case "\u001B[31m6♦\u001B[0m": case "6♣":
                value = 6;
                break;
            case "\u001B[31m7♥\u001B[0m": case "7♠": case "\u001B[31m7♦\u001B[0m": case "7♣":
                value = 7;
                break;
            case "\u001B[31m8♥\u001B[0m": case "8♠": case "\u001B[31m8♦\u001B[0m": case "8♣":
                value = 8;
                break;
            case "\u001B[31m9♥\u001B[0m": case "9♠": case "\u001B[31m9♦\u001B[0m": case "9♣":
                value = 9;
                break;
            case "\u001B[31m10♥\u001B[0m": case "10♠": case "\u001B[31m10♦\u001B[0m": case "10♣":
            case "\u001B[31mJ♥\u001B[0m": case "J♠": case "\u001B[31mJ♦\u001B[0m": case "J♣":
            case "\u001B[31mQ♥\u001B[0m": case "Q♠": case "\u001B[31mQ♦\u001B[0m": case "Q♣":
            case "\u001B[31mK♥\u001B[0m": case "K♠": case "\u001B[31mK♦\u001B[0m": case "K♣":
                value = 10;
                break;
        }
        return value;
    }


    private void divideCards(Card c) {
        c = new Card();     // Card 객체 생성과 동시에, 카드 셔플 진행 완료

        // 나눠줄 카드 배열에 담기
        String[] dividedCard = new String[(handsArr.length * 2) + 1];
        valueArr = new int[dividedCard.length];

        for(int i = 0; i < dividedCard.length; i++){
            dividedCard[i] = c.sixDeckCard[i];
            valueArr[i] = getCardValue(dividedCard[i]);     // divided 된 카드의 value 배열 생성
        }

        switch (numOfHands) {   //Todo: 이부분 잘 생각하면 함수로 반복문으로 만들 수 있을거 같은데.. 고민해보기
            case 1:
                handsArr[0].firstCard = dividedCard[0];
                d.firstCard = dividedCard[1];
                handsArr[0].secondCard = dividedCard[2];
                break;
            case 2:
                handsArr[0].firstCard = dividedCard[0];
                handsArr[1].firstCard = dividedCard[1];
                d.firstCard = dividedCard[2];
                handsArr[0].secondCard = dividedCard[3];
                handsArr[1].secondCard = dividedCard[4];
                break;
            case 3:
                handsArr[0].firstCard = dividedCard[0];
                handsArr[1].firstCard = dividedCard[1];
                handsArr[2].firstCard = dividedCard[2];
                d.firstCard = dividedCard[3];
                handsArr[0].secondCard = dividedCard[4];
                handsArr[1].secondCard = dividedCard[5];
                handsArr[2].secondCard = dividedCard[6];
                break;
        }

        sumValue(numOfHands);       // 핸드번호 total
        printDividedCards();

    }

    private void sumValue(int numOfHands) {
        if(numOfHands == 1){
            handsArr[0].totalValue = valueArr[0] + valueArr[2];
        }
        if(numOfHands == 2){
            handsArr[0].totalValue = valueArr[0] + valueArr[3];
            handsArr[1].totalValue = valueArr[1] + valueArr[4];
        }
        if (numOfHands == 3){
            handsArr[0].totalValue = valueArr[0] + valueArr[4];
            handsArr[1].totalValue = valueArr[1] + valueArr[5];
            handsArr[2].totalValue = valueArr[2] + valueArr[6];
        }
    }

    private void printDividedCards() {
        for(int i = 0; i < handsArr.length; i++){
            System.out.printf("Hand %d: %s, %s\t total value: %d \n", handsArr[i].handsNum,
                    handsArr[i].firstCard, handsArr[i].secondCard, handsArr[i].totalValue);
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
