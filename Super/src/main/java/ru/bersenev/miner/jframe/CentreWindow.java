package ru.bersenev.miner.jframe;

import javax.swing.*;
import java.awt.*;

public class CentreWindow {
    public static void centreWindow(JFrame frame) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
     /*   try {
            //получаем системную тему
            String systemLookAndFeelClassName = javax.swing.UIManager.getSystemLookAndFeelClassName();
            //устанавливаем её
            javax.swing.UIManager.setLookAndFeel(systemLookAndFeelClassName);
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
