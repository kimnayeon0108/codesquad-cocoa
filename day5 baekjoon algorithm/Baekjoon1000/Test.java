package Baekjoon1000;

import java.util.Scanner;

public class Test {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();

        public int sum() {
            return a + b;
        }

        public static void main(String[] args) {
            Test test = new Test();

            int c = test.sum();
            System.out.println(c);

        }
}
