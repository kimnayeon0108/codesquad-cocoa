package day19_codeReview;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {

    private BufferedImage background;
    private Forky forky;

    public GameWindow() {       // 생성자 생성
        lodeImage("/Users/kimnayeon/codesquad-cocoa/week4_mission8/resources/pexels-miguel-á-padriñán-255379.jpg");
        init();
        initForky();
//        addEvent();
    }

//    private void addEvent() {
//        input = new Input();
//        addMouseListener(input);
//        addMouseMotionListener(input);
//    }

    private void initForky() {
        forky = new Forky();
    }

    private void init() {
        setTitle("Cute Character");
        setSize(background.getWidth(), background.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void lodeImage(String path) {
//        File f = new File(path);        // 여기 이해 안감.. 인자로 받은게 무엇을 의미하는지 ..
        try {
            background = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void paint(Graphics g){
        g.drawImage(background, 0, 0, this);
    }

    public static void main(String[] args) {
        GameWindow frame = new GameWindow();
        frame.setVisible(true);
    }

}
