package OddEven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Rank {
    File file = new File("/Users/kimnayeon/codesquad-cocoa/mission3/OddEven/player.txt");
    FileWriter fw = null; // 왜 fw 정의하고, 생성하는걸 따로하는지..?

    public void saveData(String name, int numTurn, int amount) throws IOException {
        fw = new FileWriter(file, true);   // true를 옆에 같이 써주면 이어쓰기!
        fw.write(toString(new UserData(name, numTurn, amount)));
        fw.close();  // 이거 안써주면 fw 실행 안됨
    }

    private String toString(UserData userData) {
        return "\n이름: " + UserData.name + "\t턴 횟수: " + UserData.numTurn + "\t금액: " + UserData.amount;
    }

}
