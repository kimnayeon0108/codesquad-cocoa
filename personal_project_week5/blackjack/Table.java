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
    private Card c;
    private Dealer d = new Dealer();
    private Hands[] handsArr;

    private int oneMoreCard = 0;        // 카드 한장씩 받을때마다 1씩 증가
    private int over = 0;    // 핸드 별로 오버시 1씩 커짐

    private ArrayList<String> dividedCard;

    private DecimalFormat formatter = new DecimalFormat("###,###,###");

    public Table() {

        initPlayer();
        buyChips();
        startGame();
    }

    private void initPlayer() {
        System.out.println("사용자의 이름을 입력해주세요.");
        String getName = s.next();
        p = new Player(getName);
        System.out.println("사용자가 생성되었습니다. \n\t이름 :" + getName
                + "\t현금 : " + formatter.format(p.money) + "\t칩스 잔액 : " + p.pChips);
    }

    private void buyChips() {

        System.out.println("구매를 원하는 칩스의 금액을 입력해주세요.");
        buyingChips = s.nextInt();
        if (buyingChips % 10000 != 0) {
            System.out.println("만원 단위로만 구매가 가능합니다.");
            buyChips();
        }
        p.money = p.money - buyingChips;
        p.pChips = p.pChips + buyingChips;
        d.dChips = d.dChips - buyingChips;

        System.out.println("칩스 " + formatter.format(buyingChips) + "이 생성되었습니다.");
        System.out.println("칩스 잔액 : " + formatter.format(p.pChips));
        System.out.println("딜러 보유 칩스 금액 : " + formatter.format(d.dChips));
    }

    private void startGame() {
        // 핸드 수 지정, 베팅 금액 설정
        chooseNumOfHands();
        getHands(numOfHands);   // 받은 hands 수 만큼 array 생성
        for (int w = 0; w < handsArr.length; w++) {
            betChips(w);
        }

        divideCards(numOfHands);
        chooseHitOrStay();

        // 전부 다 over 되지 않은 경우에만 dealerGetCard() 실행
        if (this.over != numOfHands) dealerGetCard();
    }

    private int chooseNumOfHands() {
        System.out.println("\n플레이하고 싶은 Hand 수를 입력하세요. (1-3)");
        this.numOfHands = s.nextInt();

        // Three hands 이상 선택시
        if (numOfHands > MAX_HANDS || numOfHands < 1) {
            System.out.println("Three Hands 까지만 베팅이 가능합니다.\n다시 입력해 주세요.");
            chooseNumOfHands();
        }

        // 보유 칩스 금액이 선택한 hands 수보다 적을 시
        if (p.pChips < numOfHands * 10000) {
            System.out.println("칩스 보유량이 부족합니다.\n칩스를 구매하고 싶으면 1, " +
                    "Hands 수를 다시 입력하려면 2를 입력하세요.");
            int input = s.nextInt();
            if (input == 1) {
                buyChips();
                chooseNumOfHands();
            }
            if (input == 2) {
                chooseNumOfHands();
            }
        }
        return numOfHands;
    }

    private void betChips(int w) {
        System.out.printf("===== Hands %d 베팅할 금액을 입력하세요. =====\n", handsArr[w].handsNum);
        handsArr[w].bAmount = s.nextInt();

        if (handsArr[w].bAmount % 10000 != 0) {
            System.out.println("만원 단위로 베팅이 가능합니다. 다시 입력해주세요.");
            betChips(w);
        }
        if (handsArr[w].bAmount > 100000) {
            System.out.println("maximum 10만원까지 베팅 가능합니다. 다시 입력해주세요.");
            betChips(w);
        }
        if (handsArr[w].bAmount > p.pChips) {
            System.out.println("보유 칩스량이 부족합니다. \n칩스를 구매하고 싶으면 1," +
                    "베팅금액을 다시 입력하려면 2를 입력하세요.");
            int input2 = s.nextInt();
            if (input2 == 1) {
                buyChips();
                betChips(w);
            }
            if (input2 == 2) {
                betChips(w);
            }
        }
        p.pChips = p.pChips - handsArr[w].bAmount;
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

    private void dealerGetCard() {
        System.out.printf("\n======= 딜러 =======\n현재 딜러 카드: %s", d.firstCard);

        for (int i = 0; d.value < 17; i++) {
            oneMoreCard++;

            String nextCard = c.sixDeckCard[dividedCard.size() + oneMoreCard];
            int valueOfNextCard = c.valueArr[dividedCard.size() + oneMoreCard];

            if (d.firstValue == 1 || valueOfNextCard == 1) {
                d.dAce = true;
            } else {
                d.dAce = false;
            }

            System.out.printf("\n%s를 받았습니다.", nextCard);

            // 딜러 blackjack 인 경우
            if ((d.value == 1 && valueOfNextCard == 10) || (d.value == 10 && valueOfNextCard == 1)) {
                d.dBlackjack = true;
                System.out.println("딜러 blackjack ...");
                return;
            }

            // 딜러 카드 중 ace가 있고, total value 가 7 이상이면 게임 종료
            if (d.dAce && d.value + valueOfNextCard >= 7) {

                System.out.printf("\n딜러 카드의 total value: %d", d.value + valueOfNextCard + 10);
                d.value = d.value + valueOfNextCard + 10;
                return;
            }

            // 딜러 카드 중 ace가 있고, total value 가 7 이하일 경우
            if (d.dAce && d.value + valueOfNextCard < 7) {
                System.out.printf("\n딜러 카드의 total value: %d or %d",
                        d.value + valueOfNextCard, d.value + valueOfNextCard + 10);
                d.value += valueOfNextCard;
            }

            // 딜러 카드 중 ace 가 없는 경우
            if (!d.dAce) {
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
            if (!handsArr[i].pBlackjack) askHisStay(i);
        }
    }

    private void askHisStay(int i) {
        int hitOrStay;
        boolean hit = false;

        System.out.println("\n=======" + handsArr[i].handsNum + "번 핸드 hit or stay (숫자를 입력하세요. 1번 : hit, 2번 : stay) =======");
        hitOrStay = s.nextInt();

        if (hitOrStay == 1) {
            hit = true;
        }
        if (hitOrStay == 2) {
            hit = false;
        }

        // hit 일 경우
        if (hit) {
            oneMoreCard++;
            handsArr[i].totalValue += c.valueArr[dividedCard.size() + oneMoreCard];

            // 핸드별로 받은 카드 중 ace 있고, 10 이하인 경우
            if ((c.valueArr[dividedCard.size() + oneMoreCard] == 1 || handsArr[i].pAce) && handsArr[i].totalValue < 10) {
                handsArr[i].pAce = true;

                System.out.printf("\n%s 카드를 받았습니다. total value: %d or %d",
                        c.sixDeckCard[dividedCard.size() + oneMoreCard], handsArr[i].totalValue, handsArr[i].totalValue + 10);
            } else {
                System.out.printf("\n%s 카드를 받았습니다. total value: %d",
                        c.sixDeckCard[dividedCard.size() + oneMoreCard], handsArr[i].totalValue);
            }

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

            // ace 포함되어있고, 더한값이 10 이하인 경우
            if (handsArr[i].pAce && handsArr[i].totalValue < 10) {

                handsArr[i].totalValue += 10;
            }
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
        for (int i = 0; i < handsArr.length; i++) {
            handsArr[i].totalValue = handsArr[i].firstValue + handsArr[i].secondValue;
        }
    }

    private void printDividedCards() {

        for (int i = 0; i < handsArr.length; i++) {

            // ace 카드 유무 여부
            if (handsArr[i].firstValue == 1 || handsArr[i].secondValue == 1) {
                handsArr[i].pAce = true;
            } else {
                handsArr[i].pAce = false;
            }

            // 해당 핸드 블랙잭인 경우
            if ((handsArr[i].firstValue == 10 || handsArr[i].secondValue == 10) && handsArr[i].pAce) {
                handsArr[i].pBlackjack = true;

                System.out.printf("Hand %d: %s, %s\t total value: blackjack!!! congrat!!! \n", handsArr[i].handsNum,
                        handsArr[i].firstCard, handsArr[i].secondCard);
            }

            // 블랙잭 아니고, 해당 핸드 ace 있을 경우
            if ((handsArr[i].firstValue != 10 && handsArr[i].secondValue != 10) && handsArr[i].pAce) {

                System.out.printf("Hand %d: %s, %s\t total value: %d or %d \n", handsArr[i].handsNum,
                        handsArr[i].firstCard, handsArr[i].secondCard, handsArr[i].totalValue, handsArr[i].totalValue + 10);
            }

            // 블랙잭 아니고, ace도 없는 경우
            if (!handsArr[i].pBlackjack && !handsArr[i].pAce) {

                System.out.printf("Hand %d: %s, %s\t total value: %d \n", handsArr[i].handsNum,
                        handsArr[i].firstCard, handsArr[i].secondCard, handsArr[i].totalValue);
            }
        }
        System.out.printf("딜러 카드: %s", d.firstCard);
    }

    public static void main(String[] args) {
        Table t = new Table();
    }
}
