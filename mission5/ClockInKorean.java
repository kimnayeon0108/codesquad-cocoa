public class ClockInKorean {
    public static void main(String[] args) {
        Display display = new Display();
        Current current = new Current();

        display.displayAllTime();
        current.markingHour(display);
        current.markingMinute(display);

        for(int i = 0; i < display.clock.size(); i++){
            System.out.print(display.clock.get(i) + " ");
        }

    }
}




