package blackjack;

import java.util.Arrays;
import java.util.Collections;

public class Card {
    private String[] cardNum = {"a", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "J", "Q", "K"};
    private String[] shape = {"♥","♠","♦","♣"};
    private final int oneDeckNum = cardNum.length * shape.length;
    private String[] card = new String[oneDeckNum];
    public String[] sixDeckCard;
    public int[] value;
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";

    public Card(){
        getOneDeckCard();
        shuffleCard();
//        getCardValue();
    }

//    Todo: 카드 마다 value 값 주기 어떻게 하지..
//    private void getCardValue() {
//        value = new int[10];
//        for(int i = 0; i < 10; i++){
//            value[i] = i + 1;
//        }
//
//        for(int j = 0; j < sixDeckCard.length; j++) {
//            if(sixDeckCard[j].charAt(0) == 'a'){
//
//            }
//        }
//    }

    // 한덱의 카드 생성
    private String[] getOneDeckCard() {
        int idx = 0;
        for(int i = 0; i < shape.length; i++){
            for(int j = 0; j < cardNum.length; j++){
                card[idx] = cardNum[j] + shape[i];
                if(shape[i].equals("♥") || shape[i].equals("♦")){
                    card[idx] = ANSI_RED + card[idx] + ANSI_RESET;
                }
                idx++;
            }
        }
        return card;
    }

    private String[] shuffleCard() {
        sixDeckCard = new String[oneDeckNum * 6];
        for(int i = 0; i < sixDeckCard.length; i++){
            sixDeckCard[i] = card[i % oneDeckNum];
        }
        Collections.shuffle(Arrays.asList(sixDeckCard));

        return  sixDeckCard;
    }

//    public static void main(String[] args) {
//        Card c = new Card();
//        for(int i = 0; i < sixDeckCard.length; i++){
//            System.out.println(sixDeckCard[i]);
//        }
//    }
}
