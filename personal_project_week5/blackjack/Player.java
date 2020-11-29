package blackjack;

public class Player {
    public String name;
    public long money;
    public int chips;

    public Player(String name){
        this.name = name;
        this.chips = 0;
        this.money = 3_000_000_000L;    // player 기본적으로 삼백만원 생성
    }
}
