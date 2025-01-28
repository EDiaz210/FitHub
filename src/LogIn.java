import Menus.MenuAdmin;
import Menus.MenuMiembros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogIn {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel ImagenLogin;
    private JComboBox comboBox1;
    private JButton ingresarButton;
    public JPanel Login;


    public LogIn() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = ConexionBD.getConnection()) {
                    Statement statement = connection.createStatement();
                    String query = "SELECT * FROM usuarios WHERE usuario = '" + textField1.getText() + "'";
                    ResultSet resultSet = statement.executeQuery(query);


                    if(resultSet.next()) {
                        if (comboBox1.getSelectedItem().equals(resultSet.getString("rol"))) {
                            if (textField1.getText().equals(resultSet.getString("usuario"))) {
                                if (resultSet.getString("rol").equals("administrador")) {
                                    System.out.println("Ingresaste a modo administrador ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(350, 300);
                                    frame.setContentPane(new MenuAdmin().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                } else if (resultSet.getString("rol").equals("miembro")) {
                                    System.out.println("Ingresaste a modo usuario ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(350, 300);
                                    frame.setContentPane(new MenuMiembros().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                }else if (resultSet.getString("rol").equals("entrenador")) {
                                    System.out.println("Ingresaste a modo usuario ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(350, 300);
                                    frame.setContentPane(new MenuAdmin().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                }

                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showInputDialog("Digite correctamente las credenciales");

                }
            }
        });
    }



    public static class ConexionBD {
        private static final String url = "jdbc:mysql://uu0mrbdpzuyyx7gg:qQKJg3BOiryNGsrQdFYP@bjbwtijcisxcuwxsxuat-mysql.services.clever-cloud.com:3306/bjbwtijcisxcuwxsxuat";
        private static final String username = "uu0mrbdpzuyyx7gg";
        private static final String password = "qQKJg3BOiryNGsrQdFYP";
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url,username, password);
        }


    }
}
