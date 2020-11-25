package exam;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicsEx4 extends Frame implements MouseMotionListener {
    int x = 0;
    int y = 0;

    Image img = null;
    Graphics gImg = null;

    public static void main(String[] args) {
        new GraphicsEx4("GraphicsEx4");
    }

    public GraphicsEx4(String title) {
        super(title);
        addMouseMotionListener(this);       // 마우스 리스너 등록
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setBounds(100, 100, 500, 500);
        setVisible(true);

        img = createImage(500, 500);
        gImg = img.getGraphics();
        gImg.drawString("왼쪽버튼을 누른 채로 마우스를 움직여보세요.", 10, 50);
        repaint();
    }

    public void paint(Graphics g) {
        // 가상화면에 그려진 그림을 Frame 에 복사
        if (img != null) g.drawImage(img, 0, 0, this);
    }

    public void mouseMoved(MouseEvent me) {}

    public void mouseDragged(MouseEvent me) {
        // 마우스를 누른 상태로 포인터를 이동했을 때만 그림이 그려지도록 함
        if (me.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
            x = me.getX();
            y = me.getY();
            gImg.drawString("*", x, y);
            repaint();
        }
    }
}
