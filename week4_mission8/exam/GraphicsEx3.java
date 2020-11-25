package exam;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicsEx3 extends Frame implements MouseMotionListener {
    int x = 0;
    int y = 0;

    public static void main(String[] args) {
        new GraphicsEx3("GraphicsEx3");
    }

    public GraphicsEx3(String title) {
        super(title);
        addMouseMotionListener(this);       // 마우스 리스너 등록
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setBounds(100, 100, 500, 500);
        setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawString("마우스를 움직여보세요.", 10, 50);
        g.drawString("*", x, y);
    }

    public void update(Graphics g) {    // update()가 컴포넌트 영역을 지우지 않고,
        paint(g);                       // paint()만 호출하도록 오버라이딩
    }

    public void mouseMoved(MouseEvent me) {
        x = me.getX();
        y = me.getY();
        repaint();
    }

    public void mouseDragged(MouseEvent me) {
    }
}
