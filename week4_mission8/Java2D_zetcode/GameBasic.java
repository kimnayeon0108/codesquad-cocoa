package Java2D_zetcode;

import javax.swing.*;
import java.awt.*;

public class GameBasic extends JFrame {

    public GameBasic(){

        initUI();
    }

    private void initUI(){
        add(new Board());

        setSize(250, 200);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            GameBasic game = new GameBasic();
            game.setVisible(true);
        });
    }
}
