package component;

import java.awt.*;

public class ButtonTest {
    public static void main(String[] args) {
        Frame f = new Frame("Login");
        f.setSize(300, 200);
        f.setLayout(null);          // Frame은 기본 레이아웃이 설정되어잇어서 null로 설정해제

        Button b = new Button("확 인");
        b.setSize(100, 50);
        b.setLocation(100, 75);     // Frame의 Layout을 null 값을 주지 않는다면
                                          // 자동으로 위치가 설정되어서 이 코드 필요없음

        f.add(b);           // Button을 Frame에 포함시키는 코드
        f.setVisible(true);

    }
}
