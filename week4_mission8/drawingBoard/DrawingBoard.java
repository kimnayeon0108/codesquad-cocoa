package drawingBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawingBoard extends Frame implements MouseMotionListener {
    int x = 0;
    int y = 0;

    Image img = null;
    Graphics gImg = null;

    public static void main(String[] args) {
        new DrawingBoard("Drawing board");
    }

    public DrawingBoard(String title){
        super(title);
        addMouseMotionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setBounds(screenSize.width/2 -250, screenSize.height/2 -250, 500, 500);
        setVisible(true);

        img = createImage(500, 500);
        gImg = img.getGraphics();
        repaint();
    }

    public void paint (Graphics g){
        // 가상 화면에 그려민 그림을 Frame에 복사
        if(img != null) g.drawImage(img, 0, 0, this);
    }

    public void mouseMoved(MouseEvent me){
        x = me.getX();
        y = me.getY();
    }

    public void mouseDragged(MouseEvent me){
        if (me.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {

            gImg.drawLine( x, y, me.getX(), me.getY());
            x = me.getX();
            y = me.getY();

            repaint();
        }
    }
}
