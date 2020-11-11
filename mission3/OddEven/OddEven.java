package OddEven;

import java.io.*;
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

class SaveGame extends OddEven{

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
            System.out.println("베팅금액을 입력하시오. 보유 금액: " + p1.amount);
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
                System.out.println("보유 금액: " + p1.amount);
            } else {
                System.out.println("틀렸습니다!");
                p1.amount -= bettingAmount;
                o1.amount += bettingAmount;
                System.out.println("보유 금액: " + p1.amount);
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

    public void createPlayer(){
        System.out.println("이름을 입력하시오.");
        Scanner s = new Scanner(System.in);
        p1.name = s.next();
        System.out.println("보유 금액: " + p1.amount);
        System.out.println("------------------");
    }

    public void saveData(){
        File file = new File("/Users/kimnayeon/codesquad-cocoa/mission3/OddEven/player.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(p1.name + " " + numTurn + " " + p1.amount);
            bw.close();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Main{
    public static void main(String[] args) {
        OddEven oe = new OddEven();

        oe.createPlayer();
        oe.play();
        oe.saveData();
    }
}

