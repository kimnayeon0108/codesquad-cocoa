package blackjack;

public class Dealer {
    // 맨 처음에 딜러 카드 한장 받음
    public String firstCard;
    public int firstValue;
    public int value;
    public long dChips;

    public boolean dAce;        // 딜러카드중 ace 가 있는지 없는지
    public boolean dBlackjack;
    public boolean dBust;

    public Dealer(){
        this.dChips = 100_000_000L;     // 딜러 보유 칩스 금액 일억원
        this.value = this.firstValue;
    }
}
