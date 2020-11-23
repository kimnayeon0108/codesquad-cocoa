package component;

import java.awt.*;

public class FrameTest2 {
    public static void main(String[] args) {
        Frame f = new Frame("Login");
        f.setSize(300, 200);

        // 화면의 크기 구하기
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        // 화면크기의 절반값에서 Frame크기의 절반 값을 빼면 화면의 중앙에 위치하게 된다.
        f.setLocation(screenSize.width/2 -150, screenSize.height/2 -100);
        f.setVisible(true);
    }
}
