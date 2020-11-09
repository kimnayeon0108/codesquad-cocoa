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
    public static String result; //여기에 왜 static을 붙이면 되고 안붙이면 안되는

    public static String whatIsResult() {
        Random rand = new Random();
        int num = rand.nextInt(20);
        if (num % 2 == 0) {
            result = "짝";
        }
        if (num % 2 == 1) {
            result = "홀";
        }
        return result;
    }


    public static void main(String[] args) {

        Player1 p1 = new Player1();
        Player2 p2 = new Player2();

        System.out.println("이름을 입력하시오.");
        Scanner s = new Scanner(System.in);
        p1.name = s.next();

        while(p1.amount > 0) {
            System.out.println("베팅금액을 입력하시오.");
            int bettingAmount = s.nextInt();

            if (bettingAmount > p1.amount) {
                System.out.println("베팅금액을 다시 입력하시오. 가능 금액은: " + p1.amount);
                bettingAmount = s.nextInt();
            }

            System.out.println("홀 또는 짝을 입력하시오.");
            String choice = s.next();

            whatIsResult();
            if (choice == result) {
                System.out.println("맞았습니다!");
                p1.amount += bettingAmount;
                p2.amount -= bettingAmount;
                System.out.println("보유 금액: " + p1.amount);
            }
            if (choice != result) {
                System.out.println("틀렸습니다!");
                p1.amount -= bettingAmount;
                p2.amount += bettingAmount;
                System.out.println("보유 금액: " + p1.amount);
            }

        }
        System.out.println("게임이 종료되었습니다.");

    }
}

