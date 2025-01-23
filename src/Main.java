import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame jp = new JFrame();
        jp.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
        jp.setContentPane(new LogIn().Login);
        jp.setSize(500,550);
        jp.setResizable(false);
        jp.setLocationRelativeTo(null);
        jp.setVisible(true); //Hola
    }
}