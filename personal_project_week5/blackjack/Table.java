package blackjack;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    private Scanner s = new Scanner(System.in);
    private int buyingChips;
    private int numOfHands;
    private final int MAX_HANDS = 3;

    private Player p;
    private House house;
    private Card c;
    private Dealer d = new Dealer();
    private Hands[] handsArr;

    private int oneMoreCard = 0;
    private int over = 0;    // 핸드 별로 오버시 1씩 커짐

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
        String getName = s.next();
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
        // 전부 다 over 되지 않은 경우에만 dealerGetCard() 실행
        if(this.over != numOfHands) dealerGetCard();
    }

    private void dealerGetCard() {
        System.out.printf("\n======= 딜러 =======\n현재 딜러 카드: %s", d.firstCard);

        boolean ace;        // ace 갖고 있는지 아닌지

        String nextCard = c.sixDeckCard[dividedCard.size() + oneMoreCard];
        int valueOfNextCard = c.valueArr[dividedCard.size() + oneMoreCard];

        if(d.value == 1|| valueOfNextCard == 1){
            ace = true;
        } else {
            ace = false;
        }

        //Todo: 추가로 받는 카드가 a 일 경우 구현하기
        for (int i = 0; d.value < 17; i++) {
            System.out.printf("\n%s를 받았습니다.", nextCard);
            oneMoreCard++;

            // 딜러 blackjack 인 경우
            if (d.value == 1 && valueOfNextCard == 10) {
                d.dBlackjack = true;
                System.out.println("딜러 blackjack ...");
                return;
            }

            // 딜러 카드 중 ace가 있고, total value 가 7 이상이면 게임 종료
            if (ace && d.value + valueOfNextCard >= 7) {
                System.out.printf("\n딜러 카드의 total value: %d", d.value + valueOfNextCard + 10);
                return;
            }

            // 딜러 카드 중 ace가 있고, total value 가 7 이하일 경우
            if (ace && d.value + valueOfNextCard < 7) {
                   System.out.printf("\n딜러 카드의 total value: %d or %d",
                                        d.value + valueOfNextCard, d.value + valueOfNextCard + 10);
                   d.value += valueOfNextCard;
            }

            // 딜러 카드 중 ace 가 없는 경우
            if(!ace){
                System.out.printf("\n딜러 카드의 total value: %d", d.value + valueOfNextCard);
                d.value += valueOfNextCard;
            }

            // 딜러 21 이상 blackjack
            if (d.value > 21) {
                d.dBust = true;
                System.out.println("딜러 bust!!! congratulations!!");
            }

        }
    }

    private void chooseHitOrStay() {

        for (int i = 0; i < handsArr.length; i++) {     // hand 수만큼 hit stay 묻기
            askHisStay(i);
        }
    }

    private void askHisStay(int i) {
        int hitOrStay;
        boolean hit = false;

        System.out.println("\n=======" + handsArr[i].handsNum + "번 핸드 hit or stay (숫자를 입력하세요. 1번 : hit, 2번 : stay) =======");
        hitOrStay = s.nextInt();

        if(hitOrStay == 1){
            hit = true;
        }
        if(hitOrStay == 2){
            hit = false;
        }

        // hit 일 경우
        if (hit) {
            oneMoreCard++;
            handsArr[i].totalValue += c.valueArr[dividedCard.size() + oneMoreCard];
            System.out.printf("\n%s 카드를 받았습니다. total value: %d",
                    c.sixDeckCard[dividedCard.size() + oneMoreCard], handsArr[i].totalValue);

            // Over 21 인 경우
            if (handsArr[i].totalValue > 21) {
                System.out.printf("\nOver 21, %d번 핸드는 loosing 하였습니다.\n", handsArr[i].handsNum);
                over++;
            } else {
                askHisStay(i);
            }
        }

        // stay 일 경우
        if (!hit) {
            System.out.println("stay");
        }
    }

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

        handsValue(numOfHands);       // 핸드별로 total value 얻기
        printDividedCards();
    }

    private void handsValue(int numOfHands) {
        if (numOfHands == 1) {
            handsArr[0].firstValue = c.valueArr[0];
            d.value = c.valueArr[1];
            handsArr[0].secondValue = c.valueArr[2];
        }
        if (numOfHands == 2) {
            handsArr[0].firstValue = c.valueArr[0];
            handsArr[1].firstValue = c.valueArr[1];
            d.value = c.valueArr[2];
            handsArr[0].secondValue = c.valueArr[3];
            handsArr[1].secondValue = c.valueArr[4];
        }
        if (numOfHands == 3) {
            handsArr[0].firstValue = c.valueArr[0];
            handsArr[1].firstValue = c.valueArr[1];
            handsArr[2].firstValue = c.valueArr[2];
            d.value = c.valueArr[3];
            handsArr[0].secondValue = c.valueArr[4];
            handsArr[1].secondValue = c.valueArr[5];
            handsArr[2].secondValue = c.valueArr[6];
        }
        // hands 별 total value 구하기
        for(int i = 0; i < handsArr.length; i++){
            handsArr[i].totalValue = handsArr[i].firstValue + handsArr[i].secondValue;
        }
    }

    private void printDividedCards() {
        for (int i = 0; i < handsArr.length; i++) {
            // 카드가 ace 일 경우 value 1 or 11
            if (handsArr[i].firstValue == 1 || handsArr[i].secondValue == 1) {
                System.out.printf("Hand %d: %s, %s\t total value: %d or %d \n", handsArr[i].handsNum,
                        handsArr[i].firstCard, handsArr[i].secondCard, handsArr[i].totalValue, handsArr[i].totalValue + 10);
            } else {
                System.out.printf("Hand %d: %s, %s\t total value: %d \n", handsArr[i].handsNum,
                        handsArr[i].firstCard, handsArr[i].secondCard, handsArr[i].totalValue);
            }
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
