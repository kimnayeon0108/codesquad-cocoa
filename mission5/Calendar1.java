import java.util.Calendar;

public class Calendar1 {
    int firstDayOfWeek; // 이번달 1일이 무슨요일인지 일요일 1, 토요일 7
    int lastDay;  // 월마다 일 수 가 달라
    int day;
    Calendar cal = Calendar.getInstance();
    String[] weeks = {" 일", " 월", " 화", "수", " 목", " 금", "토"};
    int width = 7;
    int row = 6;
    String[][] template;

    public void print(int year, int month) {
        template = new String[6][7];

        // 이번달 1일이 무슨요일인지 구하기
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, 1);
        firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;  // 일요일은 1, 토요일은 7으로 반환됨, week 배열의 일요일은 인덱스 0번 값이므로 -1
        lastDay = cal.getActualMaximum(Calendar.DATE);
        day = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < width; j++) {
                if (j < firstDayOfWeek && i == 0) {
                    template[i][j] = " ";
                } else if (day <= lastDay) {
                    template[i][j] = String.valueOf(day);
                    day++;
                } else {
                    template[i][j] = " ";
                }
            }
        }
        System.out.println(cal.get(Calendar.YEAR) + "년\t" + (cal.get(Calendar.MONTH) + 1) + "월");
        System.out.println("------------------------------");

        for(String a : weeks){
            System.out.print(a + "  ");
        }
        System.out.println();

        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < template[i].length; j++) {
                if (template[i][j].length() == 1) {
                    System.out.print("  " + template[i][j] + " ");
                } else {
                    System.out.print(" " + template[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

}
