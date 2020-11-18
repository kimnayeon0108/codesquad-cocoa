import java.util.Calendar;
import java.util.Scanner;

public class ClockInKorean {

    public static void main(String[] args) {
        Current current = new Current();
        Time time = new Time(current);
        Calendar1 calendar1 = new Calendar1();
        Scanner s = new Scanner(System.in);
        Calendar c = Calendar.getInstance();
        int year;
        int month;

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

}




