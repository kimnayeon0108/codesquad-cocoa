import java.util.Calendar;

public class Time extends Thread {
    Current current;
    Calendar c = Calendar.getInstance();

    public Time(Current current) {
        this.current = current;
    }

    @Override
    public void run() {
        while (true) {
            current.getTime();

            try {
                Thread.sleep(60000 - c.get(Calendar.SECOND));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
