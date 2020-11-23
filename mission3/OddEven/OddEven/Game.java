package OddEven;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public String result;
    public String choice;
    public int numStage;
    public int bettingAmount;
    public  final int MAXSTAGE = 8;
    Opponent o1 = new Opponent();
    Scanner s = new Scanner(System.in);
    Rank rank = new Rank();


    public void play(Player p1) {
        numStage = 1;
        while (p1.amount > 0 && o1.amount > 0 && numStage <= MAXSTAGE) {
            System.out.println("베팅금액을 입력하시오.");
            bettingAmount = s.nextInt();
            p1.numTurn++;

            if (bettingAmount > p1.amount || bettingAmount > o1.amount) {
                System.out.println("베팅금액을 다시 입력하시오. 보유 금액: " + p1.amount
                        + " 상대 보유 금액:" + o1.amount);
                bettingAmount = s.nextInt();
            }

            System.out.println("홀 또는 짝을 입력하시오.");
            choice = s.next();

            if (choice.equals(whatIsResult())) {
                System.out.println("맞았습니다!");
                p1.amount += bettingAmount;
                o1.amount -= bettingAmount;
                System.out.println(bettingAmount + "을 획득했습니다.\n보유 금액: " + p1.amount);
            } else {
                System.out.println("틀렸습니다!");
                p1.amount -= bettingAmount;
                o1.amount += bettingAmount;
                System.out.println(bettingAmount + "을 루징하였습니다.\n보유 금액: " + p1.amount);
            }

            // 새로운 상대 생성
            if (o1.amount == 0) {
                numStage++;
                System.out.println("-----new game stage: " + numStage + "-----");
                o1.amount = p1.amount * (int) Math.pow(1.2, numStage);
                System.out.println("새로운 상대가 생성되었습니다. 상대의 보유 금액: " + o1.amount);
            }
        }
        // player amount 소진시 게임 종료
        if (p1.amount == 0) {
            System.out.println("보유 금액이 0원입니다. 게임이 종료되었습니다.");
            System.out.println("stage: " + (numStage) + " 시도 횟수: " + p1.numTurn);
        }
        // 스테이지 정복 게임 종료
        if (numStage == MAXSTAGE) {
            System.out.println("게임이 종료되었습니다.");
            System.out.println("stage: " + (numStage - 1) + " 시도 횟수: " + p1.numTurn + " 보유 금액: " + p1.amount);

        }
        try {
            rank.saveData(p1.name, p1.numTurn, p1.amount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.close();
    }

    public String whatIsResult() {
        Random rand = new Random();
        int randomNum = rand.nextInt(20) + 1;
        if (randomNum % 2 == 0) {
            result = "짝";
        }
        if (randomNum % 2 == 1) {
            result = "홀";
        }
        return result;
    }



}
