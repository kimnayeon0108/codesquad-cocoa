import java.util.Calendar;

public class Time extends Thread {
    int hour;
    int minute;
    String ANSI_RESET = "\u001B[0m";
    String ANSI_BLUE = "\u001B[34m";

    public void getTime() {
        Calendar cal = Calendar.getInstance();

        this.hour = cal.get(Calendar.HOUR);
        this.minute = cal.get(Calendar.MINUTE);

    }

    public void marking() {
        String[] clock = new String[]{"한", "두", "세", "네", "다", "섯",
                "여", "섯", "일", "곱", "여", "덟",
                "아", "홉", "열", "한", "두", "시",
                "자", "이", "삼", "사", "오", "십",
                "정", "일", "이", "삼", "사", "육",
                "오", "오", "칠", "팔", "구", "분"};

        // 자정 정오
        if ((hour == 12 || hour == 00) && minute == 00) {       // 정
            clock[24] = ANSI_BLUE + clock[24] + ANSI_RESET;
            if (hour == 00) {
                clock[18] = ANSI_BLUE + clock[18] + ANSI_RESET;    // 자
            }
            if (hour == 12) {
                clock[30] = ANSI_BLUE + clock[30] + ANSI_RESET;   // 오
            }
        } else {
            // 시
            clock[17] = ANSI_BLUE + clock[17] + ANSI_RESET;
        }
        // 분
        if (minute > 0) {
            clock[35] = ANSI_BLUE + clock[35] + ANSI_RESET;
        }
        if (minute / 10 > 0) {
            clock[23] = ANSI_BLUE + clock[23] + ANSI_RESET;
        }

        switch (hour) {
            case 1:
                clock[0] = ANSI_BLUE + clock[0] + ANSI_RESET;
                break;
            case 2:
                clock[1] = ANSI_BLUE + clock[1] + ANSI_RESET;
                break;
            case 3:
                clock[2] = ANSI_BLUE + clock[3] + ANSI_RESET;
                break;
            case 4:
                clock[3] = ANSI_BLUE + clock[3] + ANSI_RESET;
                break;
            case 5:
                clock[4] = ANSI_BLUE + clock[4] + ANSI_RESET;
                clock[5] = ANSI_BLUE + clock[5] + ANSI_RESET;
                break;
            case 6:
                clock[6] = ANSI_BLUE + clock[6] + ANSI_RESET;
                clock[7] = ANSI_BLUE + clock[7] + ANSI_RESET;
                break;
            case 7:
                clock[8] = ANSI_BLUE + clock[8] + ANSI_RESET;
                clock[9] = ANSI_BLUE + clock[9] + ANSI_RESET;
                break;
            case 8:
                clock[10] = ANSI_BLUE + clock[10] + ANSI_RESET;
                clock[11] = ANSI_BLUE + clock[11] + ANSI_RESET;
                break;
            case 9:
                clock[12] = ANSI_BLUE + clock[12] + ANSI_RESET;
                clock[13] = ANSI_BLUE + clock[13] + ANSI_RESET;
                break;
            case 10:
                clock[14] = ANSI_BLUE + clock[14] + ANSI_RESET;
                break;
            case 11:
                clock[14] = ANSI_BLUE + clock[14] + ANSI_RESET;
                clock[15] = ANSI_BLUE + clock[15] + ANSI_RESET;
                break;
            case 12:
            case 00:
                if (minute == 00) {    // 자정, 정오 표시때는 '몇' 시인지 표시하지 않음
                    break;
                }
                clock[14] = ANSI_BLUE + clock[14] + ANSI_RESET;
                clock[16] = ANSI_BLUE + clock[16] + ANSI_RESET;
                break;
        }

        switch (minute / 10) {
            case 2:
                clock[19] = ANSI_BLUE + clock[19] + ANSI_RESET;
                break;
            case 3:
                clock[20] = ANSI_BLUE + clock[20] + ANSI_RESET;
                break;
            case 4:
                clock[21] = ANSI_BLUE + clock[21] + ANSI_RESET;
                break;
            case 5:
                clock[22] = ANSI_BLUE + clock[22] + ANSI_RESET;
                break;
        }

        switch (minute % 10) {
            case 1:
                clock[25] = ANSI_BLUE + clock[25] + ANSI_RESET;
                break;
            case 2:
                clock[26] = ANSI_BLUE + clock[26] + ANSI_RESET;
                break;
            case 3:
                clock[27] = ANSI_BLUE + clock[27] + ANSI_RESET;
                break;
            case 4:
                clock[28] = ANSI_BLUE + clock[28] + ANSI_RESET;
                break;
            case 5:
                clock[31] = ANSI_BLUE + clock[31] + ANSI_RESET;
                break;
            case 6:
                clock[29] = ANSI_BLUE + clock[29] + ANSI_RESET;
                break;
            case 7:
                clock[32] = ANSI_BLUE + clock[32] + ANSI_RESET;
                break;
            case 8:
                clock[33] = ANSI_BLUE + clock[33] + ANSI_RESET;
                break;
            case 9:
                clock[34] = ANSI_BLUE + clock[34] + ANSI_RESET;
                break;
        }

        for (int i = 0; i < clock.length; i++) {
            System.out.print(clock[i] + " ");
            if ((i + 1) % 6 == 0) {
                System.out.println();
            }
        }
        System.out.println();

    }

    @Override
    public void run() {
        while (true) {
            try {
                getTime();
                marking();
                join(60000 - Calendar.MILLISECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}