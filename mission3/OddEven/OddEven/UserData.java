package OddEven;

public class UserData {
    String name;
    static int numTurn;
    static int amount;

    public UserData(String name, int numTurn, int amount) {
        this.name = name;
        UserData.numTurn = numTurn;
        UserData.amount = amount;
    }

}

