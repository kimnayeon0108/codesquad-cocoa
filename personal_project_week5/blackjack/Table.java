package blackjack;

import javax.sound.midi.Soundbank;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private boolean hit[];
    private ArrayList<boolean[]> hitArrList;
    private ArrayList<String> dividedCard;

    private DecimalFormat formatter = new DecimalFormat("###,###,###");

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
        divideCards(numOfHands);
        chooseHitOrStay();
    }

    private void chooseHitOrStay() {
        hitArrList = new ArrayList<boolean[]>();     // hand 별로 hit stay 담는 arraylist
        hit = new boolean[handsArr.length];         // hand 별 hit stay 여부

        for (int i = 0; i < handsArr.length; i++) {     // hand 수만큼 hit stay 묻기  
            askHisStay(i);
        }
    }

    private void askHisStay(int i) {
        System.out.println("\n=======" + handsArr[i].handsNum + "번 핸드 hit or stay (숫자를 입력하세요. 1번 : hit, 2번 : stay) =======");
        int hitOrStay = s.nextInt();
        if (hitOrStay == 1) {       // hit 일 경우
            int a = 1;
            int total = handsArr[i].totalValue + c.valueArr[dividedCard.size() + a];
            System.out.printf("\n%s 카드를 받았습니다. total value: %d \n", c.sixDeckCard[dividedCard.size() + a], total);
            if (total > 21){
                System.out.printf("Over 21, %d번 핸드는 loosing 하였습니다.", handsArr[i].handsNum);
            } else{
                askHisStay(i);
            }
            a++;
        }
        if (hitOrStay == 2) {       // stay 일 경우
            System.out.println("stay");
        }
    }

//    private void hitStay(int i, boolean[] hit) {
//        if (hit[i] == true) {
//            int total = handsArr[i].totalValue + c.valueArr[dividedCard.size() + (i + 1)];
//            System.out.printf("\n%s 카드를 받았습니다. total value: %d \n", c.sixDeckCard[dividedCard.size() + (i + 1)], total);
//            if (total > 21){
//                System.out.printf("Over 21, %d번 핸드는 loosing 하였습니다.", handsArr[i].handsNum);
//            } else{
//                askHisStay(i);
//            }
//        }
//        if (hit[i] == false){
//            System.out.println("stay");
//        }
//    }


    private void divideCards(int numOfHands) {
        c = new Card();     // Card 객체 생성과 동시에, 카드 셔플 진행 완료

        // 나눠줄 카드 배열에 담기, hit stay 받으면서 divided 카드 늘어나니깐 arrayList 로
        dividedCard = new ArrayList<>();
        for (int i = 0; i < numOfHands * 2 + 1; i++) {
            dividedCard.add(c.sixDeckCard[i]);
        }

        switch (numOfHands) {   //Todo: 이부분 잘 생각하면 함수로 반복문으로 만들 수 있을거 같은데.. 고민해보기
            case 1:
                handsArr[0].firstCard = this.dividedCard.get(0);
                d.firstCard = this.dividedCard.get(1);
                handsArr[0].secondCard = this.dividedCard.get(2);
                break;
            case 2:
                handsArr[0].firstCard = this.dividedCard.get(0);
                handsArr[1].firstCard = this.dividedCard.get(1);
                d.firstCard = this.dividedCard.get(2);
                handsArr[0].secondCard = this.dividedCard.get(3);
                handsArr[1].secondCard = this.dividedCard.get(4);
                break;
            case 3:
                handsArr[0].firstCard = this.dividedCard.get(0);
                handsArr[1].firstCard = this.dividedCard.get(1);
                handsArr[2].firstCard = this.dividedCard.get(2);
                d.firstCard = this.dividedCard.get(3);
                handsArr[0].secondCard = this.dividedCard.get(4);
                handsArr[1].secondCard = this.dividedCard.get(5);
                handsArr[2].secondCard = this.dividedCard.get(6);
                break;
        }

        sumValue(numOfHands);       // 핸드별로 total value 얻기
        printDividedCards();

    }

    private void sumValue(int numOfHands) {
        if (numOfHands == 1) {
            handsArr[0].totalValue = c.valueArr[0] + c.valueArr[2];
        }
        if (numOfHands == 2) {
            handsArr[0].totalValue = c.valueArr[0] + c.valueArr[3];
            handsArr[1].totalValue = c.valueArr[1] + c.valueArr[4];
        }
        if (numOfHands == 3) {
            handsArr[0].totalValue = c.valueArr[0] + c.valueArr[4];
            handsArr[1].totalValue = c.valueArr[1] + c.valueArr[5];
            handsArr[2].totalValue = c.valueArr[2] + c.valueArr[6];
        }
    }

    private void printDividedCards() {
        for (int i = 0; i < handsArr.length; i++) {
            System.out.printf("Hand %d: %s, %s\t total value: %d \n", handsArr[i].handsNum,
                    handsArr[i].firstCard, handsArr[i].secondCard, handsArr[i].totalValue);
        }
        System.out.printf("딜러 카드: %s", d.firstCard);
    }

    private Hands[] getHands(int numOfHands) {
        // player 가 지정한 hands 수 만큼 hands 객체 생성해서 배열에 값 담기
        handsArr = new Hands[numOfHands];
        for (int i = 0; i < handsArr.length; i++) {
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
        return numOfHands;
    }

    public static void main(String[] args) {
        Table t = new Table();
    }
}
