package ledger;

import java.util.ArrayList;
import java.util.Scanner;

class Data {
    static String date;
    static String note;
    static int amount;
    static Scanner s = new Scanner(System.in);
    static ArrayList<Integer> numData = new ArrayList<>();

    public static void inputData() {
        for(int i = 0; i < numData.size(); i++) {
            System.out.println("---- " + (i + 1) + " 번----");
            System.out.print("날짜, 내역, 금액을 입력하시오. \n 날짜: ");
            date = s.next();
            numData(i).add =
            System.out.print("내역: ");
            note = s.next();
            System.out.print("금액: ");
            amount = s.nextInt();
        }
    }
}

class User {
    static String name;
    static String password;
    static String id;
    static String inputPassword;
    Scanner s = new Scanner(System.in);

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

            if(!id.equals(name) || !inputPassword.equals(password)){
                System.out.println("아이디와 비밀번호가 잘못 입력되었습니다. 다시 입력해주세요.");
            }
            if(id.equals(name) &&inputPassword.equals(password)){
                System.out.println("로그인이 완료되었습니다. \n 환영합니다 " + name + "님");
                break;  // while의 조건부분에 다른걸 넣으면 break를 사용하지 않아도 되지 않을까..
            }
        }
        Data data = new Data();   // 새로운 data 객체 생성하는 위치 여기가 적절한가..? 함수 밖에 와야하진 않을까..?
        data.inputData();

    }


}

public class FinancialLedger {

    public static void main(String[] args) {
        User user = new User();
        Data data = new Data();

        user.register();
        user.login();



    }
}
