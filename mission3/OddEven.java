import java.util.Random;
import java.util.Scanner;

class Player1 {
    String name;
    int amount = 100;
}

class Player2 {
    String name = "player2";
    int amount = 120;
}

class OddEven {
    static String result; //여기에 왜 static을 붙이면 되고 안붙이면 안되는
    static String choice;
    static int numStage;
    static int numTurn;

    public static String whatIsResult() {
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

    public static void play(){
        Player1 p1 = new Player1();
        Player2 p2 = new Player2();
        Scanner s = new Scanner(System.in);

        while (p1.amount > 0 && p2.amount > 0) {
            System.out.println("베팅금액을 입력하시오. 보유 금액: " + p1.amount);
            int bettingAmount = s.nextInt();
            numTurn++;

            if (bettingAmount > p1.amount || bettingAmount > p2.amount) {
                System.out.println("베팅금액을 다시 입력하시오. 보유 금액: " + p1.amount
                        + " 상대 보유 금액:" + p2.amount);
                bettingAmount = s.nextInt();
            }

            System.out.println("홀 또는 짝을 입력하시오.");
            choice = s.next();

            if (choice.equals(whatIsResult())) {
                System.out.println("맞았습니다!");
                p1.amount += bettingAmount;
                p2.amount -= bettingAmount;
                System.out.println("보유 금액: " + p1.amount);
            } else {
                System.out.println("틀렸습니다!");
                p1.amount -= bettingAmount;
                p2.amount += bettingAmount;
                System.out.println("보유 금액: " + p1.amount);
            }

            // 새로운 상대 생성
            if (p2.amount == 0) {
                numTurn = 0;
                numStage++;
                System.out.println("-----new game stage: " + numStage + "-----");
                p2.amount = p1.amount * (int)Math.pow(1.2, numStage);
                System.out.println("새로운 상대가 생성되었습니다. 상대의 보유 금액: " + p2.amount);
            }
            // player amount 소진시 게임 종료
            if (p1.amount == 0) {
                System.out.println("보유 금액이 0원입니다. 게임이 종료되었습니다.");
                System.out.println("stage: " + (numStage) + " 시도 횟수: " + numTurn);
            }

            // 스테이지 정복 게임 종료
            if (numStage == 9) {
                System.out.println("게임이 종료되었습니다.");
                System.out.println("stage: " + (numStage - 1) + " 시도 횟수: " + numTurn + " 보유 금액: " + p1.amount);
            }

        }
    }

    public static void main(String[] args) {
        Player1 p1 = new Player1();
        Player2 p2 = new Player2();

        System.out.println("이름을 입력하시오.");
        Scanner s = new Scanner(System.in);
        p1.name = s.next();
        System.out.println("보유 금액: " + p1.amount);
        numStage = 1;

        play();

    }
}

