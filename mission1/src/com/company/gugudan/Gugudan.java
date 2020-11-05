package gugudan;

import java.util.Scanner;

public class Gugudan {
    public int start;
    public int end;

    public void enter(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter start num.");
        start = s.nextInt();
        System.out.println("enter end num");
        end = s.nextInt();
        s.close();
    }

    public void printGugudan(){
        for(int i = start; i <= end; i++){
            System.out.println(i + "단");
            for(int j = 1; j < 10; j++ ){
                System.out.println(i + " * " + j + " = " + (i * j));
            }
            System.out.println("--------");
        }
    }

    public static void main(String[] args) {
        Gugudan gugu = new Gugudan();

        gugu.enter();
        gugu.printGugudan();
    }

}

