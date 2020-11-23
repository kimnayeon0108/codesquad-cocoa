package component;

import java.awt.*;

public class TextFieldTest {
    public static void main(String[] args) {
        Frame f = new Frame("Login");
        f.setSize(400, 65);
        f.setLayout(new FlowLayout());

        Label lid = new Label("ID :", Label.RIGHT);     // 정렬을 오른쪽으로
        Label lpwd = new Label("Password :", Label.RIGHT);

        TextField id = new TextField(10);      // 약 10글자 입력가능
        TextField pwd = new TextField(10);
        pwd.setEchoChar('*');       // 입력한 값 대신 *이 보이게 하는 메소드

        f.add(lid);
        f.add(id);
        f.add(lpwd);
        f.add(pwd);
        f.setVisible(true);
    }
}
