package LogIn;

import Menus.MenuAdm;
import Menus.MenuMiembros;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogIn {
    private JTextField textField1;
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
                    String query = "SELECT * FROM usuarios WHERE cedula_usuario = '" + textField1.getText() + "'";
                    ResultSet resultSet = statement.executeQuery(query);


                    if(resultSet.next()) {
                        if (comboBox1.getSelectedItem().equals(resultSet.getString("rol"))) {
                            if (textField1.getText().equals(resultSet.getString("cedula_usuario"))) {
                                if (resultSet.getString("rol").equals("Administrador")) {
                                    System.out.println("Ingresaste a modo administrador ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(350, 350);
                                    frame.setContentPane(new MenuAdm().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                } else if (resultSet.getString("rol").equals("Miembro")) {
                                    System.out.println("Ingresaste a modo usuario ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(350, 300);
                                    frame.setContentPane(new MenuMiembros().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                }else if (resultSet.getString("rol").equals("Entrenador")) {
                                    System.out.println("Ingresaste a modo usuario ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(350, 350);
                                    frame.setContentPane(new MenuAdm().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                }

                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Digite correctamente las credenciales");

                }
            }
        });
    }



    public static class ConexionBD {
        private static final String url = "jdbc:mysql://localhost:3306/fithub";
        private static final String username = "root";
        private static final String password = "123456";
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url,username, password);
        }


    }
}
