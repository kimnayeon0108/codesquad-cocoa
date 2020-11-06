package Baekjoon;

import java.util.Scanner;

public class Backjoon2920 {
      public static void main(String[] args) {

          int[] numbers  = {1, 2, 3, 4, 5, 6, 7, 8};
          int[] reverse = {8, 7, 6, 5, 4, 3, 2, 1};

          System.out.println("enter numbers");
          Scanner s = new Scanner(System.in);

          int [] input = new int [8];
          for(int i = 0; i < numbers.length; i++ ){
              input[i] = s.nextInt();
          }
          s.close();

          String result = "";
          for(int i = 0; i < numbers.length; i++){
              if(numbers[i] == input[i]){
                  result = "ascending";
              } else if(reverse[i] == input[i]){
                  result = "descending";
              } else {
                  result = "mixed";
              }
          }
          //인덱스값을 돌아가면서 계속 비교해줘야 될때 return 이나 print를 하면 for 문 안에서 계속
          //돌기때문에 문제가 생기므로 변수를 선언해서 그 안에 값을 넣으면 된다!
          System.out.println(result);

      }
}
