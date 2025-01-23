import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jp = new JFrame();
        jp.setContentPane();
        jp.setSize(200,200);
        jp.setVisible(true);
        ((JFrame)SwingUtilities.getWindowAncestor()).dispose();


    }
}