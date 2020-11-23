package component;

import java.awt.*;

public class FileDialogTest {
    public static void main(String[] args) {
        Frame f = new Frame("Parent");
        f.setSize(300, 200);

        FileDialog fileOpen = new FileDialog(f, "파일열기", FileDialog.LOAD);

        f.setVisible(true);
        fileOpen.setDirectory("c:\\jdk1.8");
        fileOpen.setVisible(true);

        System.out.println(fileOpen.getDirectory() + fileOpen.getFile());
    }
}
