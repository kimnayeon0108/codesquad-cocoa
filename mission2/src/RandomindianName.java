import sun.rmi.server.InactiveGroupException;

import  java.util.Scanner;

public class RandomindianName {

    public int year;
    public int month;
    public int day;
    public String how;
    public String be;
    public String doWhat;

    public void enter() {
        Scanner s = new Scanner(System.in);
        System.out.println("생년월일을 입력해 주세요>");
        year = s.nextInt();
        month = s.nextInt();
        day = s.nextInt();

        s.close();
    }

    public void name() {
        String string_year = Integer.toString(year);

        switch(string_year.charAt(3)) {
            case '0':
                how = "시끄러운";
                break;
            case '1':
                how = "푸른";
                break;
            case '2':
                how = "적색";
                break;
            case '3':
                how = "조용한";
                break;
            case '4':
                how = "웅크린";
                break;
            case '5':
                how = "백색";
                break;
            case '6':
                how = "지혜로운";
                break;
            case '7':
                how = "용감한";
                break;
            case '8':
                how = "날카로운";
                break;
            case '9':
                how = "욕심많";
                break;
        }

        switch (month) {
            case 1:
                be = "늑대";
                break;
            case 2:
                be = "태양";
                break;
            case 3:
                be = "양";
                break;
            case 4:
                be = "매";
                break;
            case 5:
                be = "황소";
                break;
            case 6:
                be = "불꽃";
                break;
            case 7:
                be = "나무";
                break;
            case 8:
                be = "달빛";
                break;
            case 9:
                be = "말";
                break;
            case 10:
                be = "돼지";
                break;
            case 11:
                be = "하늘";
                break;
            case 12:
                be = "바";
                break;
        }

        switch(day) {
            case 1:
                doWhat = "와(과) 함께 춤을";
                break;
            case 2:
                doWhat = "의 기상";
                break;
            case 3:
                doWhat = "은(는) 그림자속에";
                break;
            case 4:
            case 5:
            case 6:
                doWhat = "";
                break;
            case 7:
                doWhat = "의 환생";
                break;
            case 8:
                doWhat = "의 죽음";
                break;
            case 9:
                doWhat = "아래에서";
                break;
            case 10:
                doWhat = "(를)을 보라";
                break;
            case 11:
                doWhat = "이(가) 노래하다";
                break;
            case 12:
                doWhat = "의 그림자";
                break;
            case 13:
                doWhat = "의 일격";
                break;
            case 14:
                doWhat = "에게 쫓기는 남자";
                break;
            case 15:
                doWhat = "의 행진";
                break;
            case 16:
                doWhat = "의 왕";
                break;
            case 17:
                doWhat = "의 유령";
                break;
            case 18:
                doWhat = "을 죽인자";
                break;
            case 19:
                doWhat = "는(은) 맨날 잠잔다";
                break;
            case 20:
                doWhat = "처럼";
                break;
            case 21:
                doWhat = "의 고향";
                break;
            case 22:
                doWhat = "의 전사";
                break;
            case 23:
                doWhat = "은(는) 나의 친구";
                break;
            case 24:
                doWhat = "의 노래";
                break;
            case 25:
                doWhat = "의 정령";
                break;
            case 26:
                doWhat = "의 파수꾼";
                break;
            case 27:
                doWhat = "의 악마";
                break;
            case 28:
                doWhat = "와(과) 같은 사나이";
                break;
            case 29:
                doWhat = "를(을) 쓰러트린자";
                break;
            case 30:
                doWhat = "의 혼";
                break;
            case 31:
                doWhat = "은(는) 말이없다";
                break;
        }
    }

    public void print(){
        System.out.println("당신의 이름은 " + how + be + doWhat + "입니다.");
    }

    public static void main(String[] args) {
        RandomindianName indianName = new RandomindianName();
        indianName.enter();
        indianName.name();
        indianName.print();
    }

}
