package drawingBoard;

import java.awt.*;
import java.awt.event.*;

public class DrawingBoard extends Frame implements MouseListener, MouseMotionListener {
    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;
    private int x = 0;
    private int y = 0;

    private Panel p;
    private Checkbox[] boxShape = new Checkbox[4];
    private Checkbox[] boxColor = new Checkbox[3];
    private String shapeState;

    private Image img = null;
    private Graphics gImg = null;

    public DrawingBoard(String title) {
        super(title);
        addMouseMotionListener(this);
        addMouseListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        setBounds(screenSize.width / 2 - 350, screenSize.height / 2 - 250, 700, 500);
        setResizable(true);
        setLayout(new FlowLayout());

        panelInFrame();
        add(p);

        setVisible(true);

        img = createImage(700, 500);
        gImg = img.getGraphics();
        repaint();
    }

    public void panelInFrame() {
        // checkbox 생성
        p = new Panel();
        p.setLayout(new BorderLayout(10, 10));

        chooseShape();
        chooseColor();
    }

    private void chooseShape() {
        // 그리기 패널
        Panel p1 = new Panel();
        Label l1 = new Label("그리기");
        CheckboxGroup group1 = new CheckboxGroup();

        p1.add(l1);

        boxShape[0] = new Checkbox("직선", group1, false);
        boxShape[1] = new Checkbox("곡선", group1, false);
        boxShape[2] = new Checkbox("사각형", group1, false);
        boxShape[3] = new Checkbox("원", group1, false);

        for (int i = 0; i < boxShape.length; i++) {
            boxShape[i].addItemListener(new EventHandler());
            p1.add(boxShape[i]);
        }

        p1.setSize(200, 50);
        p1.setBackground(Color.lightGray);

        p.add("West", p1);
    }

    private void chooseColor() {
        // 색상 패널
        Panel p2 = new Panel();
        Label l2 = new Label("color");
        CheckboxGroup group2 = new CheckboxGroup();

        p2.add(l2);

        boxColor[0] = new Checkbox("검정", group2, false);
        boxColor[1] = new Checkbox("분홍", group2, false);
        boxColor[2] = new Checkbox("파랑", group2, false);

        for (int i = 0; i < boxColor.length; i++) {
            boxColor[i].addItemListener(new EventHandler());
            p2.add(boxColor[i]);
        }

        p2.setSize(200, 50);
        p2.setBackground(Color.lightGray);

        p.add("East", p2);
    }


    public void paint(Graphics g) {
        // 가상 화면에 그려민 그림을 Frame에 복사
        if (img != null) g.drawImage(img, 0, 0, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
            if (shapeState.equals("straight")) {
                gImg.drawLine(x, y, endX, endY);
                x = e.getX();
                y = e.getY();
                // Todo: 이부분 연구해서 수정

                repaint();
            } else if (shapeState.equals("curve")) {
                gImg.drawOval(startX, startY, endX, endY);
                repaint();
            } else if (shapeState.equals("rectangle")) {
                gImg.drawRect(startX, startY, endX - startX, endY - startY);
                repaint();
            } else if (shapeState.equals("oval")) {
                gImg.drawOval(startX, startY, endX - startX, endY - startY);
                repaint();
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


//    public void mouseMoved(MouseEvent me) {
//        x = me.getX();
//        y = me.getY();
//    }
//
//    public void mouseDragged(MouseEvent me) {
//        if (me.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
//            gImg.drawLine(x, y, me.getX(), me.getY());
//            x = me.getX();
//            y = me.getY();
//
//            repaint();
//        }
//    }

    class EventHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Checkbox cb = (Checkbox) e.getSource();
            String color = cb.getLabel();
            String shape = cb.getLabel();
            if (color.equals("검정")) {
                gImg.setColor(Color.black);
            } else if (color.equals("분홍")) {
                gImg.setColor(Color.pink);
            } else if (color.equals("파랑")) {
                gImg.setColor(Color.blue);
            } else if (shape.equals("직선")){
                shapeState = "straight";
            } else if (shape.equals("곡선")){
                shapeState = "curve";
            } else if (shape.equals("사각형")){
                shapeState = "rectangle";
            } else if (shape.equals("원")){
                shapeState = "oval";
            }
        }
    }
}
