public class Time extends Thread {
    Current current;

    public Time(Current current) {
        this.current = current;
    }

    @Override
    public void run() {
        while (true) {
            current.getTime();
            current.marking();
        }

    }


}
