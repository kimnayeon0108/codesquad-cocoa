package exam;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicsEx2 extends Frame implements MouseMotionListener {
    int x = 0;
    int y = 0;

    public static void main(String[] args) {
        new GraphicsEx2("GraphicsEx2");
    }

    public GraphicsEx2(String title){
        super(title);
        addMouseMotionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });

        setBounds(100,100, 500,500);
        setVisible(true);
    }

    public void paint(Graphics g){
        g.drawString("마우스를 움직여보세요.",10,50);
        g.drawString("*", x, y);
    }

    public void mouseMoved(MouseEvent me){
        // 마우스가 움직이면 MouseEvent 발생
        x = me.getX();      // 포인터의 위치를 변수 x, y에 저장
        y = me.getY();
        repaint();      // repaint()를 꼭 해주어야 한다.
    }

    public void mouseDragged(MouseEvent me){}
}
