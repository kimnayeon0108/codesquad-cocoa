package OddEven;

public class Player {
    public String name;
    public int amount;
    public int numTurn;

    public Player (String name) {
        this.name = name;
        this.amount = 100;
        this.numTurn = 0;
    }

    public void createPlayer(){
        System.out.println("이름: " + this.name + "보유 금액: " + this.amount + "이 생성되었습니다.\n게임을 시작합니다.");
        System.out.println("------------------");
    }
}
