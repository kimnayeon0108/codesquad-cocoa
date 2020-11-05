package QurantN;

import java.util.Scanner;

public class QuadrantN {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num1 = s.nextInt();
        int num2 = s.nextInt();
        if(num1 > 0 && num2 >0){
            System.out.println(1);
        }
        if(num1 < 0 && num2 > 0){
            System.out.println(2);
        }
        if(num1 < 0 && num2 < 0){
            System.out.println(3);
        }
        if (num1 > 0 && num2 < 0){
            System.out.println(4);
        }
    }
}
