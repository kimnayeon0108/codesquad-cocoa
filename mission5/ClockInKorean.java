import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ClockInKorean {
    Time time = new Time();
    Calendar1 calendar1 = new Calendar1();
    Scanner s = new Scanner(System.in);
    Calendar c = Calendar.getInstance();
    int year;
    int month;


    public void input(){
        System.out.println("시계를 보려면 \"clock\" 달력을 보려면 \"cal\"을 입력해주세요.");
        String input = s.nextLine();
        if(input.equals("clock")) {
                time.start();

        }
        if(input.equals("cal")) {
            calendar1.print(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
            System.out.println("원하는 년도와 월을 입력해주세요.");
            year = s.nextInt();
            month = s.nextInt();

            calendar1.print(year, month - 1);
        }

    }

    public static void main(String[] args) {
        ClockInKorean c1 = new ClockInKorean();

        c1.input();

    }

}




