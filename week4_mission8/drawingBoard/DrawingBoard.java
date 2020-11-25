package drawingBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawingBoard extends Frame implements MouseMotionListener {
    int x = 0;
    int y = 0;
//    ScrollPane sp;
    Panel p;

    Image img = null;
    Graphics gImg = null;

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
        setResizable(true);
        setLayout(new FlowLayout());

        panelInFrame();
        add(p);

        setVisible(true);

        img = createImage(500, 500);
        gImg = img.getGraphics();
        repaint();
    }

    public void panelInFrame(){
//        sp = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);

        // checkbox 생성
        p = new Panel();
        p.setLayout(new BorderLayout(10, 10));

        // 그리기 패널
        Panel p1 = new Panel();
        Label l1 = new Label("그리기");
        CheckboxGroup group1 = new CheckboxGroup();
        Checkbox line = new Checkbox("직선", group1, false);
        Checkbox curve = new Checkbox("곡선", group1, false);
        Checkbox square = new Checkbox("사각형", group1, false);
        Checkbox circle = new Checkbox("원", group1, false);
        p1.add(l1);
        p1.add(line);
        p1.add(curve);
        p1.add(square);
        p1.add(circle);
        p1.setSize(200, 50);

        // 색상 패널
        Panel p2 = new Panel();
        Label l2 = new Label("color");
        CheckboxGroup group2 = new CheckboxGroup();
        Checkbox black = new Checkbox("검정", group2, false);
        Checkbox pink = new Checkbox("분홍", group2, false);
        Checkbox blue = new Checkbox("파랑", group2, false);
        p2.add(l2);
        p2.add(black);
        p2.add(pink);
        p2.add(blue);

        p.add("West", p1);
        p.add("East", p2);

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
