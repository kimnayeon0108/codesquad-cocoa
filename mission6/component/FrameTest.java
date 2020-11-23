package component;

import java.awt.*;

public class FrameTest {
    public static void main(String[] args) {
        Frame f = new Frame("Login");  // 객체 생성시 생성자 안 Login은 Frame의
                                            // titlebar 에 나타남
        f.setSize(300, 200);
        f.setVisible(true);   // true 를 해야 화면에 보임
    }
}
