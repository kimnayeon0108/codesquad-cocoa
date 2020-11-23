package component;

import java.awt.*;

public class PanelTest {
    public static void main(String[] args) {
        Frame f = new Frame("Panel");
        f.setSize(300, 200);
        f.setLayout(null);

        Panel p = new Panel();
        p.setBackground(Color.green);
        p.setSize(100, 100);
        p.setLocation(50, 50);

        // Panel은 기본적으로 FlowLayout을 레이아웃 매니저로 사용하므로 Button의
        // 크기와 위치는 지정하지 않아도 됨
        Button ok = new Button("OK");

        p.add(ok);
        f.add(p);
        f.setVisible(true);
    }
}
