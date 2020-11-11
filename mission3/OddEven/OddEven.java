package OddEven;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Player {
    String name;
    int amount = 100;
}

class Opponent {
    String name = "opponent";
    int amount = 120;
}

class SaveData {
    ArrayList<Player> playerList = new ArrayList<Player>();
    File file = new File("/Users/kimnayeon/codesquad-cocoa/mission3/OddEven/player.txt");

    public void fileCreate() {
        try {
            if (file.exists()) {
                file.canWrite();
            }
        } catch (Exception e) {
            System.out.println("생성 오류 발생");
        }
    }

    public ArrayList<Player> fileRead() {
        
    }

    public void fileWrite(ArrayList<Player> arrayList){

    }
}

class OddEven {
    String result;
    String choice;
    int numStage = 1;
    int numTurn = 0;
    int maxStage = 8;
    Player p1 = new Player();
    Opponent o1 = new Opponent();

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

    public void play() {
        Player p1 = new Player();
        Opponent o1 = new Opponent();
        Scanner s = new Scanner(System.in);

        while (p1.amount > 0 && o1.amount > 0 && numStage < maxStage) {
            System.out.println("베팅금액을 입력하시오.");
            int bettingAmount = s.nextInt();
            numTurn++;

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
            System.out.println("stage: " + (numStage) + " 시도 횟수: " + numTurn);
        }
        // 스테이지 정복 게임 종료
        if (numStage == maxStage) {
            System.out.println("게임이 종료되었습니다.");
            System.out.println("stage: " + (numStage - 1) + " 시도 횟수: " + numTurn + " 보유 금액: " + p1.amount);

        }
    }

    public void createPlayer() {
        // 같은 이름의 사용자가 있으면 오류가 뜨고, 새로 생성하는 메세지 호출하기
        System.out.println("이름을 입력하시오.");
        Scanner s = new Scanner(System.in);
        p1.name = s.next();
        System.out.println("보유 금액: " + p1.amount + "이 생성되었습니다.\n게임을 시작합니다.");
        System.out.println("------------------");
    }


}

class Main {
    public static void main(String[] args) {
        OddEven oe = new OddEven();
        SaveData sd = new SaveData();

        System.out.println("홀짝 게임에 오신것을 환영합니다. \n사용자를 등록해주세요.");
        System.out.println("-----------------");
        oe.createPlayer();
        oe.play();
        sd.fileCreate();
        sd.fileRead();
    }
}

