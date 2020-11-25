package OddEven;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Rank {
    File file = new File("/Users/kimnayeon/codesquad-cocoa/mission3/OddEven/player.txt");
    FileWriter fw = null;
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    public void saveData(String name, int numTurn, int amount) throws IOException {
        fw = new FileWriter(file, true);   // true를 옆에 같이 써주면 이어쓰기!
        fw.write(toString(new UserData(name, numTurn, amount)));
        fw.close();  // 이거 안써주면 fw 실행 안됨
    }

    private String toString(UserData userData) {
        return "\n이름: " + UserData.name + "\t턴 횟수: " + UserData.numTurn + "\t금액: " + UserData.amount;
    }

    public void ranking() throws IOException {
        ArrayList<String> arr = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null){
            arr.add(line);
        }
        for(int i = 0; i < arr.size(); i++){

        }
    }

}
