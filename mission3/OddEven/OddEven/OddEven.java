package OddEven;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

class Player {
    String name;
    int amount = 100;
    int numTurn;
}

class Opponent {
    String name = "opponent";
    int amount = 120;
}

class UserData {
    static String name;
    static int numTurn;
    static int amount;

    public UserData(String name, int numTurn, int amount) {
        UserData.name = name;
        UserData.numTurn = numTurn;
        UserData.amount = amount;
    }

}

class Rank {
    File file = new File("/Users/kimnayeon/codesquad-cocoa/mission3/OddEven/player.txt");
    FileWriter fw = null; // 왜 fw 정의하고, 생성하는걸 따로하는지..?

    public void saveData(String name, int numTurn, int amount) throws IOException {
        fw = new FileWriter(file, true);   // true를 옆에 같이 써주면 이어쓰기!
        fw.write(toString(new UserData(name, numTurn, amount)));
        fw.close();  // 이거 안써주면 fw 실행 안됨
    }

    private String toString(UserData userData) {
        return "\n이름: " + UserData.name + "\t턴 횟수: " + UserData.numTurn + "\t금액: " + UserData.amount;
    }

}

class PlayGame {
    String result;
    String choice;
    int numStage = 1;
    int maxStage = 8;
    Player p1 = new Player();
    Opponent o1 = new Opponent();
    Scanner s = new Scanner(System.in);
    Rank rank = new Rank();

    public String createPlayer(String name) {
        p1.name = name;
        System.out.println("보유 금액: " + p1.amount + "이 생성되었습니다.\n게임을 시작합니다.");
        System.out.println("------------------");

        return name;
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

    public void play() throws IOException {
        p1.numTurn = 0;

        while (p1.amount > 0 && o1.amount > 0 && numStage < maxStage) {
            System.out.println("베팅금액을 입력하시오.");
            int bettingAmount = s.nextInt();
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
        if (numStage == maxStage) {
            System.out.println("게임이 종료되었습니다.");
            System.out.println("stage: " + (numStage - 1) + " 시도 횟수: " + p1.numTurn + " 보유 금액: " + p1.amount);

        }
        rank.saveData(p1.name, p1.numTurn, p1.amount);
        s.close();
    }


}

class Main {
    public static void main(String[] args) throws IOException {
        PlayGame pg = new PlayGame();
        Player p = new Player();
//        UserData ud = new UserData(p.name, p.numTurn, p.amount);
        Scanner s = new Scanner(System.in);

        System.out.println("홀짝 게임에 오신것을 환영합니다. \n이름을 입력하시오");
        System.out.println("-----------------");

        String inputName = s.next();
        pg.createPlayer(inputName);
        pg.play();

        s.close();

    }
}

