import java.util.Calendar;

public class Current {
    int hour;
    int minute;
    String ANSI_RESET = "\u001B[0m";
    String ANSI_BLUE = "\u001B[34m";
    Display display = new Display();


    public synchronized void getTime() {
        Calendar cal = Calendar.getInstance();

        this.hour = cal.get(Calendar.HOUR);
        this.minute = cal.get(Calendar.MINUTE);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void marking() {
        // 자정 정오
        if ((hour == 12 || hour == 00) && minute == 00) {
            display.clock[24] = ANSI_BLUE + display.clock[24] + ANSI_RESET;     // 정
            if (hour == 24) {
                display.clock[18] = ANSI_BLUE + display.clock[18] + ANSI_RESET;     // 자
            }
            if (hour == 12) {
                display.clock[30] = ANSI_BLUE + display.clock[30] + ANSI_RESET;   // 오
            }
        } else {
            // 시
            display.clock[17] = ANSI_BLUE + display.clock[17] + ANSI_RESET;
        }
        // 분
        if (minute > 0) {
            display.clock[35] = ANSI_BLUE + display.clock[35] + ANSI_RESET;
        }
        if (minute / 10 > 0) {
            display.clock[23] = ANSI_BLUE + display.clock[23] + ANSI_RESET;
        }

        switch (hour) {
            case 1:
                display.clock[0] = ANSI_BLUE + display.clock[0] + ANSI_RESET;
                break;
            case 2:
                display.clock[1] = ANSI_BLUE + display.clock[1] + ANSI_RESET;
                break;
            case 3:
                display.clock[2] = ANSI_BLUE + display.clock[3] + ANSI_RESET;
                break;
            case 4:
                display.clock[3] = ANSI_BLUE + display.clock[3] + ANSI_RESET;
                break;
            case 5:
                display.clock[4] = ANSI_BLUE + display.clock[4] + ANSI_RESET;
                display.clock[5] = ANSI_BLUE + display.clock[5] + ANSI_RESET;
                break;
            case 6:
                display.clock[6] = ANSI_BLUE + display.clock[6] + ANSI_RESET;
                display.clock[7] = ANSI_BLUE + display.clock[7] + ANSI_RESET;
                break;
            case 7:
                display.clock[8] = ANSI_BLUE + display.clock[8] + ANSI_RESET;
                display.clock[9] = ANSI_BLUE + display.clock[9] + ANSI_RESET;
                break;
            case 8:
                display.clock[10] = ANSI_BLUE + display.clock[10] + ANSI_RESET;
                display.clock[11] = ANSI_BLUE + display.clock[11] + ANSI_RESET;
                break;
            case 9:
                display.clock[12] = ANSI_BLUE + display.clock[12] + ANSI_RESET;
                display.clock[13] = ANSI_BLUE + display.clock[13] + ANSI_RESET;
                break;
            case 10:
                display.clock[14] = ANSI_BLUE + display.clock[14] + ANSI_RESET;
                break;
            case 11:
                display.clock[14] = ANSI_BLUE + display.clock[14] + ANSI_RESET;
                display.clock[15] = ANSI_BLUE + display.clock[15] + ANSI_RESET;
                break;
            case 12:
            case 00:
                if (minute == 00) {    // 자정, 정오 표시때는 '몇' 시인지 표시하지 않음
                    break;
                }
                display.clock[14] = ANSI_BLUE + display.clock[14] + ANSI_RESET;
                display.clock[16] = ANSI_BLUE + display.clock[16] + ANSI_RESET;
                break;
        }

        switch (minute / 10) {
            case 2:
                display.clock[19] = ANSI_BLUE + display.clock[19] + ANSI_RESET;
                break;
            case 3:
                display.clock[20] = ANSI_BLUE + display.clock[20] + ANSI_RESET;
                break;
            case 4:
                display.clock[21] = ANSI_BLUE + display.clock[21] + ANSI_RESET;
                break;
            case 5:
                display.clock[22] = ANSI_BLUE + display.clock[22] + ANSI_RESET;
                break;
        }

        switch (minute % 10) {
            case 1:
                display.clock[25] = ANSI_BLUE + display.clock[25] + ANSI_RESET;
                break;
            case 2:
                display.clock[26] = ANSI_BLUE + display.clock[26] + ANSI_RESET;
                break;
            case 3:
                display.clock[27] = ANSI_BLUE + display.clock[27] + ANSI_RESET;
                break;
            case 4:
                display.clock[28] = ANSI_BLUE + display.clock[28] + ANSI_RESET;
                break;
            case 5:
                display.clock[31] = ANSI_BLUE + display.clock[31] + ANSI_RESET;
                break;
            case 6:
                display.clock[29] = ANSI_BLUE + display.clock[29] + ANSI_RESET;
                break;
            case 7:
                display.clock[32] = ANSI_BLUE + display.clock[32] + ANSI_RESET;
                break;
            case 8:
                display.clock[33] = ANSI_BLUE + display.clock[33] + ANSI_RESET;
                break;
            case 9:
                display.clock[34] = ANSI_BLUE + display.clock[34] + ANSI_RESET;
                break;
        }

        for (int i = 0; i < display.clock.length; i++) {
            System.out.print(display.clock[i] + " ");
        }

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
