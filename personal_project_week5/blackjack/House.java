package blackjack;

public class House {
    public long balance;
    public Dealer dealer;

    public House(){
        this.balance = 100_000_000_000L;    // 하우스 보유 금액 1억
    }
}
