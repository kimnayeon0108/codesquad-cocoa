import java.text.SimpleDateFormat;
import java.util.Date;

public class Current{
    Date date = new Date();
    SimpleDateFormat time = new SimpleDateFormat("aa hh시 mm분");
    String s = time.format(date);
    int hour = Integer.valueOf(s.substring(3, 5));
    int minute = Integer.valueOf(s.substring(7, 9));
    String ANSI_RESET = "\u001B[0m";
    String ANSI_BLUE = "\u001B[34m";


    public void markingHour(Display display){
        display.clock.set(17, ANSI_BLUE + display.clock.get(17) + ANSI_RESET);

        switch (hour) {
            case 1:
                display.clock.set(0, ANSI_BLUE + display.clock.get(0) + ANSI_RESET);
                break;
            case 2:
                display.clock.set(1, ANSI_BLUE + display.clock.get(1) + ANSI_RESET);
                break;
            case 3:
                display.clock.set(2, ANSI_BLUE + display.clock.get(3) + ANSI_RESET);
                break;
            case 4:
                display.clock.set(3, ANSI_BLUE + display.clock.get(3) + ANSI_RESET);
                break;
            case 5:
                display.clock.set(4, ANSI_BLUE + display.clock.get(4) + ANSI_RESET);
                display.clock.set(5, ANSI_BLUE + display.clock.get(5) + ANSI_RESET);
                break;
            case 6:
                display.clock.set(6, ANSI_BLUE + display.clock.get(6) + ANSI_RESET);
                display.clock.set(7, ANSI_BLUE + display.clock.get(7) + ANSI_RESET);
                break;
            case 7:
                display.clock.set(8, ANSI_BLUE + display.clock.get(8) + ANSI_RESET);
                display.clock.set(9, ANSI_BLUE + display.clock.get(9) + ANSI_RESET);
                break;
            case 8:
                display.clock.set(10, ANSI_BLUE + display.clock.get(10) + ANSI_RESET);
                display.clock.set(11, ANSI_BLUE + display.clock.get(11) + ANSI_RESET);
                break;
            case 9:
                display.clock.set(12, ANSI_BLUE + display.clock.get(12) + ANSI_RESET);
                display.clock.set(13, ANSI_BLUE + display.clock.get(13) + ANSI_RESET);
                break;
            case 10:
                display.clock.set(14, ANSI_BLUE + display.clock.get(14) + ANSI_RESET);
                break;
            case 11:
                display.clock.set(14, ANSI_BLUE + display.clock.get(14) + ANSI_RESET);
                display.clock.set(15, ANSI_BLUE + display.clock.get(15) + ANSI_RESET);
                break;
            case 12:
                display.clock.set(14, ANSI_BLUE + display.clock.get(14) + ANSI_RESET);
                display.clock.set(16, ANSI_BLUE + display.clock.get(16) + ANSI_RESET);
                break;
        }
    }

    public void markingMinute(Display display){
        display.clock.set(35, ANSI_BLUE + display.clock.get(35) + ANSI_RESET);
        if (minute / 10 > 0){
            display.clock.set(23, ANSI_BLUE + display.clock.get(23) + ANSI_RESET);
        }

        switch (minute / 10){
            case 2:
                display.clock.set(19, ANSI_BLUE + display.clock.get(19) + ANSI_RESET);
                break;
            case 3:
                display.clock.set(20, ANSI_BLUE + display.clock.get(20) + ANSI_RESET);
                break;
            case 4:
                display.clock.set(21, ANSI_BLUE + display.clock.get(21) + ANSI_RESET);
                break;
            case 5:
                display.clock.set(22, ANSI_BLUE + display.clock.get(22) + ANSI_RESET);
                break;
        }

        switch (minute % 10){
            case 1:
                display.clock.set(25, ANSI_BLUE + display.clock.get(25) + ANSI_RESET);
                break;
            case 2:
                display.clock.set(26, ANSI_BLUE + display.clock.get(26) + ANSI_RESET);
                break;
            case 3:
                display.clock.set(27, ANSI_BLUE + display.clock.get(27) + ANSI_RESET);
                break;
            case 4:
                display.clock.set(28, ANSI_BLUE + display.clock.get(28) + ANSI_RESET);
                break;
            case 5:
                display.clock.set(31, ANSI_BLUE + display.clock.get(31) + ANSI_RESET);
                break;
            case 6:
                display.clock.set(29, ANSI_BLUE + display.clock.get(29) + ANSI_RESET);
                break;
            case 7:
                display.clock.set(32, ANSI_BLUE + display.clock.get(32) + ANSI_RESET);
                break;
            case 8:
                display.clock.set(33, ANSI_BLUE + display.clock.get(33) + ANSI_RESET);
                break;
            case 9:
                display.clock.set(34, ANSI_BLUE + display.clock.get(34) + ANSI_RESET);
                break;
        }


    }



}
