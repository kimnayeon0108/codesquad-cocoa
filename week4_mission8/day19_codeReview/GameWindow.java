package day19_codeReview;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {

    private BufferedImage background;

    public GameWindow() {       // 생성자 생성
        lodeImage("/Users/kimnayeon/Downloads/pexels-miguel-á-padriñán-255379.jpg");

    }

    private void lodeImage(String path) {
        File f = new File("./");        // 여기 이해 안감.. 인자로 받은게 무엇을 의미하는지 ..
        try {
            background = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


    }

}
