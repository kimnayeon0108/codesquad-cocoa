package ledger;

import java.util.ArrayList;
import java.util.Scanner;

class Data {
    static String date;
    static String note;
    static int amount;

    public Data(String date, String note, int amount) {
        this.date = date;
        this.note = note;
        this.amount = amount;
    }
}

class User {
    String name;
    String password;
    String id;
    String inputPassword;
    Scanner s = new Scanner(System.in);
    Ledger ledger = new Ledger();

    public void register() {
        System.out.print("이름과 비밀번호를 입력하세요.\n이름: ");
        name = s.next();
        System.out.print("비밀번호: ");
        password = s.next();
        System.out.println("사용자 등록이 완료되었습니다.");
    }

    public void login() {
        while (true) {
            System.out.println("아이디를 입력해주세요.");
            id = s.next();
            System.out.println("비밀번호를 입력해주세요");
            inputPassword = s.next();

            if (!id.equals(name) || !inputPassword.equals(password)) {
                System.out.println("아이디와 비밀번호가 잘못 입력되었습니다. 다시 입력해주세요.");
            }
            if (id.equals(name) && inputPassword.equals(password)) {
                System.out.println("로그인이 완료되었습니다. \n 환영합니다 " + name + "님");
                break;  // while의 조건부분에 다른걸 넣으면 break를 사용하지 않아도 되지 않을까..
            }
        }

        ledger.createData(Ledger.num);



    }


}

class Ledger {
    static Scanner s = new Scanner(System.in);
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> note = new ArrayList<String>();
    ArrayList<Integer> amount = new ArrayList<Integer>();
    static int num = 0;

    public void addDate(String date) {
        this.date.add(date);
    }

    public void addNote(String note) {
        this.note.add(note);
    }

    public void addAmount(Integer amount) {
        this.amount.add(amount);
    }

    public String printDate(int num) {
        return this.date.get(num);
    }

    public String printNote(int num) {
        return this.note.get(num);
    }

    public Integer printAmount(int num) {
        return this.amount.get(num);

    }

    public void createData(int num) {
        System.out.println("------------------------------------");
        System.out.println("날짜, 내역, 금액을 입력하시오.");
        System.out.print("날짜: ");
        addDate(s.next());

        System.out.print("내역: ");
        addNote(s.next());

        System.out.print("금액: ");
        addAmount(s.nextInt());

        System.out.println("------------------------------------");
        System.out.println("\t번호\t날짜\t||\t내역\t||\t금액\t||");
        System.out.println("\t" + num + "\t" + printDate(num) + "\t" + printNote(num) + "\t" + printAmount(num));

        num++;
    }
}

class Main {
    public static void main(String[] args) {
        User user = new User();

        user.register();
        user.login();
    }
}
