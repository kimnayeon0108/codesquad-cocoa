package ledger;

import java.util.Scanner;

class Data {
    String date;
    String note;
    int amount;
    int num = 1;

    public void inputData() {
        System.out.println("---- " + num + " 번----");
        System.out.println("날짜, 적요, 금액을 입력하시오. ");
        Scanner s = new Scanner(System.in);
        date = s.next();
        note = s.next();
        amount = s.nextInt();
    }
}

class User {
    static String name;
    static int password;
}

public class FinancialLedger {

    public static void main(String[] args) {
        User user = new User();
        Data data = new Data();

        System.out.println("이름과 비밀번호를 입력하세요.");
        Scanner s = new Scanner(System.in);
        user.name = s.next();
        user.password = s.nextInt();

        data.inputData();



    }
}
