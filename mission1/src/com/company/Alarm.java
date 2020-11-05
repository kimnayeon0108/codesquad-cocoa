package com.company;

import java.util.Scanner;

public class Alarm {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in); // h시 m분 콘솔입력 받기
        int h = s.nextInt();
        int m = s.nextInt();
        s.close();

        if(m < 45){
            h--;
            m = 60 - (45 - m);
            if(h < 0){
                h = 23;
            }
            System.out.println(h + " " + m);
        } else {
            System.out.println(h + " " + (m - 45));

        }

    }


}
