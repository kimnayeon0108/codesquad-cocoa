package day19_codeReview;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Forky {

    public Forky(){
        lodeImage();
    }

    private void lodeImage() {
        getImage();
    }

    private BufferedImage getImage() {
        File f = new File("/Users/kimnayeon/codesquad-cocoa/week4_mission8/resources/Forky_waving.png");
        try {
            return ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
