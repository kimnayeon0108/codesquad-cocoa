package component;

import java.awt.*;

public class DialogTest {
    public static void main(String[] args) {
        Frame f = new Frame("Parent");
        f.setSize(300, 200);

        // f 를 부모로 지정, modal이 true면 Dialog 창이 사라지기 전까지 부모로 지정된
        // 프레임을 동작할 수 없다.
        Dialog info = new Dialog(f, "Information", true);
        info.setSize(140, 90);
        info.setLocation(50, 50);
        info.setLayout(new FlowLayout());

        Label msg = new Label("This is modal Dialog", Label.CENTER);
        Button ok = new Button("OK");
        info.add(msg);
        info.add(ok);

        f.setVisible(true);
        info.setVisible(true);
    }
}
